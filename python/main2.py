import Util


class A:
    @Util.my_profile
    def __init__(self, in_x=0, in_y=0):
        self.x = in_x
        self.y = in_y

    @property
    def x(self):
        # print("returning x...")
        return self.__x

    @x.setter
    def x(self, in_x):
        # print("setting x...")
        self.__x = in_x

    @property
    def y(self):
        # print("returning y...")
        return self.__y

    @y.setter
    def y(self, in_y):
        # print("setting y...")
        self.__y = in_y


a = A(23, 42)
# print(a)
# a.y = 33
"""a = A(1, 2)
print(a.x)
print(a.y)
a.y = 123
print(a.y)"""
