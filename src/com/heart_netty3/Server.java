//package com.heart_netty3;
//
//import org.jboss.netty.bootstrap.ServerBootstrap;
//import org.jboss.netty.channel.ChannelPipeline;
//import org.jboss.netty.channel.ChannelPipelineFactory;
//import org.jboss.netty.channel.Channels;
//import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
//import org.jboss.netty.handler.codec.string.StringDecoder;
//import org.jboss.netty.handler.codec.string.StringEncoder;
//import org.jboss.netty.handler.timeout.IdleStateHandler;
//import org.jboss.netty.util.HashedWheelTimer;
//
//import java.net.InetSocketAddress;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class Server {
//    public static void main(String[] args) {
//        //服务类
//        ServerBootstrap bootstrap=new ServerBootstrap();
//
//        //创建线程池
//        ExecutorService boss = Executors.newCachedThreadPool();
//        ExecutorService worker = Executors.newCachedThreadPool();
//
//        //设置nioSocket工厂
//        bootstrap.setFactory(new NioServerSocketChannelFactory(boss,worker));
//
//        final HashedWheelTimer hashedWheelTimer=new HashedWheelTimer();
//
//        //设置管道的工厂
//        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
//            @Override
//            public ChannelPipeline getPipeline() throws Exception {
//                ChannelPipeline pipeline = Channels.pipeline();
//                //参数：定时器，读超时的秒数，写的超时的秒数
//                pipeline.addLast("idle",new IdleStateHandler(hashedWheelTimer,5,5,10));
//                pipeline.addLast("decoder",new StringDecoder());
//                pipeline.addLast("encorder",new StringEncoder());
//                pipeline.addLast("helloHanderler",new HellowHandler());
//                return pipeline;
//            }
//        });
//        bootstrap.bind(new InetSocketAddress(10101));
//    }
//}
