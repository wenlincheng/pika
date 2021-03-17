#!/bin/bash

# 非docker形式服务启动脚本
# 检查用户
checkUser() {
    user=`id -nu`
    if [ ${user} != 'root' ]
    then
        echo "===>Stop! Only root can run this script!"
        exit 3;
    fi
}
 
PROG_NAME=$0
ACTION=$1
SERVER_NAME=pika-auth-1.0.0.jar

JAVA_OPTS="-Xms256m -Xmx256m -Xmn200m -Xss128k"
 
# 使用方法
usage() {
    echo "===>Usage: $PROG_NAME {SERVER_NAME} {status|start|stop|restart}"
    exit 1;
}
 
# 检查登录用户 
checkUser
# 显示使用方法
if [ $# -lt 1 ]; then
    usage
fi


# 启动

start()
{

	PID=$(ps -ef | grep $SERVER_NAME | grep -v grep | awk '{ print $2 }')
	
	if [ ! -z "$PID" ]; then
        echo "===>The $SERVER_NAME process already exist! Please stop it first！"
        exit 1;
    else
        echo "===>Starting $SERVER_NAME ......"

        # 设置jdk参数
        nohup java $JAVA_OPTS
        # 启动进程
        nohup java -jar -DREGISTER_HOST=122.51.153.231 -DREGISTER_NAMESPACE=test $SERVER_NAME > $SERVER_NAME-start.log 2>&1 &

        # 倒计时5秒
        for i in `seq -w 5 -1 0`
        do
            echo -ne "\b$i";
            sleep 1;
        done

        # 检查是否启动成功
        PID=$(ps -ef | grep $SERVER_NAME | grep -v grep | awk '{ print $2 }')
        if [ ! -z "$PID" ]; then
            echo -e "\n===>Start successed！可在 start.log 中查看启动日志"
        else
            echo -e "\n===>Start failed！Please try again！"
        fi
    fi
}

# 停止
stop()
{
    echo "Stopping $SERVER_NAME ......"
    # 检查是否启动成功
    STOP_PID=$(ps -ef | grep $SERVER_NAME | grep -v grep | awk '{print $2}')

    if [ ! -z "$STOP_PID" ]; then
        echo "===>$SERVER_NAME process exist, Let's kill it！"
        for id in $STOP_PID
        do
            kill -9 $id
        done

        sleep 1s
        echo "===>Stop $SERVER_NAME successed！"
    else
        echo "===>No $SERVER_NAME process！"
    fi
}


# 状态
status()
{
    STATUS_PID=$(ps -ef | grep $SERVER_NAME | grep -v grep | awk '{print $2}')
    if [ ! -z "$STATUS_PID" ]; then
        echo "===>The $SERVER_NAME process is 【Running】"
    else
        echo "===>No $SERVER_NAME process！"
    fi
}



# 方法调用
case "$ACTION" in
    start)
        start
    ;;

    stop)
        stop
    ;;

    status)
        status
    ;;
 
    restart)
        stop
        start
    ;;
    *)
        usage
    ;;
esac
