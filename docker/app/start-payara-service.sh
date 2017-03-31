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


./asadmin deploy /webapps/*.war

# create temporary password files
echo 'AS_ADMIN_PASSWORD=' >> tmpfile
echo 'AS_ADMIN_NEWPASSWORD='$ADMIN_PASSWORD >> tmpfile
echo 'AS_ADMIN_PASSWORD='$ADMIN_PASSWORD >> pwdfile

./asadmin -u $ADMIN_USER --passwordfile tmpfile --interactive=false change-admin-password
./asadmin -u $ADMIN_USER --passwordfile pwdfile --interactive=false enable-secure-admin

# JDBC
./asadmin -u $ADMIN_USER --passwordfile pwdfile --interactive=false create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGConnectionPoolDataSource --restype javax.sql.ConnectionPoolDataSource --property portNumber=${DB_PORT}:password=${DB_PASSWORD}:user=${DB_USER}:serverName=${DB_SERVER_NAME}:databaseName=${DB_NAME} --ping true ${CONNECTION_POOLNAME}

# remove password files
rm tmpfile pwdfile

./asadmin restart-domain

while true
do
    sleep 1d
done
