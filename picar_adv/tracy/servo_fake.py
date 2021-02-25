from tracy.servo_adapter import ServoAdapter


class ServoFake(ServoAdapter):
    def set_position(self, Position: int):
        return True
