package com.heart_netty5;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class Server {
    public static void main(String[] args) {
        //服务类
        ServerBootstrap bootstrap =new ServerBootstrap();

        //创建线程池
        EventLoopGroup boos =new NioEventLoopGroup();
        EventLoopGroup worker =new NioEventLoopGroup();

        //设置线程池
        bootstrap.group(boos,worker);

        //设置socket工厂
        bootstrap.channel(NioServerSocketChannel.class);

        //设置管道工厂
        bootstrap.childHandler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new IdleStateHandler(5,5,10));
                pipeline.addLast(new StringDecoder());
                pipeline.addLast(new StringEncoder());
                pipeline.addLast(new ServerHander());
            }
        });

        /*
         * netty3的写法
         * bootStrap.setOption("backlog",1024);
         * bootStrap.setOption("tcpNoDelay",true);
         * bootStrap.setOption("keepAlive",true);
         */



        //设置连接缓冲池的大小
        bootstrap.option(ChannelOption.SO_BACKLOG,2048);
        //维持连续的活跃度，清除死链接
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);
        //关闭延时发送
        bootstrap.childOption(ChannelOption.TCP_NODELAY,true);

        //绑定端口
        ChannelFuture future=bootstrap.bind(10111);
        System.out.println("start");

        //等待服务器关闭
        try {
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boos.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
