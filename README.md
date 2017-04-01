# FNTHackathon
FNT Hackathon - 01-APR-2017

## Build and run docker container

- docker build -t hackathon-ubuntu-16.04-java-8-jdk .
- docker run -it --name ubuntu ubuntu:16.04 /bin/bash

- docker build -t hackathon-ubuntu-16.04-java-8-jdk-payara-4.1.1 .
- docker run -it -p 8080:8080 -p 4848:4848 --name payara hackathon-ubuntu-16.04-java-8-jdk-payara-4.1.1

- docker build -t hackathon-ubuntu-16.04-java-8-jdk-payara-4.1.1-app .