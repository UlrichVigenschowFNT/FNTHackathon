###########################################
#
# Start domain via asadmin, change admin 
# password and enable secure remote
# admin-console. After script do sleep
# in a endless loop to keep service alive.
#
###########################################
#!/bin/bash
./asadmin --port 8080 start-domain

./asadmin change-admin-password

./asadmin enable-secure-admin

./asadmin restart-domain

while true
do
    sleep 10000
done