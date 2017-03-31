# OpenMQ Docker-Image

## Build the image:

```
docker build -t hackathon-ubuntu-16.04-java-8-jdk-openmq-5.1.1 .
```

## RUN:

```
docker run -p 7676:7676 -p 34000:34000 -p 43000:43000 -p 31000:31000 -p 36000:36000 -e TOPIC_NAME=devobst hackathon-ubuntu-16.04-java-8-jdk-openmq-5.1.1
```
