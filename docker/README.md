# Compose file for all containers

## Build the images

Put the catalog .war file in the "app" directory and build with:

```
docker build -t hackathon-ubuntu-16.04-java-8-jdk-payara-4.1.1-devobst_catalog .
```

Put the billing .war file int the "app" directory and build with:

```
docker build -t hackathon-ubuntu-16.04-java-8-jdk-payara-4.1.1-devobst_billing .
```

## Start services:

```
docker-compose up [-d]
```
