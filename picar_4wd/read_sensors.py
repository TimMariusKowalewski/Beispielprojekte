import utils
import time
from datetime import datetime
from influxdb import InfluxDBClient
client = InfluxDBClient(host='localhost', port=8086)
client.switch_database('sensors')

def writeIntoDB():
    try:
        while True:
            valueVoltage = utils.power_read()            
            cpu_temperature = utils.cpu_temperature()
            gpu_temperature = utils.gpu_temperature()
            json_body_cpu = [
            {
                    "measurement": "hardware",
                    "fields": {
                    "volt": str(valueVoltage),
                    "cpu_temperature": str(cpu_temperature),
                    "gpu_temperature": str(gpu_temperature)                    
                    }
                }
            ]
            print("Voltage: " + str(valueVoltage))
            print("CPU Temperature: " + str(cpu_temperature))
            print("CPU Temperature: " + str(gpu_temperature))
            client.write_points(json_body_cpu)
            print(timeNow() + " Daten zur Datenbank gesendet.\n")
            time.sleep(1)
    except RuntimeError:
        print("Etwas ist schief gelaufen, Programm wird zerstört")
        destroy()
            
        
def timeNow():
    now = datetime.now()
    current_time = now.strftime("%H:%M:%S")
    return current_time
    
        
def destroy():
    pass
        
if __name__=='__main__':
    try:
        writeIntoDB()
    except KeyboardInterrupt:
        print("Programm beendet.")
        destroy()