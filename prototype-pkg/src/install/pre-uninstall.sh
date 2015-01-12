#!/bin/bash

PKG=prototype

if [ -e "/etc/init.d/${PKG}" ]; then
	echo "Stopping ${PKG} for uninstall..."
	/etc/init.d/applus stop
fi