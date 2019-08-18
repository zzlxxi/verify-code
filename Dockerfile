FROM openjdk:8
COPY ./target/verify-code.jar /verify_code
WORKDIR /verify_code
RUN java -jar verify-code.jar