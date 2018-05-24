#!/bin/bash

JBOSS_HOME=/opt/jboss/wildfly
JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh
JBOSS_MODE=${1:-"standalone"}
JBOSS_MODE_ARRAY=( "standalone" "domain" )
JBOSS_CONFIG=${2:-"$JBOSS_MODE.xml"}

function wait_for_server() {
  until `$JBOSS_CLI --controller=10.5.0.2:9990 -c "ls /deployment" &> /dev/null`; do
    sleep 5
  done
}
cp -rf /opt/jboss/wildfly/customization/common/domain.xml /opt/jboss/wildfly/domain/configuration
cp -rf /opt/jboss/wildfly/customization/slave/host.xml /opt/jboss/wildfly/domain/configuration

echo "=> Starting WildFly server"
sleep 7
$JBOSS_HOME/bin/${JBOSS_MODE_ARRAY[1]}.sh  > /dev/null &
#-c $JBOSS_CONFIG

echo "=> Waiting for the server to boot"
wait_for_server

#echo "=> Tailing server.log"

#tail -f /opt/jboss/wildfly/standalone/log/server.log
sleep 5
tail -f /opt/jboss/wildfly/domain/log/host-controller.log
