#!/bin/bash
cp -rf ../../../ejbs/target/ejbs-1.0-SNAPSHOT.jar /home/kinya/dev/projects/footie-webapp-extended/servlets/footie-webapp/docker/wildfly_customization/ejbToBeDeployed
if [ $? -eq 0 ]; then
	echo "copy a success"
else
    echo "there was an error"
fi
