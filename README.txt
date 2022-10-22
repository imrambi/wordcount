Requirements need to run:

JDK 8 or greater
Maven
lombok - For importing the project into the IDE
SoapUI - Test end point

If you do not have JDK 8 or greater installed, you can download the JDK installer from
https://www.oracle.com/java/technologies/downloads/

Once a JDK is installed, you will need to have a running version of Maven. The application was built
using Maven 3.2.3

You can download maven from: https://maven.apache.org/download.cgi

Once downloaded, you will need to add the maven bin directory to your path. You can do this in the control
panel in windows, or appending it to your PATH variable in a console window.

If you are importing the project into an IDE, you will need to install lombok.

Eclipse: https://projectlombok.org/setup/eclipse
IntelliJ: https://projectlombok.org/setup/intellij

Compiling the application:

Once you have a JDK and maven installed, you can then compile the program

Once you have download the sources, cd to the wordcount directory.

Compiling and running junit test:
>mvn install

If you want to skip the test:
>mvn -DskipTests install

To run the endpoint locally:
> cd wordCountWeb
> mvn spring-boot:run

Once you see the following message the endpoint will be available locally:
"Completed initialization in XXXX ms"

The endpoint will be available at http://localhost:8080/api/words/count

If you don't have SOAPUI, you can download it at:
https://www.soapui.org/downloads/soapui/

In the wordcount/WordCountWeb/src/test/resources there is a SOAPUI project to import.
Once imported, you can then test the end point. There are two example payloads.
"Hello World" and "Invalid word".
