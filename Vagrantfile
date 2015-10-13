# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|

  config.vm.box = "vStone/centos-7.x-puppet.3.x"

  config.vm.define "hub" do |hub|
    hub.vm.hostname = "hub.local"
    hub.vm.network "private_network", ip: "192.168.33.21"
  end

  config.vm.define "node01" do |node01|
    node01.vm.hostname = "node01.local"
    node01.vm.network "private_network", ip: "192.168.33.22"
  end

  config.vm.define "node02" do |node02|
    node02.vm.hostname = "node02.local"
    node02.vm.network "private_network", ip: "192.168.33.23"
  end

  # Clear firewall rules to allow intermachine communication
  config.vm.provision "shell", inline: "service firewalld stop; chkconfig firewalld off;"

  config.vm.provision "puppet" do |puppet|
    puppet.manifests_path     = "manifests"
    puppet.manifest_file      = "site.pp"
    puppet.module_path        = "modules"
    puppet.hiera_config_path  = "hiera.yaml"
    #puppet.options            = "--verbose --debug"
   end

end
