import .config
from tracy import utils


class TracyController():
    def __init__(self):
        self.__full_manual_control = False
        self.__monitor_thread_started = False
        self.__init_threads()

    def __init_threads(self):
        # Monitor-Thread
        config.TR.threads['monitor'].start()
        self.__monitor_thread_started = True

        # Autopilot-Thread
        config.TR.threads['autopilot'].start()
        self.__autopilot_thread_started = True

    # die Hauptschleife des Programms
    # wir haben jederzeit die interaktive Kontrolle
    def run(self):

        config.TR.logger.debug('starting main loop...')
        while True:
            key = utils.readkey()

            config.TR.logger.debug('Taste gedrückt: ' + key)
            if key == 'w':
                config.TR.logger.debug('Versuche nach vorne zu fahren...')
                # self.tracy.move_forward()
            elif key == 'a':
                config.TR.logger.debug('Versuche nach links zu drehen...')
                # self.tracy.turn_to_the_left()
            elif key == 's':
                config.TR.logger.debug('Versuche nach hinten zu fahren...')
                # self.tracy.move_backward()
            elif key == 'd':
                config.TR.logger.debug('Versuche nach rechts zu drehen...')
                # self.tracy.turn_to_the_right()
            elif key == 't':
                config.TR.logger.debug('Versuche 90 Grad nach links zu drehen...')
                # self.tracy.turn_degrees_left(90.0)
            elif key == 'z':
                config.TR.logger.debug('Versuche 90 Grad nach rechts zu drehen...')
                # self.tracy.turn_degrees_right(90.0)
            elif key == 'x':
                config.TR.logger.debug('Versuche zu stoppen...')
                # self.autopilot.stop_moving()
                # self.tracy.stop_moving()
            elif key == 'q':
                config.TR.logger.debug('Versuche zu beenden...')
                # if self.monitor_thread_started == True:
                #    self.stop_monitor_thread()
                # if self.autopilot_thread_started == True:
                #    self.stop_autopilot_thread()
                # if self.speed_sensor_thread_started == True:
                #    self.speed_sensor.stop()
                # if self.ultrasonic_sensor_thread_started == True:
                #    self.stop_ultrasonic_thread()
                break
            elif key == '0':
                config.TR.logger.debug(
                    'Testcase: Versuche 1m geradeaus zu fahren...')

                # self.autopilot.move_forward()
                # time.sleep(3.5)
                # self.autopilot.stop_moving()
                # _distance2drive = 100
                # _distance_driven = 0
                # self.speed_sensor.tick_counter = 0
                # while _distance_driven <= _distance2drive:
                # _distance_driven += self.speed_sensor.speed
                # time.sleep(0.05)
                # self.speed_sensor.calc_speed()
                # print(_distance_driven)
                # self.autopilot.stop_moving()
            elif key == '1':
                pass
                # tracy.TR.get_ref('logger').debug('Testcase:\
                # Erkundungsautopilot...')
                # self.tracy.go_exploring()
            elif key == '2':
                pass
                # self.ultrasonic_sensor.do_full_scan()
            elif key == '3':
                pass
            elif key == '4':
                pass
            elif key == '5':
                pass
            elif key == '6':
                pass
            elif key == '7':
                pass
            elif key == '8':
                pass
            elif key == '9':
                pass
            # volle manuelle Kontrolle - keine Kollisionsvermeidung!
            elif key == 'm':
                pass
                # self.full_manual_control = not self.full_manual_control\
                #   # invert switch
                # if self.full_manual_control == True:
                #    tracy.TR.logger.debug(
                #        'Versuche manuelle Kontrolle zu bekommen...')
                # else:
                #    tracy.TR.logger.debug(
                #        'Versuche manuelle Kontrolle abzugeben...')
                # self.tracy.switch_manual_control(self.full_manual_control)
                # else:
                #   tracy.TR.logger.debug('Unbekannte Taste: ' + key)

        # post orgy stuff
        config.TR.logger.debug('main loop ended...')

    def notify_autopilot(self):
        # tracy.TR.thread_data['last_message_to_autopilot_fromcontroller']
        pass

    # def stop_monitor_thread(self):
    #    self.monitor.stop()
    #    self.monitor = None
    #    self.monitor_thread_started = False

    # def stop_autopilot_thread(self):
    #    self.autopilot.stop()
    #    self.autopilot = None
    #    self.autopilot_thread_started = False

    # def stop_speed_thread(self):
    #    self.speed_sensor.stop()
    #    self.speed_sensor = None
    #    self.speed_sensor_thread_started = False

    # def stop_ultrasonic_thread(self):
    #    self.ultrasonic_sensor.stop()
    #    self.ultrasonic_sensor= None
    #    self.ultrasonic_sensor_thread_started = False
