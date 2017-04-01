#!/bin/bash
set -e

./asadmin --port 8080 start-domain
./asadmin change-admin-password
./asadmin enable-secure-admin
./asadmin restart-domain

while true
do
  sleep 10000
done