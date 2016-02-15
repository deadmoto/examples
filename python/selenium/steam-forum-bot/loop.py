from time import sleep

from inventory import Inventory
from script import Bot

while True:
    try:
        games = Inventory.games()
        bot = Bot()
        bot.process(games)
    except Exception as e:
        print e.message
    finally:
        sleep(60 * 1)
