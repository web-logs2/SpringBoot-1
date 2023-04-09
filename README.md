# SpringBoot

该工程是基于Springboot框架搭建，

主要【实现功能】
1. 集成了web3j，可调用合约方法
2. 集成mybatis实现和mysql数据库连接，实现增删改查
3. 集成定时任务，定时调用合约方法

主要【操作指令】
1. 后台启动执行命令：nohup java -jar 【自己的springboot项目】.jar >【日志文件名】.log 2>&1 &
2. 例如：nohup java -jar SpringBoot-0.0.1-SNAPSHOT.jar >request.log 2>&1 &
3. 查看日志命令：tail -f request.log
4. 查看启动服务端口：ps -ef （例如查到了服务如下： 502 71465 69997   0  3:53下午 ttys000    0:14.78 /usr/bin/java -jar SpringBoot-0.0.1-SNAPSHOT.jar) 
5. 杀进程命令：kil -9 端口（例如：kil -9 71465）

