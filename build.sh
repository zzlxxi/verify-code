#!/bin/bash
image=verify-code:1.0.0-${GIT_COMMIT:0:7}
mirro=registry.cn-shanghai.aliyuncs.com/ink-icopy
docker tag ${image} ${mirro}/${image} &&
docker push ${mirro}/${image}
