from abc import ABC, abstractmethod
from os import system, get_terminal_size
from Util import explode
from colors import green, blue, bold, underline


class ConsoleController:
    def __init__(self, in_modules, in_console, in_logger):
        self.modules = in_modules
        self.console = in_console
        self.logger = in_logger
        self.current_level = 0
        self.current_page = 0
        self.page_size = 10
        self.data_count = 0
        self.last_key = ""
        self.active_menu = None
        self.previous_active_menu = None
        self.active_module = None
        self.breadcrumbs = [None, None, None]

        # Menu-Einträge generieren
        top_level_menu_items = []

        i = 0
        for module in self.modules:
            i += 1
            key = i
            name = "Modul " + '"' + module.NAME_PLURAL + '"'
            target = ConsoleMenuView(module).show
            top_level_menu_items.append(MenuItem(name, target, key, True))

        top_level_menu_items.append(MenuItem("Abbrechen", self.abort, "q"))
        self.top_level_menu = Menu("Hauptmenü", top_level_menu_items,
                                   self.breadcrumbs)
        self.active_menu = self.top_level_menu

    def show_menu(self):
        menu = None
        if self.current_level == 0:
            menu = self.top_level_menu.generate_menu()
            self.active_menu = self.top_level_menu
        elif self.current_level == 1:
            menu_items = []
            item = MenuItem("Datensätze anzeigen",
                            ConsoleListView(self.active_module,
                                            self.current_page).show, "1", True)
            menu_items.append(item)
            item = MenuItem("Neuen Datensatz anlegen",
                            ConsoleCreateView(self.active_module).show, "2",
                            True)
            menu_items.append(item)
            item = MenuItem("zurück", self.jump_menu_up, "q")
            menu_items.append(item)
            menu = Menu("Modul " + '"' + self.active_module.NAME_PLURAL + '"',
                        menu_items, self.breadcrumbs)
            self.breadcrumbs[0] = "Hauptmenu"
            self.previous_active_menu = self.active_menu
            self.active_menu = menu
            menu = menu.generate_menu()
        else:
            menu_items = []
            item = MenuItem("nächste Seite", self.next_page, "n")
            menu_items.append(item)
            item = MenuItem("vorherige Seite", self.previous_page, "p")
            menu_items.append(item)
            item = MenuItem("zurück", self.jump_menu_up, "q")
            menu_items.append(item)
            self.breadcrumbs[
                1] = "Modul " + '"' + self.active_module.NAME_PLURAL + '"'
            menu = Menu("Listenansicht", menu_items, self.breadcrumbs)
            self.previous_active_menu = self.active_menu
            self.active_menu = menu
            menu = menu.generate_menu()
        Console.clear()
        Console.OUTPUT_BUFFER = menu + Console.OUTPUT_BUFFER
        Console.flush()

        return self.console.wait_for_input(
            self.active_menu.allowed_menu_values)

    def jump_to_top_level_menu(self):
        self.console.clear()
        self.current_level = 0
        self.active_menu = self.top_level_menu
        self.breadcrumbs = [None, None, None]
        return self.active_module

    def jump_menu_up(self):
        self.breadcrumbs[self.current_level - 1] = None

        self.console.clear()
        self.current_level -= 1
        self.active_menu = self.previous_active_menu

        return self.active_module

    def next_page(self):
        self.console.clear()
        if self.current_page < (self.data_count // self.page_size):
            self.current_page += 1
        self.console.output(
            ConsoleListView(self.active_module, self.current_page,
                            self.page_size, self.data_count).show())

        return self.active_module

    def previous_page(self):
        self.console.clear()
        if self.current_page >= 1:
            self.current_page -= 1
        self.console.output(
            ConsoleListView(self.active_module, self.current_page,
                            self.page_size, self.data_count).show())

        return self.active_module

    def run(self):
        self.console.clear()
        # loopt so lange bis man Abbrechen drückt
        while True:
            # aktuelle Auswahl abrufen
            selected_menu_item = self.show_menu()
            self.last_key = selected_menu_item

            if self.current_level == 0:
                for menu_item in self.active_menu.menu_items:
                    if menu_item.key == selected_menu_item:
                        # Methoden-Zeiger ausführen
                        if menu_item.is_submenu:
                            self.current_level = 1
                        self.active_module = menu_item.target()
                        self.data_count = self.active_module.get_object_count()
            elif self.current_level == 1:
                for menu_item in self.active_menu.menu_items:
                    if menu_item.key == selected_menu_item:
                        # Methoden-Zeiger ausführen
                        if menu_item.is_submenu:
                            self.current_level = 2
                        self.active_module = menu_item.target()
                        self.data_count = self.active_module.get_object_count()
            else:
                for menu_item in self.active_menu.menu_items:
                    if menu_item.key == selected_menu_item:
                        # Methoden-Zeiger ausführen
                        self.active_module = menu_item.target()
                        self.data_count = self.active_module.get_object_count()

    def abort(self):
        self.console.output("Bye!")
        exit(0)


class Menu:
    def __init__(self, in_title, in_items, in_breadcrumbs):
        self.title = in_title
        self.menu_items = in_items
        self.breadcrumbs = in_breadcrumbs
        self.allowed_menu_values = []

    def generate_menu(self):
        output = "\n"
        for breadcrumb in self.breadcrumbs:
            if breadcrumb is not None:
                output += breadcrumb + " -> "

        output += bold(self.title) + ": \n"
        output += "============================================\n\n"
        for item in self.menu_items:
            output += "(" + item.key + ") " + str(
                item.name) + "\n"
            self.allowed_menu_values.append(item.key)

        # output += "\n" + "letzte Auswahl: " + self.last_key

        return output


class MenuItem:
    def __init__(self, in_name, in_target, in_key, in_is_submenu=False):
        self.name = in_name
        self.target = in_target
        self.key = str(in_key)
        self.is_submenu = in_is_submenu


class Console:
    OUTPUT_BUFFER = ""

    def __init__(self):
        pass

    @staticmethod
    def clear():
        system("cls")

    @staticmethod
    def flush():
        Console.output(Console.OUTPUT_BUFFER)
        Console.OUTPUT_BUFFER = ""

    @staticmethod
    def output(in_output):
        print(in_output)

    @staticmethod
    def wait_for_input(allowed_values):
        my_input = None
        no_input = True
        while no_input:
            my_input = input("Auswahl: ")

            if len(allowed_values) > 0:
                if my_input in allowed_values:
                    no_input = False

        return my_input

    @staticmethod
    def print_input_field(text: str, empty_allowed: bool):
        my_input = None
        no_input = True
        while no_input:
            my_input = input(text + ": ")

            if not empty_allowed:
                if my_input != "":
                    no_input = False
            else:
                no_input = False

        return my_input


class ConsoleView:
    def __init__(self):
        term = get_terminal_size()
        self.terminal_width = term.columns
        self.terminal_height = term.lines


class ConsoleMenuView(ConsoleView):
    def __init__(self, in_associated_class):
        super().__init__()
        self.associated_class = in_associated_class

    def show(self):
        Console.clear()
        return self.associated_class


# Listenansicht der Konsole
class ConsoleListView(ConsoleView):

    def __init__(self, in_associated_class, in_page=0, in_page_size=10,
                 in_data_count=0):
        super().__init__()
        self.associated_class = in_associated_class
        self.current_page = in_page
        self.page_size = in_page_size
        self.data_count = in_data_count

    def show(self):
        fields = self.associated_class.get_list_view_fields()
        data = self.associated_class.get_list_view_data(
            self.current_page * self.page_size, self.page_size)
        data.sort(reverse=True)
        self.data_count = self.associated_class.get_object_count()

        column_size = 20
        field_count = len(fields)

        fields_formatted = []
        for field in fields:
            field = self.associated_class.FIELD_TRANSLATIONS[field]
            fields_formatted.append(
                bold(green(str(field).center(column_size, ' '))))

        # output_str = underline(
        #    "Listenansicht für " + self.associated_class.NAME_PLURAL + ":\n")
        output_str = "\n" + blue("|") + blue(
            ((column_size * field_count) + 12) * "-") + blue("|") + "\n"
        output_str += blue("|") + explode(fields_formatted, blue(" | ")) + blue(
            "|") + "\n"
        output_str += blue("|") + blue(
            ((column_size * field_count) + 12) * "-") + blue("|") + "\n"

        # tmp = None
        # first_field = True
        for data_object in data:
            # tmp = "|"
            first_field = True
            for field in fields:
                tmp = str(getattr(data_object, field))

                if len(tmp) > column_size - 3:
                    tmp = tmp[:column_size - 3] + "..."
                tmp = tmp.rjust(column_size, ' ')
                if first_field:
                    tmp = blue("|") + tmp
                    first_field = False
                output_str += tmp + blue(" | ")
            output_str += "\n"

        output_str += blue("|") + blue(
            ((column_size * field_count) + 12) * "-") + blue("|") + "\n"

        total_count = self.current_page * self.page_size + self.page_size
        if total_count > self.data_count:
            total_count = self.data_count

        sum_line = "Datensätze " + str(
            self.current_page * self.page_size + 1) + " bis " + str(
            total_count) + " von insgesamt " + str(
            self.data_count)
        output_str += bold(sum_line) + "\n"
        # Console.clear()
        Console.OUTPUT_BUFFER += output_str

        return self.associated_class


# Neuanlegen-Ansicht der Konsole
class ConsoleCreateView:
    def __init__(self, in_associated_class):
        super().__init__()
        self.associated_class = in_associated_class

    def show(self):
        Console.clear()
        output_str = underline(
            self.associated_class.NAME_SINGULAR + " neu anlegen:\n")
        Console.output(output_str)

        input_values = {}
        asd = []
        fields = self.associated_class.get_create_view_fields()
        for field in fields:
            ret = Console.print_input_field(
                str(self.associated_class.FIELD_TRANSLATIONS[field] + " (" + fields[field] + ")"), False)

            # Typ korrigieren
            if fields[field] == "int":
                ret = int(ret)
            elif fields[field] == "float":
                ret = float(ret)
            elif fields[field] == "str":
                ret = str(ret)

            input_values[str(field)] = ret
            asd.append(ret)

        # neues Objekt anlegen
        # constructor_fields = input_values['max_speed']
        # new_object = self.associated_class([input_values.values()])
        new_object = self.associated_class.create_view_constructor(input_values)
        # for value in input_values:
        #     setattr(new_object, value, input_values[value])
        new_object.save()
        # print(new_object)
        return self.associated_class


# Interface für Standardansichten auf der Konsole
class ConsoleRenderer(ABC):
    def __init__(self):
        pass

    @abstractmethod
    def get_list_view_fields(self):
        pass

    @abstractmethod
    def get_list_view_data(self, offset, count):
        pass

    @abstractmethod
    def get_create_view_fields(self):
        pass

    @abstractmethod
    def get_create_view_data(self):
        pass

    @abstractmethod
    def get_edit_view_data(self):
        pass

    @abstractmethod
    def get_detail_view_data(self):
        pass
