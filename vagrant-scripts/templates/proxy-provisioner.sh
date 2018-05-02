#!/bin/sh
set -e # Exit script immediately on first error.
set -x # Print commands and their arguments as they are executed.

cp /tmp/vagrant-scripts/build/apt.conf /etc/apt/apt.conf
cp /tmp/vagrant-scripts/build/.curlrc /home/vagrant/.curlrc
cp /tmp/vagrant-scripts/build/.wgetrc /home/vagrant/.wgetrc
cp /tmp/vagrant-scripts/build/.wgetrc /etc/wgetrc
cp /tmp/vagrant-scripts/build/.gitconfig ~/.gitconfig
echo "proxy provisioning is done"
sudo apt-get update -y