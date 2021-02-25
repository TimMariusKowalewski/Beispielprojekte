import uuid
from datetime import datetime
from abc import ABC


def var_dump(in_var):
    print(id(in_var))
    # TODO: Ausgabe komplexer Typen
    print(type(in_var))
    print(in_var)


def explode(sequence, delimiter=""):
    output = ""

    element_max_count = len(sequence) - 1
    element_current_count = 0
    if type(sequence) is str or type(sequence) is list:
        for element in sequence:
            output += str(element)
            if element_current_count < element_max_count:
                output += delimiter
            element_current_count += 1
    elif type(sequence) is dict:
        for element in sequence.values():
            output += str(element)
            if element_current_count < element_max_count:
                output += delimiter
            element_current_count += 1
    else:
        raise Exception("explode(): unbekannter Datentyp " + type(sequence))

    return output


class Logger:
    def __init__(self, in_filename):
        self.filename = in_filename
        self.filehandle = open(self.filename, "a")

    def log(self, in_target, in_msg):
        _ = str(datetime.now()) + " - " + in_target + ": " + in_msg + "\n"
        self.filehandle.write(_)


# debug-Decorator
def my_debug1(func):
    print(datetime.now(), "calling", func)

    def wrapper(*args, **kwargs):
        print("args:", args)
        print("kwargs:", kwargs)
        _ = func(*args, **kwargs)
        print("return value:", _)

        return _

    return wrapper


def check_empty(attr):
    def check_method(function):
        def wrapped(cls, **kwargs):
            if kwargs.get(attr) is None:
                raise Exception('{item} cannot be empty'.format(item=attr))
            return function(cls, **kwargs)

        return wrapped

    return check_method


# log-Decorator
def my_log_decorator(func):
    def wrapper(*args, **kwargs):
        print("Something is happening before the function is called.")
        _ = func(*args, **kwargs)
        print("Something is happening after the function is called.")

        return _

    return wrapper


# cache-Decorator


# profiling-Decorator
def my_profile(func):
    begin = datetime.now()

    def wrapper(*args, **kwargs):
        func(*args, **kwargs)

    end = datetime.now()
    print("profiling", func, "took", begin - end)
    return wrapper


# Meta-Basisklasse für alle Objekte unseres Systems
class MyObject(ABC):
    def __init__(
            self,
            in_id=uuid.uuid4(),  # ID des Objekts
            in_creation_date=datetime.now(),  # Erstellungsdatum
            in_last_modification_date=datetime.now(),
            # Datum der letzen Änderung
            in_deleted: bool = False,  # Flag für "gelöscht"
            in_deletion_date=None  # Datum der Löschung
    ):
        self.id = in_id
        self.creation_date = in_creation_date
        self.last_modification_date = in_last_modification_date
        self.deleted = in_deleted
        self.deletion_date = in_deletion_date

    # dient dem Wiederherstellen von gespeicherten Werten
    @classmethod
    def constructor1(
            cls,
            in_id,
            in_creation_date,
            in_last_modification_date,
            in_deleted,
            in_deletion_date
    ):
        obj = MyObject()
        obj.id = in_id
        obj.creation_date = in_creation_date
        obj.last_modification_date = in_last_modification_date
        obj.deleted = in_deleted
        obj.deletion_date = in_deletion_date

        return obj

    @classmethod
    def create(cls):
        return cls()

    @classmethod
    def load(cls):
        pass


class MyObjectList:
    def __init__(self):
        pass

    @staticmethod
    def create():
        pass


class DataAdapter:
    def __init__(self):
        pass

    def read_one(self):
        pass

    def read_all(self):
        pass

    def write_one(self):
        pass

    def write_all(self):
        pass

    def delete_one(self):
        pass

    def delete_all(self):
        pass
