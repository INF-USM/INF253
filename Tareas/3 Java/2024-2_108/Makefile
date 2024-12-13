MAINCLASS = NoJavaSky
NAME = Ejecutable

CLASS_DIR = ./class

JAR = ${NAME}.jar
CLASS = $(shell cd ${CLASS_DIR} && find . -name '*.class')

default: ${JAR} clean

${JAR}: dirs compile
	@cd ${CLASS_DIR} && jar cfe ../${JAR} ${MAINCLASS} ${CLASS}

compile:
	javac -d ${CLASS_DIR} *.java

dirs:
	@mkdir -p ${CLASS_DIR}

clean:
	@rm -rf ${CLASS_DIR}