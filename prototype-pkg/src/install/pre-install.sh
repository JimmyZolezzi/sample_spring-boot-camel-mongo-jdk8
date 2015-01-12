#!/bin/bash

PKG=prototype

if [ -d "/etc/init.d/${PKG}" ]; then
  echo "Stopping ${PKG} prior to installation..."
  `/etc/init.d/${PKG} stop`
fi

echo "[INFO] Create group: ${PKG}"

GROUP_EXISTS=`getent group | awk -F":" '{print $1}' | egrep "^${PKG}$" | wc -l`

# group exists
if [ "$GROUP_EXISTS" != "1" ]; then

	GROUP_ADD=`/usr/sbin/groupadd ${PKG} 2>&1`
	GROUP_ADD_RET=$?

	if [ $GROUP_ADD_RET -ne 0 ]; then
		echo "[ERROR] Can't create group ${PKG}"
		exit 1
	fi

	echo "[INFO] Group ${PKG} created successfully"
fi

echo "[INFO] Create user: ${PKG}"

USER_EXISTS=`/usr/bin/id ${PKG} 2>&1`
USER_EXISTS_RET=$?

# user exists
if [ $USER_EXISTS_RET -ne 0 ]; then

	USER_ADD=`/usr/sbin/useradd -g ${PKG} ${PKG} 2>&1`
	USER_ADD_RET=$?

	if [ $USER_ADD_RET -ne 0 ]; then
		echo "[ERROR] Can't create user ${PKG}"
		exit 1;
	fi

	echo "[INFO] User ${PKG} created successfully"
fi