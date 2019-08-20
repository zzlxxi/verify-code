#!/bin/bash
image-version=verify-code:1.0.0-${GIT_COMMIT:0:7}
mirro=registry.cn-shanghai.aliyuncs.com/ink-icopy
docker tag ${image-version} ${mirro}/${image-version} &&
docker push ${mirro}/${image-version}
