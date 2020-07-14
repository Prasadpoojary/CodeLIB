FROM openjdk:8
ADD target/codelib.war codelib.war
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "codelib.war"]