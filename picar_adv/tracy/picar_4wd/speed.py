import RPi.GPIO as GPIO
import math

class Speed():
    def __init__(self, pin):
        self.tick_counter = 0
        self.speed = 0
        self.rps = 0
        self.pin = pin
        GPIO.setmode(GPIO.BCM)
        GPIO.setup(pin, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)
        GPIO.add_event_detect(pin, GPIO.BOTH, callback=self.callback_counter)

    def callback_counter(self,value):
        self.tick_counter += 1
        print(self.tick_counter)

    def calc_speed(self):
        count = self.tick_counter / 2
        self.rps = count / 20.0
        self.speed = round(2 * math.pi * 3.5 * self.rps, 2)
