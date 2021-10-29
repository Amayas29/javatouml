FILENAME = ""

all:
	$(JAVA_HOME)/bin/javac  src/fr/javatouml/*/*.java -classpath bin:lib/plantuml.jar:lib/javaparser-core-3.23.0.jar -d bin 
	$(JAVA_HOME)/bin/java -Dfile.encoding=UTF-8 -classpath bin:lib/plantuml.jar:lib/javaparser-core-3.23.0.jar -XX:+ShowCodeDetailsInExceptionMessages fr.javatouml.main.JavaToUmlMain $(FILENAME)