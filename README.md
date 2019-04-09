# nettytest

## netty练习
1. firstdemo为聊天模拟，模仿的微信聊天，先启动WeChatServer.java服务器，然后再启动WeChatClient.java客户端，客户端可以启动多个，不同的客户端会进入相同的频道进行聊天，有不同的命名。
2. customprotocol这个文件夹里面是使用protocol协议进行数据传输，在启动时需要启动Server.java服务器，再启动Client.java客户端就可以进行相互通讯了。
3. seconddemo是最简单基础的netty案例，先启动EchoServer.java服务器，再启动EchoClient.java就可以了，进行简单的字符串数据传输。

## websocket练习
这个练习中，启动WebsocketApplication.java启动类，然后访问[http://localhost:8080/login](http://localhost:8080/login)进行登录
> 登录名为：user1、user2，密码为：freedom
然后访问[http://localhost:8080/chat](http://localhost:8080/chat)就可以进行页面的聊天了
