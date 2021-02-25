import tracy.config as config
import threading
import time


CONTROL_STATE_NO_CONTROL = 0
CONTROL_STATE_DRIVE_ASSIST = 1
CONTROL_STATE_FULL_CONTROL = 2

CAR_STATUS_NOT_MOVING = 0
CAR_STATUS_MOVING_FORWARD = 1
CAR_STATUS_MOVING_BACKWARD = 2
CAR_STATUS_TURNING_LEFT = 3
CAR_STATUS_TURNING_RIGHT = 4


class Autopilot():
    def __init__(self):
        self.control_state = CONTROL_STATE_NO_CONTROL
        self.car_status = CAR_STATUS_NOT_MOVING

        self.thread_name = "AutopilotThread"
        self.timer_flag = True
        self.timer = threading.Thread(target=self.func, name=self.thread_name)

        # Zeitstempel für Benachrichtigungen
        self.last_time_controller = 0

    def start(self):
        _log_msg = self.thread_name + " startet..."
        config.TR.logger.debug(_log_msg)
        self.timer.start()

    def stop(self):
        _log_msg = self.thread_name + " stoppt..."
        config.TR.logger.debug(_log_msg)
        self.timer_flag = False
        self.timer.join()

    def func(self):
        while self.timer_flag:
            _log_msg = 'ping from ' + self.thread_name
            config.TR.logger.debug(_log_msg)

            # Motor ausmachen? - Bewegungsbefehle geben u.U. eine Laufzeit vor
            if self.stop_motor_after_time != 0:
                _timestamp = time.time()
                if __debug__:
                    _log_msg = 'Motor könnte deaktiviert werden:\n'
                    _log_msg += 'Wert von self.stop_motor_after_time: ' +\
                        str(self.stop_motor_after_time) + '\n'
                    _log_msg += 'Wert von _timestamp: ' + str(_timestamp) +\
                        '\n'
                    config.TR.logger.debug(_log_msg)
                if _timestamp >= self.stop_motor_after_time:
                    if __debug__:
                        config.TR.logger.debug("Motor muss deaktiviert werden")
                    self.stop_moving()
                    self.stop_motor_after_time = 0
                else:
                    if __debug__:
                        config.TR.logger.debug("Motor muss noch laufen")
            else:
                if __debug__:
                    config.TR.logger.debug("Kein Grund Motor auszumachen")

            # Motor ausmachen? - haben wir eine bestimmte Distanz zurückgelegt?
            if self.distance_travel is True:
                if float(self.stop_motor_after_distance) <= 0.0:
                    self.stop_moving()
                    self.stop_motor_after_distance = 0.0
                    self.distance_travel = False
                    config.TR.logger.debug("Motor aus: Distanz erreicht")
                else:
                    if __debug__:
                        config.TR.logger.debug(
                            "Wert von self.stop_motor_after_distance: " +
                            str(self.stop_motor_after_distance))
                        config.TR.logger.debug(
                            "Wert von self.global_sensor_data\
                            ['sensor_left_rear_speed']: "
                            + str(
                                self.global_sensor_data[
                                    'sensor_left_rear_speed'
                                ]
                            )
                        )
                    self.stop_motor_after_distance -=\
                        self.global_sensor_data['sensor_left_rear_speed']

            # Benachrichtigungen vom Controller prüfen
            if (
                config.TR.thread_data['last_message_to_autopilot_from_controller'] >
                self.last_time_controller
            ):
                self.last_time_controller = time.time()
                _msg = config.TR.thread_data['message_to_autopilot_from_controller']
                _msg_parts = _msg.split(',')
                if __debug__:
                    config.TR.logger.debug(_msg)
                    config.TR.logger.debug(_msg_parts)

                if _msg_parts[0] == 'move_forward':
                    self.move_forward()
                elif _msg_parts[0] == 'move_backward':
                    self.move_backward()
                elif _msg_parts[0] == 'turn_around':
                    self.turn_around()
                elif _msg_parts[0] == 'turn_to_the_left':
                    self.turn_to_the_left(_msg_parts[1])
                elif _msg_parts[0] == 'turn_to_the_right':
                    self.turn_to_the_right(_msg_parts[1])
                elif _msg_parts[0] == 'turn_degrees_left':
                    self.turn_degrees_left(_msg_parts[1])
                elif _msg_parts[0] == 'turn_degrees_right':
                    self.turn_degrees_right(_msg_parts[1])
                elif _msg_parts[0] == 'stop':
                    self.stop_moving()
                elif _msg_parts[0] == 'move_distance_forward':
                    self.move_distance_forward(_msg_parts[1])
                elif _msg_parts[0] == 'move_distance_backward':
                    self.move_distance_backward(_msg_parts[1])
                elif _msg_parts[0] == 'switch_manual_control':
                    self.switch_manual_control(_msg_parts[1])
                else:
                    if __debug__:
                        config.TR.logger.debug('Unbekannte Nachricht: ' + str(_msg))
                        config.TR.logger.debug(str(_msg_parts))

            time.sleep(config.TR.constants.AUTOPILOT_THREAD_SLEEP_TIME)

    # Bewegungsbefehle
    def move_forward(self):
        self.stop_moving()
        self.__hardware['motor'].set_power(config.TR.constants.DEFAULT_MOTOR_POWER)
        self.stop_motor_after_time = 0
        self.car_status = CAR_STATUS_MOVING_FORWARD

    def move_backward(self):
        self.stop_moving()
        self.__hardware['motor'].set_power_backward(
            -config.TR.constants.DEFAULT_MOTOR_POWER)
        self.stop_motor_after_time = 0
        self.car_status = CAR_STATUS_MOVING_BACKWARD

    def turn_around(self):
        self.turn_degrees_left(180)

    def turn_to_the_left(self, Duration):
        Duration = float(Duration)
        assert Duration > 0

        self.stop_moving()
        self.__hardware['motor'].set_power_for_left_turn(
            config.TR.constants.DEFAULT_MOTOR_POWER)
        self.stop_motor_after_time = time.time() + Duration
        self.car_status = CAR_STATUS_TURNING_LEFT

    def turn_to_the_right(self, Duration):
        Duration = float(Duration)
        assert Duration > 0

        self.stop_moving()
        self.__hardware['motor'].set_power_for_right_turn(
            config.TR.constants.DEFAULT_MOTOR_POWER)
        self.stop_motor_after_time = time.time() + Duration
        self.car_status = CAR_STATUS_TURNING_RIGHT

    def turn_degrees_left(self, Degrees):
        Degrees = float(Degrees)
        assert Degrees > 0
        _turn_duration = (config.TR.constants.DURATION_LEFT_TURN_90 / 90) * Degrees
        assert _turn_duration > 0
        self.turn_to_the_left(_turn_duration)

    def turn_degrees_right(self, Degrees):
        Degrees = float(Degrees)
        assert Degrees > 0
        _turn_duration = (
            config.TR.constants.DURATION_RIGHT_TURN_90 / 90) * Degrees
        assert _turn_duration > 0
        self.turn_to_the_right(_turn_duration)

    def stop_moving(self):
        self.__hardware['motor'].set_power(0)
        self.stop_motor_after_time = 0
        self.car_status = CAR_STATUS_NOT_MOVING

    def move_distance_forward(self, Distance):
        self.__hardware['sensors']['speed'].tick_counter = 0
        gefahrene_distanz = 0
        self.move_forward()
        while gefahrene_distanz <= Distance:
            self.__hardware['sensors']['speed'].calc_speed()
            gefahrene_distanz = self.__hardware['sensors']['speed'].speed
            time.sleep(0.06)
        self.stop_moving()
        self.__hardware['sensors']['speed'].calc_speed()
        print("Gefahrene Distanz: " + str(gefahrene_distanz) + '\n')
        self.__hardware['sensors']['speed'].tick_counter = 0

    def move_distance_backward(self, Distance):
        self.move_backward()
        self.stop_motor_after_distance = float(Distance)
        self.distance_travel = True

    def switch_manual_control(self, ManualControl: bool):
        pass

    def go_exploring(self, ManualControl: bool):
        pass
