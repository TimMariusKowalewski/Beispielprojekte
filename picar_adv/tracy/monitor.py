import tracy.config as config
import time
import threading


class Monitor():
    def __init__(self):
        self.thread_name = "MonitorThread"
        self.timer_flag = True
        self.timer = threading.Thread(target=self.func, name=self.thread_name)

    def start(self):
        _log_msg = self.thread_name + ' startet...'
        config.TR.logger.debug(_log_msg)
        self.timer.start()

    def stop(self):
        _log_msg = self.thread_name + ' stoppt...'
        config.TR.logger.debug(_log_msg)
        self.timer_flag = False
        self.timer.join()

    def func(self):
        while self.timer_flag:
            _log_msg = 'ping from ' + self.thread_name
            config.TR.logger.debug(_log_msg)
            time.sleep(config.TR.constants.MONITOR_THREAD_SLEEP_TIME)
