#!/bin/bash
if [ $# -ne 3 ]
then
  echo "Usage: `basename $0` <lastName> <firstName> <birthday>"
  exit 1
fi

curl -i \
    -S \
    -s \
    -H "Content-Type:application/json" \
    -H "Transfer-Encoding: chunked" \
    -X POST \
    -d "{ \"lastName\": \"$1\", \"firstName\":\"$2\", \"birthday\": \"$3\" }" \
    "http://localhost:9090/prototype/api/persons/create"

echo ""
echo ""
