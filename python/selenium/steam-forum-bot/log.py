import datetime
import sqlite3


class Log(object):
    def __init__(self):
        super(Log, self).__init__()
        sql = 'CREATE TABLE if not exists log (game int, ts datetime)'
        self.connection = sqlite3.connect('log.sqlite')
        self.cursor = self.connection.cursor()
        self.cursor.execute(sql)
        self.connection.commit()

    def __del__(self):
        self.cursor.close()
        self.connection.close()

    def insert(self, game):
        sql = 'INSERT INTO log VALUES (?, ?)'
        self.cursor.execute(sql, (game, datetime.datetime.now()))
        self.connection.commit()

    def check(self, game):
        sql = 'SELECT * FROM log WHERE game = ? and datetime(ts) > datetime(?)'
        yesterday = datetime.datetime.now() + datetime.timedelta(days=-1)
        return self.cursor.execute(sql, (game, yesterday)).fetchone() is not None


log = Log()