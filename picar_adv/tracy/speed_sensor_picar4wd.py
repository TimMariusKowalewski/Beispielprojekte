from tracy.speed_sensor_adapter import SpeedSensorAdapter
import RPi.GPIO as GPIO
import math


class SpeedSensorPicar4WD(SpeedSensorAdapter):
    def __init__(self, global_thread_data, global_sensor_data):
        self.speed = 0
        self.rps = 0
        self.tick_counter = 0

        # init pins
        GPIO.setmode(GPIO.BCM)
        GPIO.setup(25, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)
        GPIO.add_event_detect(25, GPIO.BOTH, callback=self.callback_counter)

    def callback_counter(self, Value):
        self.tick_counter += 1
        self.calc_speed()
        # print(self.tick_counter)

    def calc_speed(self):
        count = self.tick_counter / 2
        self.rps = count / 20.0
        self.speed = round(2 * math.pi * 3.5 * self.rps, 2)
