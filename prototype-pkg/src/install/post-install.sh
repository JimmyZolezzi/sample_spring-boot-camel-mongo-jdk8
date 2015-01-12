#!/bin/bash

PKG=prototype
BASE=/opt/${PKG}

mkdir -p ${BASE}/log
mkdir -p ${BASE}/var

if [ -d ${BASE} ]; then
	echo "[INFO] Setting user/group to ${PKG} on ${BASE}"
	chown -R ${PKG}:${PKG} ${BASE}
fi