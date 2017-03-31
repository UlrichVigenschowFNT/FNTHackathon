#! /usr/bin/env bash

export TOPIC_NAME=${TOPIC_NAME}

# start broker
${OPENMQ_BIN}/imqbrokerd &

# FIXME: wait for broker deamon background process to be started successfully
sleep 4

# create topic (note: "tee" for verbose output)
${OPENMQ_BIN}/imqcmd create dst -t t -n ${TOPIC_NAME} -b localhost:7676 -u admin -passfile ${OPENMQ_LOCATION}/password.txt -f | tee 

# list destinations (note: "tee" for verbose output)
${OPENMQ_BIN}/imqcmd list dst -b :7676 -u admin -passfile ${OPENMQ_LOCATION}/password.txt -f | tee

# restart broker in foreground
${OPENMQ_BIN}/imqcmd shutdown bkr -u admin -passfile ${OPENMQ_LOCATION}/password.txt -f
${OPENMQ_BIN}/imqbrokerd \
    -port 7676 \
    -startRmiRegistry \
    -rmiRegistryPort 34000 \
    -Dimq.jmx.connector.jmxrmi.port=31000 \
    -Dimq.jmx.connector.ssljmxrmi.port=32000 \
    -Dimq.jmx.rmiregistry.port=34000 \
    -Dimq.portmapper.port=7676 \
    -Dimq.admin.tcp.port=36000 \
    -Dimq.cluster.port=37000 \
    -Dimq.cluster_discovery.port=38000 \
    -Dimq.cluster.heartbeat.port=39000 \
    -Dimq.httpjms.http.servletPort=40000 \
    -Dimq.httpsjms.https.servletPort=41000 \
    -Dimq.jms.tcp.port=43000
