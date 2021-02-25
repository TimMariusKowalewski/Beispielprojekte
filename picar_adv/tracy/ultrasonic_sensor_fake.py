from tracy.ultrasonic_sensor_adapter import UltrasonicSensorAdapter


class UltrasonicSensorFake(UltrasonicSensorAdapter):
    def __init__(self):
        pass

    def get_distance(self, Angle):
        pass

    def notify_autopilot(self, rand):
        pass
