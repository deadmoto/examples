from selenium import webdriver
from selenium.common.exceptions import NoSuchElementException, TimeoutException
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait

from inventory import Inventory
from log import log


profile = webdriver.FirefoxProfile("./firefox")
driver = webdriver.Firefox(firefox_profile=profile)
games = Inventory.games()
for game in sorted(games.keys(), key=lambda g: len(games[g]), reverse=True):
    if log.check(game):
        print 'Topic for game #{} was created less than 24 hours ago'.format(game)
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
        log.insert(game)
        print 'Created a topic for game #{}: {}'.format(game, topic.encode('utf_8'))
    except NoSuchElementException:
        print('Failed to create topic for game {}!'.format(game))
        print('URL: {}'.format(url))
    except TimeoutException:
        print 'You cannot create one more topic!'.format(game)
driver.quit()