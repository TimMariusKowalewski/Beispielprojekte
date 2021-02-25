import config
import time
import math
import numpy as np
from tracy.picar_4wd.ultrasonic import Ultrasonic
from tracy.picar_4wd.pin import Pin
from tracy.picar_4wd.servo import Servo
from tracy.picar_4wd.pwm import PWM
from tracy.ultrasonic_sensor_adapter import UltrasonicSensorAdapter


class UltrasonicSensorPicar4WD(UltrasonicSensorAdapter):
    def __init__(self):
        self.us = Ultrasonic(Pin('D8'), Pin('D9'))
        self.servo = Servo(PWM("P0"), offset=0)

    def notify_autopilot(self, rand):
        pass

    def do_full_scan(self):
        # setup full scan
        # Distanzen im Winkel phi, wir denken hier in Polarkoordinaten
        phi_deg2distance = dict()
        # Distanzen zum Mittelpunkt des Autos im Winkel phi, wir denken hier\
        # in Polarkoordinaten
        phi_deg2distance_real = dict()
        phi_deg2coords = dict()  # Koordinaten für den getroffenen Punkt in phi
        # das ist nicht phi - die Motor-API denkt in Werten von -90 (90 Grad
        # rechts) bis 90 (90 Grad links)
        start_angle = -90
        # das ist nicht phi - die Motor-API denkt in Werten von -90 (90 Grad
        # rechts) bis 90 (90 Grad links)
        end_angle = 90 + 1

        MAX_DISTANCE = 40  # 40 cm, alles darüber ist eh ungenau
        # Distanz zwischen dem Mittelpunkt des Autos und dem Rotationspunkt
        # des Servos
        LENGTH_VECTOR_A = 11.5
        # Distanz zwischen dem Rotationspunkt des Servos und der Spitze des
        # Ultraschallsensors
        LENGTH_VECTOR_B = 2.4

        # Planquadraten zuordnen
        _quadrants = dict()

        # aktuelle Position im Planquadratsytem
        _quadrant_x_offset = 50
        _quadrant_y_offset = 50

        # Größe eines Planquadrats
        _quadrant_size_x = 30
        _quadrant_size_y = 30

        # Anzahl Planquadrate
        _number_quadrants_x = 100
        _number_quadrants_y = 100

        # Planquadrate initialisieren
        for i in range(0, _number_quadrants_x + 1):
            for j in range(0, _number_quadrants_y + 1):
                _key = str(i) + ',' + str(j)
                _quadrants[_key] = 0

        _steps = 1
        for _step in range(_steps):
            for _angle in range(start_angle, end_angle):
                time.sleep(0.01)
                _phi_deg = _angle + 90  # das ist phi (als Polarkoordinate)
                self.servo.set_angle(_angle)
                _distance = self.us.get_distance() + LENGTH_VECTOR_B

                # Werte, die uns nicht weiterhelfen, ignorieren
                if _distance >= MAX_DISTANCE or _distance <= 0:
                    continue

                phi_deg2distance[_phi_deg] = _distance

                # auf den Mittelpunkt des Autos umrechnen
                # der Vektor zwischen Mittelpunkt des Autos und dem Servo-Kopf
                _vector_car_center2servo_head = np.array([0, LENGTH_VECTOR_A])

                # wir kennen die Länge und den Winkel des Vektors zwischen
                # Servo-Kopf und Ziel
                # Konvertierung von phi ins BogenmassDanke, Herr Niklas :)
                _rho = _distance
                _phi_rad = np.radians(_phi_deg)
                _vector_servo2target_x = round(_rho * np.cos(_phi_rad), 2)
                _vector_servo2target_y = round(_rho * np.sin(_phi_rad), 2)
                _vector_servo2target = np.array(
                    [_vector_servo2target_x, _vector_servo2target_y])
                _real_vector_distance2target = _vector_car_center2servo_head +\
                    _vector_servo2target
                _distance = np.linalg.norm(_real_vector_distance2target)

                phi_deg2distance_real[_phi_deg] = round(_distance, 2)

                _line = "Winkel " + str(_phi_deg) + ": gemessene " + str(
                    phi_deg2distance[_phi_deg]) + ' cm, Distanz zum Auto-\
                        Mittelpunkt ' + str(phi_deg2distance_real[_phi_deg])
                config.TR.logger.debug(_line)

            # Koordinaten berechnen
            for _phi in phi_deg2distance_real:
                _rho = phi_deg2distance_real[_phi]
                # Konvertierung von phi ins Bogenmass - Danke, Herr Niklas :)
                _phi_rad = np.radians(_phi)
                _x = round(_rho * np.cos(_phi_rad), 2)
                _y = round(_rho * np.sin(_phi_rad), 2)
                phi_deg2coords[_phi] = [_x, _y]

            # Gefundene Punkte den Planquadraten zuordnen
            for _point in phi_deg2coords:
                _x = phi_deg2coords[_point][0]
                _y = phi_deg2coords[_point][1]

                # print("_x: " + str(_x))
                # print("_y: " + str(_y))
                # print("x % _quadrant_size_x: " + str(math.floor(_x /
                # _quadrant_size_x)))
                # print("y % _quadrant_size_y: " + str(math.floor(_y /
                # _quadrant_size_y)))
                _quadrant_x = _quadrant_x_offset + \
                    math.floor(_x / _quadrant_size_x)
                _quadrant_y = _quadrant_y_offset + \
                    math.floor(_y / _quadrant_size_y)
                _key = str(_quadrant_x) + ',' + str(_quadrant_y)
                # print(_key)
                _quadrants[_key] = _quadrants[_key] + 1
            # print(_quadrants)

            # Karte zeichnen
            _logstr = "NEW_RUN" + "\n"
            for _x in range(_number_quadrants_x, -1, -1):
                for _y in range(_number_quadrants_y, -1, -1):
                    _key = str(_x) + "," + str(_y)
                    _logstr += _key + ": " + str(_quadrants[_key]) + "\n"
                    # print(str(_x) + "," + str(_y) + "|", end='')
                # print('\n', end='')
            _logfile = open("karte", "a")
            _logfile.write(_logstr)
            _logfile.close()

            # nächste Richtung festlegen, ggf. drehen und ein Stück vorwärts
            # fahren
            # ist der Quadrant vor uns frei?
            _key = str(_quadrant_x_offset) + "," + str(_quadrant_size_y + 1)
            if _quadrants[_key] == 0:
                _quadrant_y_offset += 1
                self.autopilot.move_distance_forward(30)
                # self.autopilot.move_forward()
                # time.sleep((3.5 / 100) * 30)
                # self.autopilot.stop_moving()
                continue

            # ist der Quadrant links von uns frei?
            _key = str(_quadrant_x_offset - 1) + "," + str(_quadrant_size_y)
            if _quadrants[_key] == 0:
                _quadrant_x_offset += -1
                print("würde nach links fahren")
                # self.autopilot.turn_degrees_left(90)
                # self.autopilot.stop_moving()
                continue

            # ist der Quadrant rechts von uns frei?
            _key = str(_quadrant_x_offset + 1) + "," + str(_quadrant_size_y)
            if _quadrants[_key] == 0:
                _quadrant_x_offset += 1
                print("würde nach rechts fahren")
                # self.autopilot.turn_degrees_left(90)
                # self.autopilot.stop_moving()
                continue

            # wenn vorne, links und rechts alles voll ist, müssen wir erstmal
            # umdrehen und weiterschauen
            print("würde mich umdrehen")
            # self.autopilot.turn_degrees_left(180)
