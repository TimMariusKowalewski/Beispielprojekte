from abc import ABC, abstractmethod
from CSV import CSV
from Console import ConsoleRenderer
from Util import MyObject
from datetime import datetime


class Fahrzeug(MyObject, ABC):
    COUNTER = 0
    NAME_SINGULAR = ""
    NAME_PLURAL = ""

    def __init__(self, in_max_speed: float, in_wheel_count: int,
                 in_drive):
        # Attribute der Klasse
        # Fahrzeug.COUNTER += 1
        super().__init__()
        self.max_speed = in_max_speed
        self.wheel_count = in_wheel_count
        self.drive = in_drive

    @abstractmethod
    def save(self):
        pass

    def update(self):
        pass

    def delete(self):
        self.deleted = 1
        self.deletion_date = datetime.now()

    @classmethod
    def list(cls):
        pass

    def get_max_speed(self):
        return self.max_speed

    # magic methods
    def __lt__(self, other):
        return self.max_speed < other.max_speed

    def __gt__(self, other):
        return self.max_speed > other.max_speed

    def __eq__(self, other):
        return self.max_speed == other.max_speed


class Auto(Fahrzeug):
    NAME_SINGULAR = "Auto"
    NAME_PLURAL = "Autos"
    CSV = CSV("Auto.csv")
    FIELD_TRANSLATIONS = {
        "id": "ID:",
        "creation_date": "Erstellt:",
        "wheel_count": "Anzahl Räder",
        "max_speed": "Max. Geschw.",
        "drive": "Antrieb"
    }

    def __init__(self, in_wheel_count, in_max_speed, in_drive):
        self.wheel_count = in_wheel_count
        self.max_speed = in_max_speed
        self.drive = Drive(in_drive)
        self.volume_storage = 123
        super().__init__(in_max_speed, 4, self.drive)

    @classmethod
    def create_view_constructor(cls, fields):
        auto = Auto(0, 0, 0)
        auto.wheel_count = fields['wheel_count']
        auto.max_speed = fields['max_speed']
        auto.drive = Drive(fields['drive']).to_string()
        return auto

    @classmethod
    def get_create_view_fields(cls):
        return {'wheel_count': 'int', 'max_speed': 'float', 'drive': 'str'}

    @classmethod
    def get_list_view_fields(cls):
        return ['id', 'creation_date', 'wheel_count', 'max_speed', 'drive']

    @classmethod
    def get_list_view_data(cls, offset, count):
        return_list = []

        for record in Auto.CSV.read(offset, count):
            auto = Auto(record[2], record[3], record[4])
            auto.id = record[0]
            auto.creation_date = record[1]
            auto.wheel_count = record[3]
            auto.drive = str(record[4])
            return_list.append(auto)

        return return_list

    @classmethod
    def get_object_count(cls):
        return Auto.CSV.count_records()

    @classmethod
    def get_create_view_data(cls):
        print("create view Auto")

    @classmethod
    def get_edit_view_data(cls):
        print("edit view Auto")

    @classmethod
    def get_detail_view_data(cls):
        print("detail view Auto")

    def save(self):
        record = [
            self.id, self.creation_date, self.max_speed, self.wheel_count,
            self.drive]
        Auto.CSV.write_dataset(record)

    def __str__(self):
        return "Ich bin Auto " + str(self.id)


