from tracy.servo_adapter import ServoAdapter
from tracy.picar_4wd.servo import Servo
from tracy.picar_4wd.pwm import PWM


class ServoPicar4WD(ServoAdapter):
    def __init__(self):
        self.servo = Servo(PWM("P0"), 0)

    def set_position(self, Position: int):
        return True
