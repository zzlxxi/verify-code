#!/bin/bash
image=verify-code:${1}-${GIT_COMMIT:0:7}
mirro=registry.cn-shanghai.aliyuncs.com/ink-icopy
docker rmi ${mirro}/${image}
docker build -t ${image} . && 
docker tag ${image} ${mirro}/${image} && 
docker rmi ${image} && 
docker push ${mirro}/${image}
