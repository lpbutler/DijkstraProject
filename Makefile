runFrontendDeveloperTests: compFrontendDeveloperTests
	java --module-path javafx-sdk-19/lib/ --add-modules javafx.controls --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED -jar junit5.jar -cp .:JavaFXTester.jar --scan-classpath

compFrontendDeveloperTests:
	javac --module-path javafx-sdk-19/lib/ --add-modules javafx.controls -cp .:junit5.jar:JavaFXTester.jar FrontendDeveloperTests.java

runApp: compApp
	java -cp .:javafx-sdk-19/lib/* --module-path javafx-sdk-19/lib/ --add-modules javafx.controls,javafx.fxml GraphicalPages

compApp: GraphicalPages.java
	javac -cp javafx-sdk-19/lib/*:. GraphicalPages.java

compApp2: GraphicalPages.java
	javac --module-path javafx-sdk-19/lib/ --add-modules javafx.controls,javafx.fxml GraphicalPages.java

runApp2: compApp2
	java --module-path javafx-sdk-19/lib/ --add-modules javafx.controls,javafx.fxml GraphicalPages

clean:
	rm *.class
