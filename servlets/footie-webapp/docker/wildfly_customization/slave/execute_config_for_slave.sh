#!/bin/bash

JBOSS_HOME=/opt/jboss/wildfly
JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh
JBOSS_MODE=${1:-"standalone"}
JBOSS_MODE_ARRAY=( "standalone" "domain" )
JBOSS_CONFIG=${2:-"$JBOSS_MODE.xml"}

function waitForMasterToBoot {
	status=""
	while [[ "$status" != *'running'* ]];do
		status=$(/opt/jboss/wildfly/bin/jboss-cli.sh -c --controller=10.5.0.2:9990 --user=admin1 --password=admin1 --commands="/host=master:read-attribute(name=host-state)" | grep "running")
		echo $status
		if [[ "$status" != *"running"* ]]; then
			echo "Slave waiting for Master..."
			sleep 1
		fi
	done
}



function waitForSlaveToBoot {
	status=""
	while [[ "$status" != *'running'* ]];do
		status=$(/opt/jboss/wildfly/bin/jboss-cli.sh -c --controller=10.5.0.2:9990 --user=admin1 --password=admin1 --commands="/host=slave:read-attribute(name=host-state)" | grep "running")
		echo $status
		if [[ "$status" != *"running"* ]]; then
			echo "Slave waiting for itself..."
			sleep 1
		fi
	done
}

cp -rf /opt/jboss/wildfly/customization/common/domain.xml /opt/jboss/wildfly/domain/configuration
cp -rf /opt/jboss/wildfly/customization/slave/host.xml /opt/jboss/wildfly/domain/configuration

waitForMasterToBoot

echo "Starting Slave"
$JBOSS_HOME/bin/${JBOSS_MODE_ARRAY[1]}.sh  > /dev/null &

waitForSlaveToBoot

echo "=> Tailing server.log"
tail -f wildfly/domain/servers/server-three-slave/log/server.log
tail -f /opt/jboss/wildfly/domain/log/host-controller.log