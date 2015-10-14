# vagrant-selenium
Puppet POC with Selenium Grid and Node

## What you get

- 3 VM's using Centos 7.x with the Virtualbox guest utils installed and Puppet.
- A Selenium Grid node
- A couple of slaves automatically connecting to the Grid

## How to update - (Note: execute this the first time as well)


1) git pull --rebase; git submodule sync; git submodule update --init --recursive;

2) vagrant up;

3) Open your browser and point to http://192.168.33.21:4444/grid/console
