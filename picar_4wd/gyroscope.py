import picar_4wd as fc
import smbus, time, threading, math

class Gyroscope():
    def __init__(self):
        self.sleep_time = 0.001
        self.power_mgmt_1 = 0x6b
        self.power_mgmt_2 = 0x6c
        self.bus = smbus.SMBus(1) # bus = smbus.SMBus(0) fuer Revision 1
        self.address = 0x68       # via i2cdetect
        self.bus.write_byte_data(self.address, 0x1C, 24) # soll die Auflösung des Sensors auf +/- 8G erhöhen
        self.bus.write_byte_data(self.address, self.power_mgmt_1, 0) # Aktivieren, um das Modul ansprechen zu koennen

    def read_byte(self, reg):
        return self.bus.read_byte_data(self.address, reg)
 
    def read_word(self, reg):
        h = self.bus.read_byte_data(self.address, reg)
        l = self.bus.read_byte_data(self.address, reg+1)
        value = (h << 8) + l
        return value
 
    def read_word_2c(self, reg):
        val = self.read_word(reg)
        if (val >= 0x8000):
            return -((65535 - val) + 1)
        else:
            return val
 
    def dist(self, a,b):
        return math.sqrt((a*a)+(b*b))
 
    def get_y_rotation(self, x,y,z):
        radians = math.atan2(x, self.dist(y,z))
        return -math.degrees(radians)
 
    def get_x_rotation(self, x,y,z):
        radians = math.atan2(y, self.dist(x,z))
        return math.degrees(radians)
 
    def read_values(self):
        fc.sensor_gyro_gx = self.read_word_2c(0x43)
        fc.sensor_gyro_gx_scaled = fc.sensor_gyro_gx / 131
        fc.sensor_gyro_gy = self.read_word_2c(0x45)
        fc.sensor_gyro_gy_scaled = fc.sensor_gyro_gy / 131
        fc.sensor_gyro_gz = self.read_word_2c(0x47)
        fc.sensor_gyro_gz_scaled = fc.sensor_gyro_gz / 131
        fc.sensor_gyro_ax = self.read_word_2c(0x3b)
        fc.sensor_gyro_ax_scaled = fc.sensor_gyro_ax / 16384.0
        fc.sensor_gyro_ay = self.read_word_2c(0x3d)
        fc.sensor_gyro_ay_scaled = fc.sensor_gyro_ay / 16384.0
        fc.sensor_gyro_az = self.read_word_2c(0x3f)
        fc.sensor_gyro_az_scaled = fc.sensor_gyro_az / 16384.0
        fc.sensor_gyro_temp = self.read_word_2c(0x41)
        fc.sensor_x_rotation = self.get_x_rotation(fc.sensor_gyro_ax_scaled, fc.sensor_gyro_ay_scaled, fc.sensor_gyro_az_scaled)
        fc.sensor_y_rotation = self.get_y_rotation(fc.sensor_gyro_ax_scaled, fc.sensor_gyro_ay_scaled, fc.sensor_gyro_az_scaled)
        #fc.sensor_z_rotation = self.get_z_rotation(fc.sensor_gyro_ax_scaled, fc.sensor_gyro_ay_scaled, fc.sensor_gyro_az_scaled)
         
        #print("gyroskop_xout: " + ("%5d" % gyroskop_xout) + " skaliert: " + str(gyroskop_xout / 131) + '\n')
        #print("gyroskop_yout: " + ("%5d" % gyroskop_yout) + " skaliert: " + str(gyroskop_yout / 131) + '\n')
        #print("gyroskop_zout: " + ("%5d" % gyroskop_zout) + " skaliert: " + str(gyroskop_zout / 131) + '\n')
         
 
        #beschleunigung_xout = self.read_word_2c(0x3b)
        #beschleunigung_yout = self.read_word_2c(0x3d)
        #beschleunigung_zout = self.read_word_2c(0x3f)
 
        #beschleunigung_xout_skaliert = beschleunigung_xout / 16384.0
        #beschleunigung_yout_skaliert = beschleunigung_yout / 16384.0
        #beschleunigung_zout_skaliert = beschleunigung_zout / 16384.0
 
        #print("beschleunigung_xout: " + str("%6d" % beschleunigung_xout) + " skaliert: " + str(beschleunigung_xout_skaliert) + '\n')
        #print("beschleunigung_yout: " + str("%6d" % beschleunigung_yout) + " skaliert: " + str(beschleunigung_yout_skaliert) + '\n')
        #print("beschleunigung_zout: " + str("%6d" % beschleunigung_zout) + " skaliert: " + str(beschleunigung_zout_skaliert) + '\n')
        #print("X Rotation: " + str(self.get_x_rotation(beschleunigung_xout_skaliert, beschleunigung_yout_skaliert, beschleunigung_zout_skaliert)) + '\n')
        #print("Y Rotation: " + str(self.get_y_rotation(beschleunigung_xout_skaliert, beschleunigung_yout_skaliert, beschleunigung_zout_skaliert)) + '\n')
        
    def __call__(self):
        return self

    #def deinit(self):
    #    self.timer_flag = False
    #    self.timer.join()
