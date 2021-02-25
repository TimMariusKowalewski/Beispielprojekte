from Util import DataAdapter


class CSV(DataAdapter):
    def __init__(self, in_filename):
        super().__init__()
        self.file_name = in_filename
        self.file_handle = None

    def __del__(self):
        if self.file_handle is not None:
            self.file_handle.close()

    def count_records(self):
        self.file_handle = open(self.file_name, 'r')
        file_content = self.file_handle.readlines()

        return len(file_content)

    def read(self, offset=0, count=10):
        self.file_handle = open(self.file_name, 'r')
        file_content = self.file_handle.readlines()

        count += offset
        line_counter = 0
        data = []
        for line in file_content:
            if offset > line_counter:
                line_counter += 1
                continue
            if line_counter == count:
                break
            else:
                line_counter += 1

            fields = line.split(",")
            fields_new = []

            for field in fields:
                tmp = str(field).strip()
                tmp = tmp.replace('"', '')
                fields_new.append(tmp)
            data.append(fields_new)

        return data

    def save(self):
        pass

    def write_dataset(self, in_data):
        self.file_handle = open(self.file_name, 'a+')
        # print(in_data)
        csv_str = ''
        # print(csv_str)

        first = True
        for element in in_data:
            if not first:
                csv_str += ','
            else:
                first = False

            csv_str += '"' + str(element) + '"'

        csv_str += '\n'
        # print(csv_str)
        self.file_handle.write(csv_str)

# class DataAdapter(ABC):
#    @abstractmethod
#    def set_adapter(self, in_csv: CSV):
#        pass
