FROM openjdk:11.0.14.1-jre-bullseye AS base
RUN mkdir /app
EXPOSE 8080

FROM openjdk:11.0.14.1-jdk-bullseye AS build-env
RUN mkdir /opt/mvn && mkdir /opt/source
WORKDIR /opt/mvn
RUN wget https://dlcdn.apache.org/maven/maven-3/3.8.5/binaries/apache-maven-3.8.5-bin.zip
RUN unzip -d . apache-maven-3.8.5-bin.zip && rm apache-maven-3.8.5-bin.zip
ENV PATH=$PATH:/opt/mvn/apache-maven-3.8.5/bin

FROM build-env AS build
COPY . /opt/source
WORKDIR /opt/source
RUN mvn package

FROM base AS app
COPY --from=build /opt/source/target /app
WORKDIR /app
ENTRYPOINT [ "java", "-jar", "RecipesMaven-0.0.1-SNAPSHOT.jar"]