from datetime import datetime, timedelta
from re import search
from time import strptime, mktime

from selenium import webdriver
from selenium.common.exceptions import NoSuchElementException, TimeoutException, UnexpectedAlertPresentException
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait

from log import log


class PostingTooFrequentlyException(Exception):
    pass


class Bot(object):
    def __init__(self):
        super(Bot, self).__init__()
        profile = webdriver.FirefoxProfile("./firefox")
        self.driver = webdriver.Firefox(firefox_profile=profile)
        self.driver.set_window_position(0, 1200)

    @staticmethod
    def is_expired(game):
        expires = log.select(game)
        if expires is not None:
            if expires[0] > datetime.now():
                return True

    def check_error_message(self, game):
        text = self.driver.find_element_by_class_name('forum_newtopic_error').text
        if 'You\'ve been posting too frequently' in text:
            raise PostingTooFrequentlyException(text)
        error = self.driver.find_element_by_class_name('forum_newtopic_error').text
        if 'There is a limit on how frequently you can post in this forum' in error:
            match = search('[\d]{1,2}\s[\w]{3}\s@\s[\d]{1,2}:[\d]{2}[\w]{2}', error)
            group = match.group(0)
            expires = strptime('{} {}'.format(datetime.now().year, group), '%Y %d %b @ %I:%M%p')
            log.insert(game, datetime.fromtimestamp(mktime(expires)))
            print('Topic for game #{} cannot be created until {}'.format(game, datetime.fromtimestamp(mktime(expires))))

    def post_topic(self, game, items):
        if self.is_expired(game):
            return
        print('Processing game #{}'.format(game))
        cards = ', '.join(items)
        if game in [u'550', u'730']:
            topic = u'[H] {} [W] Foils'.format(cards)
        else:
            topic = u'[H] {} [W] Any other cards 1:1+'.format(cards)
        url = "http://steamcommunity.com/app/{}/tradingforum/".format(game)
        self.driver.get(url)
        try:
            self.driver.find_element_by_id('age_gate_btn_continue').click()
        except NoSuchElementException:
            pass
        try:
            self.driver.find_element_by_xpath('//a[contains(@href,\'Forum_CreateTopic\')]').click()
            wait = WebDriverWait(self.driver, 2)
            condition = expected_conditions.visibility_of_element_located((By.CLASS_NAME, 'forum_topic_input'))
            forum_topic_input = wait.until(condition)
            forum_topic_input.send_keys(topic)
            text = 'I have cards you may need and expect just a bit more value in return.\n'
            text += 'I do not accept coupons, smiles or backgrounds.'
            self.driver.find_element_by_class_name('forumtopic_reply_textarea').send_keys(text)
            self.driver.find_element_by_class_name('btn_green_white_innerfade').click()
            condition = expected_conditions.invisibility_of_element_located((By.CLASS_NAME, 'forum_topic_input'))
            wait.until(condition)
            log.insert(game, datetime.now() + timedelta(days=1))
            print('Created a topic for game #{}: {}'.format(game, topic.encode('utf_8')))
        except NoSuchElementException:
            print('Failed to create topic for game {}!'.format(game))
            print('URL: {}'.format(url))
        except TimeoutException:
            try:
                self.check_error_message(game)
            except NoSuchElementException:
                print('Topic for game #{} was not created!'.format(game))
        except UnexpectedAlertPresentException as e:
            print(e)

    def process(self, games):
        for game in sorted(games.keys(), key=lambda g: len(games[g]), reverse=True):
            self.post_topic(game, games[game])
        self.driver.quit()
