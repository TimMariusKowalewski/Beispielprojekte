import time, math
import threading
import picar_4wd as fc
from picar_4wd.adc import ADC

class ADC2():

    def __init__(self,  thread_name = 'ADCThread'):
        self.sleep_time = 0.001
        self.timer_flag = True
        self.timer = threading.Thread(target=self.fun_timer, name=thread_name)
        self.adc0 = ADC(0)
        self.adc1 = ADC(1)
        self.adc2 = ADC(2)
        self.adc3 = ADC(3)
        self.adc4 = ADC(4)
        self.adc5 = ADC(5)
        self.adc6 = ADC(6)
        self.adc7 = ADC(7)

    def start(self):
        self.timer.start()

    def fun_timer(self):
        while self.timer_flag:
            fc.sensor_adc_channel0 = self.adc0.read()
            fc.sensor_adc_channel1 = self.adc1.read()
            fc.sensor_adc_channel2 = self.adc2.read()
            fc.sensor_adc_channel3 = self.adc3.read()
            fc.sensor_adc_channel4 = self.adc4.read()
            fc.sensor_adc_channel5 = self.adc5.read()
            fc.sensor_adc_channel6 = self.adc6.read()
            fc.sensor_adc_channel7 = self.adc7.read()
            time.sleep(self.sleep_time)

    def deinit(self):
        self.timer_flag = False
        self.timer.join()