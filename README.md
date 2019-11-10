## Overview
This is a simple web service with CRUD.
(Spring Boot, Redis)
 
## Start application
* clone whaleservice `git clone https://github.com/kazumaz/whaleservice.git`
* run docker `cd ./whaleservice/Docker` and `docker-compose up`
* build application `cd ./whaleservice/` and `./gradlew build`
* run application `./whaleservice/build/libs/` and `java -jar whaleservice-0.0.1-SNAPSHOT.jar &`
* access [your local application](http://localhost:8080/players) 

## jenkins auto deploy to EC2
```
rm -rf whaleservice/
sleep 5
git clone https://github.com/kazumaz/whaleservice.git
sleep 5
cd ./whaleservice/
./gradlew build
sleep 5
cd ..
scp -i AWS.pem ./whaleservice/build/libs/whaleservice-0.0.1-SNAPSHOT.jar ubuntu@54.190.187.220:/home/ubuntu/
sleep 15
ssh -i AWS.pem  ubuntu@54.190.187.220 'cd Docker;docker-compose up -d'
sleep 5
ssh -i AWS.pem  ubuntu@54.190.187.220 'java -jar whaleservice-0.0.1-SNAPSHOT.jar &'
```
