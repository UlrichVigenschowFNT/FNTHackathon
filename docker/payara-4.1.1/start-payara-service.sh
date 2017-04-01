#!/bin/bash
set -e

./asadmin --port 8080 start-domain

./asadmin deploy /webapps/*.war

# create temporary password files
echo 'AS_ADMIN_PASSWORD=' >> tmpfile
echo 'AS_ADMIN_NEWPASSWORD='$ADMIN_PASSWORD >> tmpfile
echo 'AS_ADMIN_PASSWORD='$ADMIN_PASSWORD >> pwdfile

./asadmin -u $ADMIN_USER --passwordfile tmpfile change-admin-password

./asadmin -u $ADMIN_USER --passwordfile pwdfile enable-secure-admin

# remove password files
rm tmpfile pwdfile

./asadmin restart-domain

while true
do
    sleep 1d
done