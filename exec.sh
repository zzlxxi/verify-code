#!/bin/bash
sshpass -p ${s2} ssh ubuntu@{1}
cd /opt/verify-code 
bash ./stop.sh
bash ./start.sh ${3}-${GIT_COMMIT:0:7}
exit
