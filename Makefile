.DEFAULT_GOAL := build

clean:
	./gradlew clean

build: clean
	./gradlew build -x test

test:
	./gradlew test

lint:
	./gradlew checkstyleMain checkstyleTest

coverage:
	./gradlew test jacocoTestReport
	./gradlew jacocoTestCoverageVerification
