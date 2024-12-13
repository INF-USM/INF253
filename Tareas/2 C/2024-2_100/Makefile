# DIR
BUILD_DIR = ./build
OBJ_DIR = ./build/obj
SRC_DIR = ./src

# FILES
SOURCES = CityDefender.c Tablero.c Cartas.c
OBJECTS = ${SOURCES:%.c=${OBJ_DIR}/%.o}

# COMP
CXX = gcc
CFLAGS = -c -I ${SRC_DIR} -Wall
LD = gcc

# EXE
EXE = ${BUILD_DIR}/CityDefender

# MAKE
default: ${EXE}

${EXE}: dirs ${OBJ_DIR}/CityDefender.o ${OBJ_DIR}/Tablero.o ${OBJ_DIR}/Cartas.o
	${LD} ${OBJECTS} -o ${EXE}

${OBJ_DIR}/%.o: ${SRC_DIR}/%.c
	${CXX} ${CFLAGS} -o $@ $<

dirs:
	@mkdir -p ${BUILD_DIR}
	@mkdir -p ${OBJ_DIR}