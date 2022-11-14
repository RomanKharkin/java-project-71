run-dist:
	./build/install/app/bin/app -h

install:
	./gradlew clean install

run-install-dist: install run-dist

run-install-mock-j-p:
	./gradlew clean install
	./build/install/app/bin/app -f plain "./src/main/resources/file1.json" "./src/main/resources/file2.json"

run-install-mock-j-s:
	./gradlew clean install
	./build/install/app/bin/app "./src/main/resources/file1.json" "./src/main/resources/file2.json"

run-install-mock-j-j:
	./gradlew clean install
	./build/install/app/bin/app -f json "./src/main/resources/file1.json" "./src/main/resources/file2.json"

run-install-mock-y-p:
	./gradlew clean install
	./build/install/app/bin/app -f plain "./src/main/resources/fileY1.yml" "./src/main/resources/fileY2.yml"

run-install-mock-y-s:
	./gradlew clean install
	./build/install/app/bin/app "./src/main/resources/fileY1.yml" "./src/main/resources/fileY2.yml"

run-install-mock-y-j:
	./gradlew clean install
	./build/install/app/bin/app -f json "./src/main/resources/fileY1.yml" "./src/main/resources/fileY2.yml"

.PHONY: build