class Fahrrad(Fahrzeug, ConsoleRenderer):
    NAME_SINGULAR = "Fahrrad"
    NAME_PLURAL = "Fahrräder"
    CSV = CSV("Fahrrad.csv")
    FIELD_TRANSLATIONS = {
        "id": "ID",
        "wheel_count": "Anzahl Räder",
        "max_speed": "Max. Geschw.",
        "drive": "Antrieb"
    }

    def __init__(self, in_wheel_count, in_max_speed, in_drive):
        self.wheel_count = in_wheel_count
        self.max_speed = in_max_speed
        self.drive = Drive(in_drive)
        super().__init__(in_max_speed, 2, self.drive)

    @classmethod
    def create_view_constructor(cls, fields):
        fahrrad = Fahrrad(0, 0, 0)
        fahrrad.wheel_count = fields['wheel_count']
        fahrrad.max_speed = fields['max_speed']
        fahrrad.drive = Drive(fields['drive']).to_string()
        return fahrrad

    @classmethod
    def get_create_view_fields(cls):
        return {'wheel_count': 'int', 'max_speed': 'float', 'drive': 'str'}

    @classmethod
    def get_list_view_fields(cls):
        return ['id', 'wheel_count', 'max_speed', 'drive']

    @classmethod
    def get_list_view_data(cls, offset, count):
        return_list = []

        for record in Fahrrad.CSV.read(offset, count):
            fahrrad = Fahrrad(record[1], record[2], record[3])
            fahrrad.id = record[0]
            fahrrad.wheel_count = record[2]
            fahrrad.drive = str(record[3])
            return_list.append(fahrrad)

        return return_list

    @classmethod
    def get_object_count(cls):
        return Fahrrad.CSV.count_records()

    @classmethod
    def get_create_view_data(cls):
        print("create view Fahrrad")

    @classmethod
    def get_edit_view_data(cls):
        print("edit view Fahrrad")

    @classmethod
    def get_detail_view_data(cls):
        print("detail view Fahrrad")

    def save(self):
        record = [
            self.id, self.max_speed, self.wheel_count, self.drive]
        Fahrrad.CSV.write_dataset(record)

    def __str__(self):
        return "Ich bin Fahrrad " + str(self.id)


class Mofa(Fahrzeug, ConsoleRenderer):
    NAME_SINGULAR = "Mofa"
    NAME_PLURAL = "Mofas"
    CSV = CSV("Mofa.csv")
    FIELD_TRANSLATIONS = {
        "id": "ID",
        "wheel_count": "Anzahl Räder",
        "max_speed": "Max. Geschw.",
        "drive": "Antrieb"
    }

    def __init__(self, in_wheel_count, in_max_speed, in_drive):
        self.wheel_count = in_wheel_count
        self.max_speed = in_max_speed
        self.drive = Drive(in_drive)
        super().__init__(in_max_speed, 2, self.drive)

    @classmethod
    def create_view_constructor(cls, fields):
        mofa = Mofa(0, 0, 0)
        mofa.wheel_count = fields['wheel_count']
        mofa.max_speed = fields['max_speed']
        mofa.drive = Drive(fields['drive']).to_string()
        return mofa

    @classmethod
    def get_create_view_fields(cls):
        return {'wheel_count': 'int', 'max_speed': 'float', 'drive': 'str'}

    @classmethod
    def get_list_view_fields(cls):
        return ['id', 'wheel_count', 'max_speed', 'drive']

    @classmethod
    def get_list_view_data(cls, offset, count):
        return_list = []

        for record in Mofa.CSV.read(offset, count):
            mofa = Mofa(record[1], record[2], record[3])
            mofa.id = record[0]
            mofa.wheel_count = record[2]
            mofa.drive = str(record[3])
            return_list.append(mofa)

        return return_list

    @classmethod
    def get_object_count(cls):
        return Mofa.CSV.count_records()

    @classmethod
    def get_create_view_data(cls):
        print("create view Mofa")

    @classmethod
    def get_edit_view_data(cls):
        print("edit view Mofa")

    @classmethod
    def get_detail_view_data(cls):
        print("detail view Mofa")

    def save(self):
        record = [
            self.id, self.max_speed, self.wheel_count, self.drive]
        Mofa.CSV.write_dataset(record)

    def __str__(self):
        return "Ich bin Mofa " + str(self.id)


class Drive:
    def __init__(self, in_type: str):
        self.type = in_type

    def to_string(self):
        return self.type
