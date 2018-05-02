#!/bin/sh

set -e # Exit script immediately on first error.
set -x # Print commands and their arguments as they are executed.

apt-get -y install linux-image-extra-$(uname -r) linux-image-extra-virtual
apt-get -y install \
    apt-transport-https \
    ca-certificates \
    software-properties-common

wget https://download.docker.com/linux/ubuntu/gpg
apt-key add gpg
add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
apt-get -y update
apt-get -y install docker-ce