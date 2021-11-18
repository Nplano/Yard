JAVAFILES= *.java

.PHONY: clean

compile:
	javac $(JAVAFILES)

run:
	java -ea BasicTestYard
	java -ea TestYard

clean:
	rm -rf *.class
