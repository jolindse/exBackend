#!/bin/bash
while ! curl -s localhost 3306 > /dev/null
do
    echo "Waiting for db"
    sleep 3
done

java -jar /exbackend-0.0.1-SNAPSHOT.jar
