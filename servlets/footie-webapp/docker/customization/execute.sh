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
  until `$JBOSS_CLI -c "ls /deployment" &> /dev/null`; do
    sleep 1
  done
}
cp -rf /opt/jboss/wildfly/ha/host.xml /opt/jboss/wildfly/domain/configuration
cp -rf /opt/jboss/wildfly/customization/standalone.conf /opt/jboss/wildfly/bin/

echo "=> Starting WildFly server"
$JBOSS_HOME/bin/${JBOSS_MODE_ARRAY[1]}.sh > /dev/null &

#echo "=> Waiting for the server to boot"
#wait_for_server

#echo "=> Executing the commands"
#$JBOSS_CLI -c --file=`dirname "$0"`/commands.cli

#echo "=> Copying WAR"

#cp -r /opt/jboss/wildfly/warToBeDeployed/footie-webapp.war /opt/jboss/wildfly/standalone/deployments/

#echo "=> Tailing server.log"

#tail -f /opt/jboss/wildfly/standalone/log/server.log
sleep 5
/opt/jboss/wildfly/bin/add-user.sh --silent=true admin1 admin1
/opt/jboss/wildfly/bin/add-user.sh --silent=true slave slave
tail -f /opt/jboss/wildfly/domain/log/host-controller.log
