#!/bin/bash
sleep 5
{
    curl -s "localhost:8088/api/direct?dep_sid=3&arr_sid=4" | grep -E 'true|false' > /dev/null &&
    curl -s "localhost:8088/api/direct?dep_sid=0&arr_sid=1" | grep -E 'true|false' > /dev/null &&
    curl -s "localhost:8088/api/direct?dep_sid=951440&arr_sid=646561" | grep -E 'true|false' > /dev/null &&
    curl -s "localhost:8088/api/direct?dep_sid=1&arr_sid=126694" | grep -E 'true|false' > /dev/null &&
    curl -s "localhost:8088/api/direct?dep_sid=1&arr_sid=1" | grep -E 'true|false' > /dev/null &&
    echo TEST PASSED!
} || {
    echo TEST FAILED!
}