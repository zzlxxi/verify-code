#!/bin/bash
ssh ubuntu@${s1} -p ${s2}
cd /opt/verify-code 
bash ./stop.sh
bash ./start.sh ${3}-${GIT_COMMIT:0:7}
exit
