## Build
docker-compose up
docker-compose down

##Connect to jboss-cli specifying management controller of master 
./jboss-cli.sh -c --controller=10.5.0.2:9990 //LOG IN FROM MASTER
./jboss-cli.sh -c --controller=10.5.0.2:9990 --password=admin1 --user=admin1 //LOG IN FROM SLAVE

##Connect to containers
docker exec -it wildfly_container1 bash 
docker exec -it wildfly_container2 bash 

##Test URL for testing with Apache
http://10.5.0.4/footie-webapp/rest/users/test

##jmeter -n -t tests/trivial/footie_test_plan.jmx -l ./tests/trivial/footie_test_plan.jmx