FROM public.ecr.aws/docker/library/openjdk:11-jdk
EXPOSE 8080
ADD target/iexstocks-1.0.0.jar iexinfo.jar
ENTRYPOINT ["java",  "-jar", "/iexinfo.jar"]