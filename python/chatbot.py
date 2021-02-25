from datetime import datetime
import random


# Methode zur Begrüßung
def greet():
    print("Hallo User!")


# Methode um die Benutzereingaben zu holen
def get_user_input():
    user_input: str = input("Was kann ich für Dich tun?\n")

    return user_input


# verfügbare Befehle
def main():
    commands = dict()
    commands["foo?"] = "bar!"
    commands["time"] = datetime.now().strftime("%H:%M:%S")
    commands["sag_hallo"] = "Hallo :)"

    program_runs = True
    user_input_empty = True
    greet()
    while program_runs:

        # Benutzereingabe abholen - darf nicht leer sein
        user_input = ""
        while user_input_empty:
            user_input = get_user_input()
            if user_input != "":
                user_input_empty = False

        # Benutzereingabe prüfen
        if user_input == "bye":
            program_runs = False
        else:
            words = user_input.split()

            # Prüfung, ob die Wörter bekannten Kommandos entsprechen
            for word in words:
                if word in commands.keys():
                    print(commands[word], '\n')
                else:
                    random_element = random.choice(list(commands.values()))
                    print(random_element)

            user_input_empty = True
    # Schleifenende

    print("Bye bye!")
    exit(0)


if __name__ == "__main__":
    main()
