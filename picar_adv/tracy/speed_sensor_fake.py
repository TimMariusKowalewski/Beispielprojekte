import random
from tracy.speed_sensor_adapter import SpeedSensorAdapter


class SpeedSensorFake(SpeedSensorAdapter):
    def __init__(self):
        pass

    def calc_speed(self):
        self.speed = random.randrange(1, 10) * 0.1
        # self.global_sensor_data['sensor_left_rear_speed'] = self.speed
