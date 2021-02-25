# stellt eine abtrakte Klasse oder Interface dar
# gibts beides nicht wirklich in Python
class Lebenwesen:
    def atmen(self):
        raise NotImplementedError("The method not implemented")


class Tier(Lebenwesen):
    anzahl_tiere = 0

    def __init(self, in_name: str):
        self.name = in_name
        Tier.anzahl_tiere += 1

    def atmen(self):
        pass

    def ton_machen(self, in_anzahl):
        pass

    # Destruktor
    def __del__(self):
        pass

    # Getter
    def get_name(self):
        return self.name

    # privater Setter
    def __set_name(self, name):
        self.name = name

    @classmethod
    def get_anzahl_tiere(cls):
        return Tier.anzahl_tiere


class Hund(Tier):
    def __init__(self, in_name: str):
        super().__init__(in_name)

    def ton_machen(self, in_anzahl):
        print(in_anzahl * "wau")


class Katze(Tier):

    # Konstruktor
    def __init__(self, in_name):
        super().__init__(in_name)
        # self.name = name

    def ton_machen(self, in_anzahl):
        print(in_anzahl * "miaz")


if __name__ == "__main__":
    katze1 = Katze("Katze1")
    print(katze1.get_name())
    katze1.ton_machen(3)

    hund1 = Hund("Hund1")
    print(hund1.get_name())
    hund1.ton_machen(3)

    Tier.get_anzahl_tiere()
