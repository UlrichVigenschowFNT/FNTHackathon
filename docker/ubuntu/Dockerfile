From ubuntu:16.04

MAINTAINER Leon Varga <leon.varga.hdh@gmail.com>

RUN apt-get -y update \
  && apt-get -y upgrade\
  && apt-get -y install openjdk-8-jdk openjdk-8-demo \
  						openjdk-8-doc openjdk-8-jre-headless \
  						openjdk-8-source
  						
ENV JAVA_HOME /usr/lib/jvm/java-1.8.0-openjdk-adm64
ENV PATH $PATH:$JAVA_HOME/bin