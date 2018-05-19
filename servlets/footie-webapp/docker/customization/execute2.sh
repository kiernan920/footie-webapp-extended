#!/bin/bash

# Usage: execute.sh [WildFly mode] [configuration file]
#
# The default mode is 'standalone' and default configuration is based on the
# mode. It can be 'standalone.xml' or 'domain.xml'.

JBOSS_HOME=/opt/jboss/wildfly
JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh
JBOSS_MODE=${1:-"standalone"}
JBOSS_MODE_ARRAY=( "standalone" "domain" )
JBOSS_CONFIG=${2:-"$JBOSS_MODE.xml"}

function wait_for_server() {
  until `$JBOSS_CLI --controller=localhost:9992 -c "ls /deployment" &> /dev/null`; do
    sleep 5
  done
}
cp -rf /opt/jboss/wildfly/customization/host.xml /opt/jboss/wildfly/domain/configuration
cp -rf /opt/jboss/wildfly/customization/standalone.xml /opt/jboss/wildfly/standalone/configuration/
cp -rf /opt/jboss/wildfly/customization/standalone.conf /opt/jboss/wildfly/bin/

echo "=> Starting WildFly server"
sleep 7
$JBOSS_HOME/bin/${JBOSS_MODE_ARRAY[1]}.sh  > /dev/null &
#-c $JBOSS_CONFIG

#echo "=> Waiting for the server to boot"
#wait_for_server

#echo "=> Executing the commands"
#$JBOSS_CLI -c --controller=localhost:9992 --file=`dirname "$0"`/commands.cli

#echo "=> Copying WAR"

#cp -r /opt/jboss/wildfly/warToBeDeployed/footie-webapp.war /opt/jboss/wildfly/standalone/deployments/

#echo "=> Tailing server.log"

#tail -f /opt/jboss/wildfly/standalone/log/server.log
sleep 5
tail -f /opt/jboss/wildfly/domain/log/host-controller.log
