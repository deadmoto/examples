from time import sleep

from inventory import Inventory
from script import Bot

while True:
    games = Inventory.games()
    bot = Bot()
    bot.process(games)
    sleep(60 * 15)
