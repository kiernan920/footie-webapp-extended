#!/bin/bash
cp -rf ../target/footie-webapp.war ./wildfly_customization/warToBeDeployed
if [ $? -eq 0 ]; then
	echo "copy a success"
else
    echo "there was an error"
fi