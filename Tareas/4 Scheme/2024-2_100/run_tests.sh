#
#   Esto solo es un script que ejecuta las pruebas y compara los resultados
#
if [ -z $1 ]; then
    echo -e "\033[0;31m\tInput is needed\033[0m"
    exit 1
fi

if [ -z $ANSI ]; then
    BLUE="\033[0;34m"
    MAGENTA="\033[0;35m"
    NOCOLOR="\033[0m"
fi

python -u "./lib/truncate.py" $(pwd) $(cd ./tests && ls -d *.rkt.test)

for file in $(cd ./tests && ls -d P$1*.rkt.test);do
    src="P${1//.*/}.rkt"
    echo -e "$BLUE====================${src}====================$NOCOLOR"
    while IFS= read -r EJ;do
        IFS=";" read -r -a PAIR <<< "$EJ"
        echo -e "Running $MAGENTA${PAIR[0]}$NOCOLOR"
        RESULT=$(racket -e "(require \"$src\") ${PAIR[0]}")
        python -u "./lib/check.py" "_$ANSI" "${RESULT}" "${PAIR[@]:1}"
    done < "./tests/$file"
done