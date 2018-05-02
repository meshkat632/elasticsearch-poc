#!/bin/sh

set -e # Exit script immediately on first error.
set -x # Print commands and their arguments as they are executed.

service docker stop

echo 'export http_proxy=<%= http_proxy %>/' | sudo tee -a /etc/default/docker
echo 'export https_proxy=<%= http_proxy %>/' | sudo tee -a /etc/default/docker

usermod -aG docker ${USER}
service docker restart
docker run hello-world