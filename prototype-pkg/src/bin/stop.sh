#!/bin/bash
echo "Stopping PROTOTYPE service..."

PID_FILE=/opt/prototype/var/prototype.pid

if [[ -f ${PID_FILE} ]]
then
	PID=`cat ${PID_FILE}`
	kill ${PID}

	COUNTER=0
	while ps -p ${PID} > /dev/null && [[ ${COUNTER} -lt 10 ]]
	do
		echo "Waiting..."
		sleep 1
		let counter=counter+1
	done

	rm -f ${PID_FILE}

	# hard kill process if kill did not succeed
	if ps -p ${PID} > /dev/null
	then
		echo "PROTOTYPE did not shut down gracefully, killing process hard"
		kill -9 ${PID}
	fi
fi

echo "[Stopped]"