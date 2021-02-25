import config
from tracy.motor_adapter import MotorAdapter
from tracy.picar_4wd.motor import Motor
from tracy.picar_4wd.pwm import PWM
from tracy.picar_4wd.pin import Pin

LEFT_FRONT_DEFAULT_POWER = 15
RIGHT_FRONT_DEFAULT_POWER = 15
LEFT_REAR_DEFAULT_POWER = 10
RIGHT_REAR_DEFAULT_POWER = 10
DEFAULT_TURN_POWER = 50


class MotorPicar4WD(MotorAdapter):
    def __init__(self):
        self.motor_left_front = Motor(PWM("P13"), Pin("D4"), False)
        self.motor_right_front = Motor(PWM("P12"), Pin("D5"), False)
        self.motor_left_rear = Motor(PWM("P8"), Pin("D11"), False)
        self.motor_right_rear = Motor(PWM("P9"), Pin("D15"), False)

    def set_power(self, PowerValue: int):
        assert PowerValue >= -100 and PowerValue <= 100
        config.TR.logger.debug('set_power(' + str(PowerValue) + ')')

        # andere Werte mit defaults ersetzen
        if PowerValue == 0:
            self.motor_left_front.set_power(0)
            self.motor_right_front.set_power(0)
            self.motor_left_rear.set_power(0)
            self.motor_right_rear.set_power(0)
        else:
            # self.motor_left_front.set_power(LEFT_FRONT_DEFAULT_POWER)
            # self.motor_right_front.set_power(RIGHT_FRONT_DEFAULT_POWER)
            # self.motor_left_rear.set_power(LEFT_REAR_DEFAULT_POWER)
            # self.motor_right_rear.set_power(RIGHT_REAR_DEFAULT_POWER)
            self.motor_left_front.set_power(LEFT_FRONT_DEFAULT_POWER)
            self.motor_right_front.set_power(RIGHT_FRONT_DEFAULT_POWER)
            self.motor_left_rear.set_power(LEFT_REAR_DEFAULT_POWER)
            self.motor_right_rear.set_power(RIGHT_REAR_DEFAULT_POWER)

        return True

    def set_power_backward(self, PowerValue: int):
        assert PowerValue >= -100 and PowerValue <= 100
        config.TR.logger.debug('set_power(' + str(PowerValue) + ')')

        # andere Werte mit defaults ersetzen
        if PowerValue == 0:
            self.motor_left_front.set_power(0)
            self.motor_right_front.set_power(0)
            self.motor_left_rear.set_power(0)
            self.motor_right_rear.set_power(0)
        else:
            self.motor_left_front.set_power(-LEFT_FRONT_DEFAULT_POWER)
            self.motor_right_front.set_power(-RIGHT_FRONT_DEFAULT_POWER)
            self.motor_left_rear.set_power(-LEFT_REAR_DEFAULT_POWER)
            self.motor_right_rear.set_power(-RIGHT_REAR_DEFAULT_POWER)

        return True

    def set_power_for_left_turn(self, PowerValue: int):
        assert PowerValue >= -100 and PowerValue <= 100
        config.TR.logger.debug('set_power_for_left_turn(' + str(PowerValue) + ')')

        self.motor_left_front.set_power(-DEFAULT_TURN_POWER)
        self.motor_right_front.set_power(DEFAULT_TURN_POWER)
        self.motor_left_rear.set_power(-DEFAULT_TURN_POWER)
        self.motor_right_rear.set_power(DEFAULT_TURN_POWER)

        return True

    def set_power_for_right_turn(self, PowerValue: int):
        assert PowerValue >= -100 and PowerValue <= 100
        config.TR.logger.debug('set_power_for_right_turn(' + str(PowerValue) + ')')

        self.motor_left_front.set_power(DEFAULT_TURN_POWER)
        self.motor_right_front.set_power(-DEFAULT_TURN_POWER)
        self.motor_left_rear.set_power(DEFAULT_TURN_POWER)
        self.motor_right_rear.set_power(-DEFAULT_TURN_POWER)

        return True
