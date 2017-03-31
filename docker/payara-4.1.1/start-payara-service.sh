###########################################
#
# Start domain via asadmin, create a
# database-connection-pool, change admin
# password, enable remote admin console and
# deploy webapplication. After script do sleep
# in a endless loop to keep service alive.
#
###########################################
#!/bin/bash
set -e

./asadmin --port 8080 start-domain

./asadmin create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGConnectionPoolDataSource --restype javax.sql.ConnectionPoolDataSource --property portNumber=${DB_PORT}:password=${DB_PASSWORD}:user=${DB_USER}:serverName=${DB_SERVER_NAME}:databaseName=${DB_NAME} --ping true ${CONNECTION_POOLNAME}

./asadmin deploy /webapps/*.war

# create temporary password files
echo 'AS_ADMIN_PASSWORD=\n\AS_ADMIN_NEWPASSWORD='$ADMIN_PASSWORD'\nEOF\n' >> tmpfile
echo 'AS_ADMIN_PASSWORD='$ADMIN_PASSWORD'\nEOF\n' >> pwdfile

./asadmin -u $ADMIN_USER --passwordfile tmpfile change-admin-password
./asadmin -u $ADMIN_USER --passwordfile pwdfile enable-secure-admin

# JMS
./asadmin -u $ADMIN_USER --passwordfile pwdfile set server.jms-service.type=REMOTE
./asadmin -u $ADMIN_USER --passwordfile pwdfile delete-jms-host default_JMS_host
./asadmin -u $ADMIN_USER --passwordfile pwdfile create-jms-host --mqhost $MQHOST --mqport $MQPORT --mquser admin --mqpassword admin default_JMS_host

# remove password files
rm tmpfile pwdfile

./asadmin restart-domain

while true
do
    sleep 1d
done
