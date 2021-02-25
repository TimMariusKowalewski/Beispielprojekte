import picar_4wd as fc
import threading, time
from picar_4wd.tracy import Tracy

# wird z.Zt. nicht benutzt
class Autopilot():
    def __init__(self, thread_name = "AutopilotThread"):
        self.timer_flag = True
        self.sleep_time = 0.01
        self.timer = threading.Thread(target=self.fun_timer, name=thread_name)
        self.drive_distance = False
        self.distance_to_go = 0
        self.distance_driven = 0
        self.tracy = Tracy()

    def start(self):
        self.timer.start()

    def fun_timer(self):
        while self.timer_flag:
            if self.drive_distance == True:
                while self.distance_driven <= self.distance_to_go:
                    self.distance_driven += fc.sensor_left_rear_speed * 0.1
                    self.tracy.x += self.distance_driven
                    time.sleep(0.001)
                    #print("%smm/s/10"%speed)
                #print("%smm"%x)
                #self.driven_distance += x
                self.stop()
                self.drive_distance = False
 
            time.sleep(self.sleep_time)

    def measure_distance(self, distance, direction):
        self.distance_driven = 0
        self.distance_to_go = distance
        self.go(direction)
        self.drive_distance = True

    def go(self, direction):
        if direction == 'forward':
            fc.left_front.set_power(fc.power_val_left_front_default)
            fc.left_rear.set_power(fc.power_val_left_rear_default)
            fc.right_front.set_power(fc.power_val_right_front_default)
            fc.right_rear.set_power(fc.power_val_right_rear_default)
        else:
            fc.left_front.set_power(-fc.power_val_left_front_default)
            fc.left_rear.set_power(-fc.power_val_left_rear_default)
            fc.right_front.set_power(-fc.power_val_right_front_default)
            fc.right_rear.set_power(-fc.power_val_right_rear_default)

    def stop(self):
        fc.left_front.set_power(0)
        fc.left_rear.set_power(0)
        fc.right_front.set_power(0)
        fc.right_rear.set_power(0)

    def deinit(self):
        self.timer_flag = False
        self.timer.join()