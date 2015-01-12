#!/bin/bash

# APPLUS_HOME will be set be init script
export SPRING_CONFIG_LOCATION="${PROTOTYPE_HOME}/etc/application.yml"
export JAVA_OPTS="-Xms512m -Xmx1048m"

# launch the application
echo "Starting PROTOTYPE ..."
echo "Base dir: "${PROTOTYPE_HOME}

export LAST_VERSION=`ls -1 ${PROTOTYPE_HOME}/lib/*.jar | egrep -v "(javadoc|sources)" | xargs -I{} basename {}|sort -nr | head -1`
echo "Version: "${LAST_VERSION}
nohup ${JAVA_HOME}/bin/java -jar ${PROTOTYPE_HOME}/lib/$LAST_VERSION >${PROTOTYPE_HOME}/var/nohup.out 2>&1 &

echo "[Started]"