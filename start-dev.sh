#!/bin/bash
# start-dev.sh
echo "Starting ServiceSense AI development environment..."
docker-compose up -d postgres redis
mvn spring-boot:run