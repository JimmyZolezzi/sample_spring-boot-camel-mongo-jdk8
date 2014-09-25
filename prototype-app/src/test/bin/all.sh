#!/bin/bash
if [ $# -ne 0 ]
then
  echo "Usage: `basename $0`"
  exit 1
fi

curl -i http://localhost:9090/prototype/api/persons/all

echo ""
echo ""