import tracy.config as config
from tracy.motor_adapter import MotorAdapter


class MotorFake(MotorAdapter):
    def __init__(self):
        pass

    def set_power(self, PowerValue: int):
        assert PowerValue >= -100 and PowerValue <= 100
        config.TR.logger.debug('set_power(' + str(PowerValue) + ')')
        # no real hardware to talk to
        return True

    def set_power_for_left_turn(self, PowerValue: int):
        assert PowerValue >= -100 and PowerValue <= 100
        config.TR.logger.debug('set_power_for_left_turn(' + str(PowerValue) + ')')
        # no real hardware to talk to
        return True

    def set_power_for_right_turn(self, PowerValue: int):
        assert PowerValue >= -100 and PowerValue <= 100
        config.TR.logger.debug('set_power_for_right_turn(' + str(PowerValue) + ')')
        # no real hardware to talk to
        return True
