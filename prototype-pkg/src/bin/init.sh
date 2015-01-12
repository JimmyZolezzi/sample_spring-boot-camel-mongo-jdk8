#!/bin/bash
#

### BEGIN INIT INFO
# Provides:          prototype
# Required-Start:    $local_fs $remote_fs $network $syslog
# Required-Stop:     $local_fs $remote_fs $network $syslog
# Default-Start:     3 5
# Default-Stop:      0 1 6
# X-Interactive:     true
# Short-Description: Start/stop PROTOTYPE
### END INIT INFO

export JAVA_HOME=/usr/java/default

export PROTOTYPE_USER=prototype
export PROTOTYPE_HOME=/opt/prototype

# -------------------------------------------------------------------------
# no modifications after this point
# -------------------------------------------------------------------------

PROTOTYPE_START=${PROTOTYPE_HOME}/bin/start.sh
PROTOTYPE_STOP=${PROTOTYPE_HOME}/bin/stop.sh

case $1 in
    start)
      su -c "$PROTOTYPE_START" ${PROTOTYPE_USER}
    ;;
    stop)
      su -c "$PROTOTYPE_STOP" ${PROTOTYPE_USER}
    ;;
    restart)
      su -c "$PROTOTYPE_STOP" ${PROTOTYPE_USER}
      su -c "$PROTOTYPE_START" ${PROTOTYPE_USER}
    ;;
esac
exit 0