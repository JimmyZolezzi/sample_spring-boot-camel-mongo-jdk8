#!/bin/bash
PKG=prototype
BASE=/opt/${PKG}

if [ -d /var/log/${PKG} ]; then
	echo "[INFO] Removing /var/log/${PKG}"
	rm -rf /var/log/${PKG}
fi

if [ -e /etc/init.d/${PKG} ]; then
	echo "[INFO] Removing /etc/init.d/${PKG}"
	rm -f /etc/init.d/${PKG}
fi

if [ -d "${BASE}" ]; then
  echo "[INFO] Removing: ${BASE}"
  rm -rf ${BASE}
fi

echo "[INFO] Remove user: ${PKG}"

USER_EXISTS=`/usr/bin/id ${PKG} 2>&1`
USER_EXISTS_RET=$?

# user exists
if [ $USER_EXISTS_RET -eq 0 ]; then

	USER_DEL=`/usr/sbin/userdel -r ${PKG} 2>&1`
	USER_DEL_RET=$?

	if [ $USER_DEL_RET -ne 0 ]; then
		echo "[ERROR] Unable to remove user ${PKG}: $USER_DEL"
	else
		echo "[INFO] User removed successfully"
	fi
else
	echo "[WARN] No such user '${PKG}', skipping"
fi


echo "[INFO] Remove group: ${PKG}"

GROUP_EXISTS=`getent group | awk -F":" '{print $1}' | egrep "^${PKG}$" | wc -l`

# group exists
if [ "$GROUP_EXISTS" == "1" ]; then

	GROUP_DEL=`/usr/sbin/groupdel ${PKG}`
	GROUP_DEL_RET=$?

	if [ $GROUP_DEL_RET -ne 0 ]; then
		echo "[ERROR] Unable to remove group ${PKG}: $GROUP_DEL"
	else
		echo "[INFO] Group removed successfully"
	fi
else
	echo "[WARN] No such group ${PKG}"
fi