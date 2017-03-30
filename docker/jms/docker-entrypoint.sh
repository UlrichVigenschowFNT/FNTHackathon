#! /usr/bin/env bash

export TOPIC_NAME=${TOPIC_NAME}

# start broker
${OPENMQ_BIN}/imqbrokerd &

# FIXME: wait for broker deamon background process to be started successfully
sleep 4

# create topic (note: "tee" for verbose output)
${OPENMQ_BIN}/imqcmd create dst -n ${TOPIC_NAME} -t t -b localhost:7676 -u admin -passfile ${OPENMQ_LOCATION}/passwords.txt -f | tee 

# list destinations (note: "tee" for verbose output)
${OPENMQ_BIN}/imqcmd list dst -b :7676 -u admin -passfile ${OPENMQ_LOCATION}/passwords.txt -f | tee

# restart broker in foreground
${OPENMQ_BIN}/imqcmd shutdown bkr -u admin -passfile ${OPENMQ_LOCATION}/passwords.txt -f
${OPENMQ_BIN}/imqbrokerd