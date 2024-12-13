'''
Esto solo es un script para verificar que los test terminen con un solo salto de linea
'''
import sys

def read_clean(dir:str) -> str:
    file = open(dir, 'r');
    clean_data = file.read().strip();
    file.close();
    return clean_data + '\n';

def write_on(dir:str, data:str):
    file = open(dir, 'w');
    file.write(data);
    file.close();

def main(file_names:list[str], root_dir):
    for file_name in file_names:
        dir = f"{root_dir}/tests/{file_name}";
        clean_data = read_clean(dir);
        write_on(dir, clean_data);


if __name__ == '__main__':
    main(sys.argv[2:], sys.argv[1]);
