## Overview
This is a simple web service with CRUD.
SpringBoot, Redis, Prometheus, Grafana

## Start application
* clone whaleservice `git clone https://github.com/kazumaz/whaleservice.git`
* run docker `cd ./whaleservice/Docker` and `docker-compose up`
* build application `cd ./whaleservice/` and `./gradlew build`
* run application `./whaleservice/build/libs/` and `java -jar whaleservice-0.0.1-SNAPSHOT.jar &`
* access [your local application](http://localhost:8080/players) 

## Access Prometheus and Grafana
* access [Prometheus](http://localhost:9090/)
* access [Grafana](http://localhost:3000/)

## jenkins auto deploy to EC2
```
rm -rf whaleservice/
git clone https://github.com/kazumaz/whaleservice.git
cd ./whaleservice/
./gradlew build
cd ..
scp -i AWS.pem ./whaleservice/build/libs/whaleservice-0.0.1-SNAPSHOT.jar ubuntu@54.190.187.220:/home/ubuntu/
ssh -i AWS.pem  ubuntu@54.190.187.220 'cd Docker;docker-compose up -d'
ssh -i AWS.pem  ubuntu@54.190.187.220 'java -jar whaleservice-0.0.1-SNAPSHOT.jar &'
```
