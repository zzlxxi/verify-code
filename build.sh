#/bin/bash
echo ${GIT_COMMIT:0:7}
commit=${GIT_COMMIT:0:7}
docker build -t verify-code:1.0.0-${commit} .
