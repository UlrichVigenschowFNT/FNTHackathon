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

./asadmin --port 8080 start-domain --debug

# create password files
echo 'AS_ADMIN_PASSWORD=' >> tmpfile
echo 'AS_ADMIN_NEWPASSWORD='$ADMIN_PASSWORD >> tmpfile
echo 'AS_ADMIN_PASSWORD='$ADMIN_PASSWORD >> pwdfile

./asadmin -u $ADMIN_USER --passwordfile tmpfile --interactive=false change-admin-password
./asadmin -u $ADMIN_USER --passwordfile pwdfile --interactive=false enable-secure-admin

# JDBC
./asadmin -u $ADMIN_USER --passwordfile pwdfile --interactive=false create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGConnectionPoolDataSource --restype javax.sql.ConnectionPoolDataSource --property portNumber=${DB_PORT}:password=${DB_PASSWORD}:user=${DB_USER}:serverName=${DB_SERVER_NAME}:databaseName=${DB_NAME} --ping true ${CONNECTION_POOLNAME}
./asadmin -u $ADMIN_USER --passwordfile pwdfile --interactive=false create-jdbc-resource --connectionpoolid ${CONNECTION_POOLNAME} ${CONNECTION_RESOURCE_NAME}

# JMS
./asadmin -u $ADMIN_USER --passwordfile pwdfile --interactive=false set server.jms-service.type=REMOTE
./asadmin -u $ADMIN_USER --passwordfile pwdfile --interactive=false delete-jms-host default_JMS_host
./asadmin -u $ADMIN_USER --passwordfile pwdfile --interactive=false create-jms-host --mqhost $MQHOST --mqport $MQPORT --mquser admin --mqpassword admin default_JMS_host

./asadmin -u $ADMIN_USER --passwordfile pwdfile --interactive=false restart-domain --debug=true

./asadmin -u $ADMIN_USER --passwordfile pwdfile --interactive=false deploy /webapps/*.war

while true
do
    sleep 1d
done