run-dist:
	./build/install/app/bin/app -h

install:
	./gradlew clean install

run-install-dist: install run-dist

run-install-mock-j:
	./gradlew clean install
	./build/install/app/bin/app "/home/roman/java-project-71/app/src/main/java/hexlet/code/file1.json" "/home/roman/java-project-71/app/src/main/java/hexlet/code/file2.json"

run-install-mock-y-p:
	./gradlew clean install
	./build/install/app/bin/app -f plain "/home/roman/java-project-71/app/src/main/java/hexlet/code/fileY1.yml" "/home/roman/java-project-71/app/src/main/java/hexlet/code/fileY2.yml"

run-install-mock-y:
	./gradlew clean install
	./build/install/app/bin/app "/home/roman/java-project-71/app/src/main/java/hexlet/code/fileY1.yml" "/home/roman/java-project-71/app/src/main/java/hexlet/code/fileY2.yml"

.PHONY: build