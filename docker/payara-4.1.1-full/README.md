# Payara 4.1.1 Docker-Image

## Build the image:

```
docker build -t hackathon-ubuntu-16.04-java-8-jdk-payara-4.1.1 .
```

## Run the image in container:

```
docker run -it -p 8080:8080 -p 4848:4848 --name payara hackathon-ubuntu-16.04-java-8-jdk-payara-4.1.1
```

### Payara admin-console

admin-name: admin
admin-default-password: empty-string