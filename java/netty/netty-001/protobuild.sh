#!/bin/bash

./protoc --proto_path=./proto --java_out=./src/main/java user.proto
./protoc --proto_path=./proto --java_out=./src/main/java request.proto
./protoc --proto_path=./proto --java_out=./src/main/java response.proto

