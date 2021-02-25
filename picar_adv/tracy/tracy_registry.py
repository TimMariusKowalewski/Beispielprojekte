import logging
from tracy.tracy import Tracy
from tracy.tracysworld import TracysWorld
from tracy.tracy_controller import TracyController
from tracy.motor_fake import MotorFake
from tracy.speed_sensor_fake import SpeedSensorFake
from tracy.ultrasonic_sensor_fake import UltrasonicSensorFake
from tracy.servo_fake import ServoFake
from tracy.monitor import Monitor
from tracy.autopilot import Autopilot


# zentrale Registry (Singleton)
# legt auch Objekte an
class TracyRegistry(object):
    # innere Klasse für Singleton
    class __TracyRegistry:
        def __init__(self):
            self.val = None

        def __str__(self):
            return repr(self) + self.val

        instance = None

        @classmethod
        def __new__(cls):  # __new__ always a classmethod
            if not TracyRegistry.instance:
                TracyRegistry.instance = TracyRegistry.__TracyRegistry()
            return TracyRegistry.instance

        def __getattr__(self, name):
            return getattr(self.instance, name)

        def __setattr__(self, name):
            return setattr(self.instance, name)

    was_inited = False

    # Konstanten
    LOGLEVEL_DEBUG = 'debug'
    LOGLEVEL_WARN = 'warn'
    LOGLEVEL_ERROR = 'error'
    LOGFILE_PATH = '/home/tim/asdlog'
    WORLD_SIZE_X = 200
    WORLD_SIZE_Y = 200
    SPEED_SENSOR_LEFT_REAR_PIN = 23
    AUTOPILOT_THREAD_SLEEP_TIME = 0.1
    MONITOR_THREAD_SLEEP_TIME = 1
    SPEED_SENSOR_THREAD_SLEEP_TIME = 0.1
    ULTRASONIC_SENSOR_THREAD_SLEEP_TIME = 1
    GYROSCOPE_SENSOR_THREAD_SLEEP_TIME = 1
    ADC_SENSOR_THREAD_SLEEP_TIME = 1
    DEFAULT_TURN_DURATION = 10.0
    DEFAULT_TURN_DEGREES = 90.0
    DEFAULT_MOTOR_POWER = 100
    DURATION_LEFT_TURN_90 = 0.59  # Wert muss kalibriert werden
    DURATION_RIGHT_TURN_90 = 0.59  # Wert muss kalibriert werden

    # Objekte
    logger = logging.getLogger(__name__)
    threads = {
        'monitor': Monitor(),
        'autopilot': Autopilot()
    }
    tracy = Tracy(),
    world = TracysWorld(200, 200)
    controller = TracyController()
    hardware = {
        'motor': MotorFake(),
        # 'motor': MotorPicar4WD(),
        'sensors': {
            # 'speed': SpeedSensorPicar4WD(),
            'speed': SpeedSensorFake(),
            # 'gyroscope': GyroscopeFake(global_thread_data, \
            #  global_sensor_data),
            # 'ultrasonic': UltrasonicSensorPicar4WD(),
            'ultrasonic': UltrasonicSensorFake(),
            # 'adc': ADCFake(global_thread_data, global_sensor_data),
            # 'servo': ServoPicar4WD(),
            'servo': ServoFake(),
        },
    }
    sensor_data = {
        'sensor_left_rear_speed': 0,
        'sensor_right_rear_speed': 0,
        'sensor_gyroscope_gx': 0,
        'sensor_gyroscope_gy': 0,
        'sensor_gyroscope_gz': 0,
        'sensor_gyroscope_ax': 0,
        'sensor_gyroscope_ay': 0,
        'sensor_gyroscope_az': 0,
        'sensor_gyroscope_x_rotation': 0,
        'sensor_gyroscope_y_rotation': 0,
        'sensor_gyroscope_z_rotation': 0,
        'sensor_gyroscope_temperatur': 0,
    }
    thread_data = {
        'last_message_to_autopilot_from_controller': 0,
        'message_to_autopilot_from_controller': '',
    }

    @staticmethod
    def init():
        if TracyRegistry.was_inited is True:
            return
        TracyRegistry.was_inited = True

        logging.basicConfig(
            format='%(asctime)s,%(msecs)d %(levelname)-8s\
                [%(filename)s:%(lineno)d] %(message)s',
            datefmt='%Y-%m-%d:%H:%M:%S',
            filename=TracyRegistry.CONSTANTS['LOGFILE_PATH'],
            level=logging.DEBUG)
