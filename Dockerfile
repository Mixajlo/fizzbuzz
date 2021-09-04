FROM openjdk:16
EXPOSE 8081

RUN mkdir -p /usr/local/src/fizzbuzz
ADD . /usr/local/src/fizzbuzz
RUN cd /usr/local/src/fizzbuzz && ./mvnw clean package
RUN mv /usr/local/src/fizzbuzz/target/*.jar /fizzbuzz.jar
RUN rm -rf /usr/local/src/fizzbuzz
CMD java -jar /fizzbuzz.jar
