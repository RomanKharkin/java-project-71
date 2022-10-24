run-dist:
	./build/install/app/bin/app

install:
	./gradlew clean install

run-install-dist: install run-dist

run-install-mock:
	./gradlew clean install
	./build/install/app/bin/app "/home/roman/java-project-71/app/src/main/java/hexlet/code/file1.json" "/home/roman/java-project-71/app/src/main/java/hexlet/code/file2.json"



.PHONY: run-dist