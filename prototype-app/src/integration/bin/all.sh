#!/bin/bash
if [ $# -ne 0 ]
then
  echo "Usage: `basename $0`"
  exit 1
fi

curl -i http://127.0.0.1:9090/prototype/api/persons/all

echo ""
echo ""