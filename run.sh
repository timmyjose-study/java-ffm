#!/bin/bash

java -Djava.library.path=".:rational-rs/target/release/" -jar --add-modules jdk.incubator.foreign --enable-native-access=ALL-UNNAMED java-ffm/target/java-ffm-1.0-SNAPSHOT.jar

