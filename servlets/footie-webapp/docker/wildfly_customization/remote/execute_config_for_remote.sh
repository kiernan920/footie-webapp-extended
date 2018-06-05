#!/bin/bash

# Usage: execute.sh [WildFly mode] [configuration file]
#
# The default mode is 'standalone' and default configuration is based on the
# mode. It can be 'standalone.xml' or 'domain.xml'.

JBOSS_HOME=/opt/jboss/wildfly
JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh
JBOSS_MODE=${1:-"standalone"}
JBOSS_CONFIG=${2:-"$JBOSS_MODE.xml"}

function wait_for_server() {
  until `$JBOSS_CLI -c --controller=10.5.0.6:9990 "ls /deployment" &> /dev/null`; do
    echo "Remote Standalone waiting for itself..."
    sleep 1
  done
}
cp -rf /opt/jboss/wildfly/customization/remote/standalone-full.xml /opt/jboss/wildfly/standalone/configuration
/opt/jboss/wildfly/bin/add-user.sh -a --silent=true --user ejb --password test
/opt/jboss/wildfly/bin/add-user.sh --silent=true --user ejb --password test

echo "Starting Remote Standalone Server"
$JBOSS_HOME/bin/$JBOSS_MODE.sh -server-config=standalone-full.xml > /dev/null &

wait_for_server

echo "=> Copying EJB"
cp -r /opt/jboss/wildfly/customization/ejbToBeDeployed/ejbs.jar /opt/jboss/wildfly/standalone/deployments/

echo "=> Tailing server.log"
tail -f /opt/jboss/wildfly/standalone/log/server.log
