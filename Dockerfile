FROM openjdk:11
ARG WAR_FILE=target/*.war
COPY ${WAR_FILE} ecommerce-springmvc.war
ENTRYPOINT ["java", "war", "/ecommerce-springmvc.war"]
EXPOSE 8084