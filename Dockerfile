FROM maven:3-jdk-8

WORKDIR /code
ADD pom.xml /code/pom.xml  
RUN mvn dependency:resolve verify
# Adding source, compile and package into a fat jar
ADD src /code/src  
RUN mvn clean package

EXPOSE 8080  
CMD java -jar target/RestZUP-0.0.1-SNAPSHOT.war  
