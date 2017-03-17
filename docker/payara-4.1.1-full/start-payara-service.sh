###########################################
#
# Start domain via asadmin, create
# database-connection-pool and deploy
# webapplication. After script do sleep
# in a endless loop to keep service alive.
#
###########################################
#!/bin/bash
set -e

cd /usr/share/payara41/bin/

./asadmin --port 8080 start-domain

./asadmin change-admin-password

./asadmin enable-secure-admin

./asadmin restart-domain

while true
do
    sleep 10000
done
