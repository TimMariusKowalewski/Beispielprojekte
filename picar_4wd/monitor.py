import time, math, datetime
import threading
import picar_4wd as fc
#from influxdb import InfluxDBClient
from picar_4wd.gyroscope import Gyroscope

class Monitor():
    def __init__(self, thread_name, speed_left, gyroscope):
        self.speed_left = speed_left
        self.gyroscope = gyroscope
        self.timer_flag = True
        self.timeout = 1
        self.timer = threading.Thread(target=self.fun_timer, name=thread_name)

        #self.client = InfluxDBClient(host='localhost', port=8086)
        #self.client.switch_database('sensors')

    def start(self):
        self.timer.start()

    def fun_timer(self):
        while self.timer_flag:
            self.read_sensors()
            #self.writeIntoDB()
            time.sleep(self.timeout)
    
    def read_sensors(self):
        self.gyroscope.read_values()
        self.speed_left.calc_speed()

    def writeIntoDB(self):
        #valueVoltage = utils.power_read()
        json_body = [
        {
            "measurement": "pi_sensor_data",
            "fields": {
                'car_status': fc.car_status,
                'sensor_left_rear_speed': fc.sensor_left_rear_speed,
                'sensor_right_rear_speed': fc.sensor_right_rear_speed,
                'sensor_x_rotation': fc.sensor_x_rotation,
                'sensor_y_rotation': fc.sensor_y_rotation,
                'sensor_z_rotation': fc.sensor_z_rotation,
                'sensor_gyro_gx': fc.sensor_gyro_gx,
                'sensor_gyro_gy': fc.sensor_gyro_gy,
                'sensor_gyro_gz': fc.sensor_gyro_gz,
                'sensor_gyro_ax': fc.sensor_gyro_ax,
                'sensor_gyro_ay': fc.sensor_gyro_ay,
                'sensor_gyro_az': fc.sensor_gyro_az,
                'sensor_gyro_temp': fc.sensor_gyro_temp,
                'sensor_adc_channel0': fc.sensor_adc_channel0,
                'sensor_adc_channel1': fc.sensor_adc_channel1,
                'sensor_adc_channel2': fc.sensor_adc_channel2,
                'sensor_adc_channel3': fc.sensor_adc_channel3,
                'sensor_adc_channel4': fc.sensor_adc_channel4,
                'sensor_adc_channel5': fc.sensor_adc_channel5,
                'sensor_adc_channel6': fc.sensor_adc_channel6,
                'sensor_adc_channel7': fc.sensor_adc_channel7
            }
        }
        ]
        #self.client.write_points(json_body)

    def deinit(self):
        self.timer_flag = False
        self.timer.join()
