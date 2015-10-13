node /hub/ {

  include java
  include selenium::hub

  Class['java'] -> Class['selenium::hub']

}

node /node/ {

  include java
  include selenium::node

  Class['java'] -> Class['selenium::node']

}
