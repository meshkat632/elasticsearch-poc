require 'erb'
require 'ostruct'


def build_scripts(templatePath, destinationPath, data)
   newScript = ERB.new(File.read(templatePath)).result(data.instance_eval { binding })
   open(destinationPath, 'w') do |f|
       f.puts newScript
   end
   puts "script is created at :"+destinationPath
end

def build_scripts_for_proxy()
   puts "build_scripts_for_proxy"
   if File.exist?("vagrant.yml")
      vagrant_config = YAML.load_file(File.open("vagrant.yml"))
      http_proxy = vagrant_config['http_proxy']
      https_proxy = vagrant_config['https_proxy']
      ftp_proxy = vagrant_config['ftp_proxy']
      no_proxy = vagrant_config['no_proxy']
      host_ip = vagrant_config['host_ip']
      timeStamp = Time.now.strftime("%Y_%m_%d_%H_%M_%S_%L")
      namespace = OpenStruct.new( time_stamp:timeStamp, http_proxy: http_proxy, https_proxy: https_proxy, ftp_proxy: ftp_proxy, no_proxy:no_proxy )
      build_scripts("vagrant-scripts/templates/.curlrc","vagrant-scripts/build/.curlrc", namespace )
      build_scripts("vagrant-scripts/templates/.wgetrc","vagrant-scripts/build/.wgetrc", namespace )
      build_scripts("vagrant-scripts/templates/apt.conf","vagrant-scripts/build/apt.conf", namespace )
      build_scripts("vagrant-scripts/templates/.gitconfig","vagrant-scripts/build/.gitconfig", namespace )
      build_scripts("vagrant-scripts/templates/docker-proxy-settings.sh","vagrant-scripts/build/docker-proxy-settings.sh", namespace )
      return true
   else
      return false
   end
end

Vagrant.configure("2") do |config|

  runProxySettings = build_scripts_for_proxy()
  puts runProxySettings

  config.vm.provider 'virtualbox' do |v|
    v.memory = 1536
    v.cpus = 1
  end

  devvm_disk = 'devvm.vdi'

  config.vm.define :devvm do |box|
      box.vm.box = "ubuntu/trusty64"
      box.vm.network :private_network, ip: "192.168.1.48"
      box.vm.network "forwarded_port", guest: 8080, host: 8080,       
        auto_correct: true
      box.vm.network "forwarded_port", guest: 3000, host: 3000,       
        auto_correct: true
      box.vm.network "forwarded_port", guest: 9200, host: 9200,       
        auto_correct: true    
      box.vm.network "forwarded_port", guest: 5601, host: 5601,       
        auto_correct: true      
      box.vm.host_name = "ubuntu-elasticsearch-test"

      box.vm.synced_folder "vagrant-scripts/build/", "/tmp/vagrant-scripts/build/"
      #box.vm.synced_folder "guttenberg-search/", "/development/guttenberg-search"

      config.vm.provider 'virtualbox' do |v|
        v.memory = 2048
        v.cpus = 1
      end
      config.ssh.username = "vagrant"
      config.ssh.password = "vagrant"
      config.ssh.insert_key = true

      if runProxySettings
         config.vm.provision :shell, :path => "vagrant-scripts/templates/proxy-provisioner.sh"
      end

      config.vm.provision :shell, :path => "vagrant-scripts/scripts/install-docker.sh"
      config.vm.provision :shell, :path => "vagrant-scripts/scripts/install-docker-compose.sh"
      config.vm.provision :shell, :path => "vagrant-scripts/build/docker-proxy-settings.sh"      
  end
end