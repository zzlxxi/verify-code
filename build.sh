#!/bin/bash
image=verify-code:${version}-${GIT_COMMIT:0:7}
mirro=registry.cn-shanghai.aliyuncs.com/ink-icopy
docker login --username=3270349725@qq.com registry.cn-shanghai.aliyuncs.com -p=Zzlxxi@123
docker build -t ${image} . && docker tag ${image} ${mirro}/${image} && docker push ${mirro}/${image}
