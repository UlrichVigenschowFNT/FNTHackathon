# Payara 4.1.1 Docker-Image

## Build the image:

```
docker build -t hackathon-ubuntu-16.04-java-8-jdk-payara-4.1.1 .
```

## Run the image in container:

```
docker run -p 8080:8080 -p 4848:4848 -p 9009:9009 -e "ADMIN_USER=admin" -e "ADMIN_PASSWORD=password" --name payara hackathon-ubuntu-16.04-java-8-jdk-payara-4.1.1
```
