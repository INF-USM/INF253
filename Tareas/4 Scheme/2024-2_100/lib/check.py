'''
Esto solo es un script que compara 2 strings y resalta sus diferencias con colores
'''
import sys;

good_color="\033[0;32m"
bad_color="\033[0;31m"
outb_color="\033[0;30m"
quit_color="\033[0m"

def no_ansi():
    global good_color
    global bad_color
    global outb_color
    global quit_color

    good_color=""
    bad_color=""
    outb_color=""
    quit_color=""


def log(out:str):
    print(out, end=quit_color);

def compare(exp:str, res:str):
    if exp == res:
        log(good_color + res);
    else:
        log(bad_color + res);

def main(expected:str, result:str):
    l_exp = len(expected);
    l_res = len(result);
    log("> " + outb_color + expected + "\n");
    log("> ");
    short = l_exp if l_exp <= l_res else l_res;
    for i in range(short):
        compare(expected[i], result[i]);
    if l_res > l_exp:
        for i in range(short, l_res):
            log(outb_color + result[i]);
        print("   <- Too large");
    elif l_res < l_exp:
        print("   <- Too short");
    else:
        print();


if __name__ == '__main__':
    if sys.argv[1] != '_': no_ansi();
    main(sys.argv[3], sys.argv[2]);