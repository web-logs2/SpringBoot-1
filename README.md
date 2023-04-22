# SpringBoot

该工程是基于Springboot框架搭建，

###主要【实现功能】
1. 集成了web3j，可调用合约方法
2. 集成mybatis实现和mysql数据库连接，实现增删改查
3. 集成定时任务，定时调用合约方法

###主要【操作指令】
####一、后端服务后台启动命令
1.     后台启动执行命令：nohup java -jar 【自己的springboot项目】.jar >【日志文件名】.log 2>&1 &
  
       例如：nohup java -jar SpringBoot-0.0.1-SNAPSHOT.jar >request.log 2>&1 &
2.     查看日志命令：tail -f request.log
3.     查看启动服务端口：ps -ef （例如查到了服务如下： 502 71465 69997   0  3:53下午 ttys000    0:14.78 /usr/bin/java -jar SpringBoot-0.0.1-SNAPSHOT.jar) 
4.     杀进程命令：kil -9 端口（例如：kil -9 71465）

####二、前端服务后台启动命令
1. 设置开机启动
   
       systemctl start nginx.service
       systemctl enable nginx.service
2. 启动命令   
   
       nginx -s stop 快速停止服务器
       nginx -s quit 完全正常停止
       nginx -s reload 重新加载配置文件，修改后执行该命令即可
####三、设置防火墙开放 80 端口；
1.     firewall-cmd --zone=public --add-port=80/tcp --permanent
       firewall-cmd --reload && firewall-cmd --list-port
####四、测试 nginx 是否可被访问，应该显示nginx的欢迎界面；
1.     http://服务器IP地址:80/
###nginx路径
1. jdk路径：    
   
       /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.362.b08-1.el7_9.x86_64/jre

2. maven路径：
     
       /root/apache-maven-3.3.9  maven仓库路径：/usr/local/repository

3. 环境变量配置路径：
   
        vim /etc/profile   保存生效：source /etc/profile

4. nginx配置文件存放目录
   
        nginx的配置文件在/etc/nginx/nginx.conf

        自定义的配置文件放在/etc/nginx/conf.d

        项目文件存放在/usr/share/nginx/html/

        日志文件存放在/var/log/nginx/

        还有一些其他的安装文件都在/etc/nginx

