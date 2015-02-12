import sqlite3


class Log(object):
    def __init__(self):
        super(Log, self).__init__()
        sql = 'CREATE TABLE if not exists log (game int, expires timestamp)'
        self.connection = sqlite3.connect('log.sqlite', detect_types=sqlite3.PARSE_DECLTYPES)
        self.cursor = self.connection.cursor()
        self.cursor.execute(sql)
        self.connection.commit()

    def __del__(self):
        self.cursor.close()
        self.connection.close()

    def insert(self, game, expires):
        sql = 'INSERT INTO log VALUES (?, ?)'
        self.cursor.execute(sql, (game, expires))
        self.connection.commit()

    def select(self, game):
        sql = 'SELECT expires FROM log WHERE game = ? ORDER BY expires DESC'
        return self.cursor.execute(sql, (game,)).fetchone()


log = Log()