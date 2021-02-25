#from Util import Logger, my_log_decorator
from Fahrzeug import *
from Console import *
from itertools import permutations

DEBUG = True

if __name__ == "__main__":

    @my_log_decorator
    def fibonacci(n):
        last = 1
        beforelast = 0

        print(beforelast)
        print(last)
        for i in range(2, n + 1):
            new = last + beforelast
            print(new)
            beforelast = last
            last = new

        return "foo"

    _ = fibonacci(50)
    #print(_)
    exit(0)

    Logger = Logger("test.log")
    Logger.log("main.py", "asd")
    Console = Console()
    Modules = [Auto, Mofa, Fahrrad]
    Controller = ConsoleController(Modules, Console, Logger)
    Controller.run()
