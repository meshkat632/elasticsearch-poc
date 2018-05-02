#!/bin/sh

set -e # Exit script immediately on first error.
set -x # Print commands and their arguments as they are executed.


wget -O /usr/local/bin/docker-compose https://github.com/docker/compose/releases/download/1.20.1/docker-compose-`uname -s`-`uname -m`
chmod +x /usr/local/bin/docker-compose
docker-compose --version
