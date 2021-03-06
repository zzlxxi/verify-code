FROM java:8-jre-alpine
RUN mkdir /verify_code
COPY ./target/verify-code.jar /verify_code
WORKDIR /verify_code
CMD ["java", "-jar", "verify-code.jar"]
EXPOSE 8080