#!/bin/bash

JBOSS_HOME=/opt/jboss/wildfly
JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh
JBOSS_MODE=${1:-"standalone"}
JBOSS_MODE_ARRAY=( "standalone" "domain" )
JBOSS_CONFIG=${2:-"$JBOSS_MODE.xml"}

function waitForSlaveToBoot {
	status=""
	while [[ "$status" != *'STARTED'* ]];do
		status=$(/opt/jboss/wildfly/bin/jboss-cli.sh -c --controller=10.5.0.2:9990 --commands="/host=slave/server-config=server-three-slave:read-resource(include-runtime=true)" | grep "STARTED")
		echo $status
		if [[ "$status" != *"STARTED"* ]]; then
			echo "Master waiting for Slave..."
			sleep 1
		fi
	done
}

cp -rf /opt/jboss/wildfly/customization/common/domain.xml /opt/jboss/wildfly/domain/configuration
cp -rf /opt/jboss/wildfly/customization/master/host.xml /opt/jboss/wildfly/domain/configuration

echo "Starting Master"
$JBOSS_HOME/bin/${JBOSS_MODE_ARRAY[1]}.sh > /dev/null &

/opt/jboss/wildfly/bin/add-user.sh --silent=true admin1 admin1
/opt/jboss/wildfly/bin/add-user.sh --silent=true slave slave

waitForSlaveToBoot

echo "=> Executing the commands"
$JBOSS_CLI -c --controller=10.5.0.2:9990 --file=`dirname "$0"`/commands.cli

echo "=> Tailing Log"
tail -f  wildfly/domain/servers/server-three/log/server.log
tail -f /opt/jboss/wildfly/domain/log/host-controller.log

