#!/usr/bin/env bash
# 运行脚本可启动 pika-upms、pika-auth、pika-gateway-admin 服务
# 1、确认
echo '1.1 开发环境确认'
echo '请确保安装好java8, maven, git, docker, docker-compose等开发环境'
read -r -p '开发环境准备好了吗? [Y/n] ' envConfirm
case $envConfirm in
    [yY][eE][sS]|[yY])
		echo 'Yes 继续执行'
		;;
    [nN][oO]|[nN])
		echo 'No 终止执行'
		exit 1
       	;;
    *)
		echo '输入有误 终止执行'
		exit 1
		;;
esac

echo '1.2 基础服务确认'
echo '请确保安装好MySQL、Redis、RocketMQ、Nacos、Sentinel等基础服务'
read -r -p '基础服务是否安装好？ [Y/n] ' baseConfirm
case $baseConfirm in
    [yY][eE][sS]|[yY])
		echo 'Yes 继续执行'
		;;
    [nN][oO]|[nN])
		echo 'No 终止执行'
		exit 1
       	;;
    *)
		echo '输入有误 终止执行'
		exit 1
		;;
esac

echo '1.3 数据初始化确认'
echo '请运行数据库脚本，初始化各服务基础数据'
read -r -p '是否运行数据库脚本？ [Y/n] ' dataConfirm
case $dataConfirm in
    [yY][eE][sS]|[yY])
		echo 'Yes 继续执行'
		;;
    [nN][oO]|[nN])
		echo 'No 终止执行'
		exit 1
       	;;
    *)
		echo '输入有误 终止执行'
		exit 1
		;;
esac

# 2、安装项目公共包
cd ./pika-common && mvn clean install -DskipTests -U

# TODO 停止服务

# 3、启动 pika-upms 服务
echo '2.启动 pika-upms 服务'

echo '开始构建 pika-upms 镜像...'
cd ./pika-upms
mvn package && mvn docker:build
echo '构建 pika-upms 镜像完成!'

echo '开始启动 pika-upms 服务...'
cd ../docker-compose
docker-compose -f docker-compose-base.yml -f docker-compose-elk.yml -f docker-compose-app.yml up -d pika-upms

echo '启动 pika-upms 服务完成!'

# 4、启动 pika-auth 服务
cd ../
echo '3.启动 pika-auth 服务'

echo '开始构建 pika-auth 镜像...'
cd ./pika-auth
mvn package && mvn docker:build
echo '构建 pika-auth 镜像完成!'

echo '开始启动 pika-auth 服务...'
cd ../docker-compose
docker-compose -f docker-compose-base.yml -f docker-compose-elk.yml -f docker-compose-app.yml up -d pika-auth
echo '启动 pika-auth 服务完成!'

# 5、启动 pika-gateway-admin 服务
cd ../
echo '4.启动 pika-gateway-admin 服务'

echo '开始构建 pika-gateway-admin 镜像...'
cd ./pika-gateway/pika-gateway-admin
mvn package && mvn docker:build

echo '开始启动 pika-gateway-admin 服务...'
cd ../../docker-compose
docker-compose -f docker-compose-base.yml -f docker-compose-elk.yml -f docker-compose-app.yml up -d pika-gateway-admin

echo '启动 pika-gateway-admin 服务完成!'
