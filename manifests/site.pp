node /hub/ {

  include java
  include selenium::hub

  Class['java'] -> Class['selenium::hub']

}

node /node/ {

  include java
  include selenium::node
  include common

  Class['java'] -> Class['selenium::node']

}

class common {

  package { 'xorg-x11-server-Xvfb': }
  package { 'firefox': }

  # Globally available EXEC for reloading Systemd
  exec {'Reload systemd':
    command     => '/usr/bin/systemctl daemon-reload',
    refreshonly => true,
  }

  file { 'xvfb.service':
    ensure  => file,
    path    => '/usr/lib/systemd/system/xvfb.service',
    content => '
[Unit]
Description=Frame Buffer

[Service]
Type=simple
PIDFile=/var/run/xvfb.pid
ExecStart=/usr/bin/Xvfb :0 -ac -screen 0 1024x768x24 +extension GLX +render -noreset
    ',
    notify  => Exec['Reload systemd'],
  }

  service { 'xvfb':
    ensure  => running,
    enable  => true,
    require => File['xvfb.service'],
  }

}