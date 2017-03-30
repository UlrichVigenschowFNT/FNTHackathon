# OpenMQ Docker-Image

## Build the image:

```
docker build --rm -t hackathon-openmq .
```

## Docker-Compose:

```
docker-compose up [-d]
```



#ARG1: domain name, e.g. assets
#ARG2: topic name, e.g. DomainAssets
#ARG3: Broker Port

# create a docker volume
docker volume create --name vol-mq-$1 -d local

docker run -d -p $3:7676 -v vol-mq-$1:/opt/openmq/MessageQueue5.1.1/var/mq/instances/imqbroker --name=mq-$1 -e TOPIC_NAME=$2 cmdnxg/openmq
