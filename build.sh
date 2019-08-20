#! /bin/bash
docker build -t verify-code:1.0.0-${GIT_COMMIT:0:7} .
