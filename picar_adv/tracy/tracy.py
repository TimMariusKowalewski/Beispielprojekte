# Tracy braucht den Autopiloten, um Bewegungen ausführen zu können
class Tracy:
    # Konstruktor
    def __init__(self):
        self.__x = 0.0
        self.__y = 0.0
        self.__compass = 0.0

    # Properties
    def getCompass(self):
        return self.__compass

    # zeigt die relative Ausrichtung zum Startpunkt
    def setCompass(self, Degree):
        new_compass_value = self.getCompass() + Degree
        if new_compass_value > 90 or new_compass_value < -90:
            new_compass_value = 0
        self.__compass = new_compass_value

    def setX(self, X):
        self.__x = X

    def getX(self):
        return self.__x

    def setY(self, Y):
        self.__y = Y

    def getY(self):
        return self.__y
