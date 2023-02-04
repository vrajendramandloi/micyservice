#!/bin/bash
UP=$(lsof -i:8786 | wc -l);
if [ "$UP" -gt 0 ];
then
    echo "Service is running $UP";
else
    echo "Service is Down -- Restarting $UP";
        java -jar /home/ec2-user/micyservice.jar > /home/ec2-user/micyservice.log 2>&1 &
fi