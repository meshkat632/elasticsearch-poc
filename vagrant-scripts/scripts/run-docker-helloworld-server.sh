#!/bin/sh

set -e # Exit script immediately on first error.
set -x # Print commands and their arguments as they are executed.


cd dockercloud-hello-world
docker-compose up