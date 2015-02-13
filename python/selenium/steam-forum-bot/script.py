from datetime import datetime, timedelta
from re import search
from time import strptime, mktime

from selenium import webdriver
from selenium.common.exceptions import NoSuchElementException, TimeoutException
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait

from inventory import Inventory
from log import log


class Bot(object):
    def process(self, games):
        for game in sorted(games.keys(), key=lambda g: len(games[g]), reverse=True):
            expires = log.select(game)
            if expires is not None:
                if expires[0] > datetime.now():
                    print 'Topic for game #{} cannot be created till {}'.format(game, expires[0])
                    continue
            print 'Processing game #{}'.format(game)
            topic = '[H] ' + games[game].pop()
            while len(games[game]) > 0:
                topic += ', '
                topic += games[game].pop()
            topic += ' [W] Any other cards 1:1+'
            url = "http://steamcommunity.com/app/{}/tradingforum/".format(game)
            driver.get(url)
            try:
                driver.find_element_by_id('age_gate_btn_continue').click()
            except NoSuchElementException:
                pass
            try:
                driver.find_element_by_xpath('//a[contains(@href,\'Forum_CreateTopic\')]').click()
                wait = WebDriverWait(driver, 2)
                condition = expected_conditions.visibility_of_element_located((By.CLASS_NAME, 'forum_topic_input'))
                forum_topic_input = wait.until(condition)
                forum_topic_input.send_keys(topic)
                text = 'I have cards you need and expect just a little bit more value in return.\n'
                text += 'I do not accept coupons, smiles or backgrounds.'
                driver.find_element_by_class_name('forumtopic_reply_textarea').send_keys(text)
                driver.find_element_by_class_name('btn_green_white_innerfade').click()
                condition = expected_conditions.invisibility_of_element_located((By.CLASS_NAME, 'forum_topic_input'))
                wait.until(condition)
                log.insert(game, datetime.now() + timedelta(days=1))
                print 'Created a topic for game #{}: {}'.format(game, topic.encode('utf_8'))
            except NoSuchElementException:
                print('Failed to create topic for game {}!'.format(game))
                print('URL: {}'.format(url))
            except TimeoutException:
                try:
                    error = driver.find_element_by_class_name('forum_newtopic_error').text
                    match = search('[\w]{3}\s[\d]{1,2}\s@\s[\d]{1,2}:[\d]{2}[\w]{2}', error)
                    if match is None:
                        raise NoSuchElementException
                    group = match.group(0)
                    expires = strptime('{} {}'.format(datetime.now().year, group), '%Y %b %d @ %I:%M%p')
                    log.insert(game, datetime.fromtimestamp(mktime(expires)))
                    print 'Topic for game #{} cannot be created till {}'.format(game, datetime.fromtimestamp(mktime(expires)))
                except NoSuchElementException:
                    print 'Topic for game #{} was not created!'.format(game)


profile = webdriver.FirefoxProfile("./firefox")
driver = webdriver.Firefox(firefox_profile=profile)
games = Inventory.games()
bot = Bot()
bot.process(games)
driver.quit()