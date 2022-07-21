#!/bin/bash

./protoc --proto_path=./proto --java_out=./src/main/java group.proto
./protoc --proto_path=./proto --java_out=./src/main/java message.proto
./protoc --proto_path=./proto --java_out=./src/main/java user.proto

