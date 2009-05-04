#!/bin/sh -e
#

cd ~/$1
mvn archetype:create \
-DarchetypeGroupId=org.helianto \
-DarchetypeArtifactId=helianto-archetype \
-DarchetypeVersion=0.0.3 \
-DgroupId=$2 \
-DartifactId=$3
cd $3
