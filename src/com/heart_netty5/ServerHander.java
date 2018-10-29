package com.heart_netty5;


import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;


public class ServerHander extends SimpleChannelInboundHandler<String> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) {
        System.out.println(msg);
        ctx.channel().writeAndFlush("hi");
        ctx.writeAndFlush("Saber");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event=null;
        if(evt instanceof IdleStateEvent)
            event=(IdleStateEvent) evt;

        if (event.state()== IdleState.ALL_IDLE){
            //清除超时会话
            ChannelFuture writandAndFlush =ctx.writeAndFlush("you will close");
            writandAndFlush.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    ctx.channel().close();
                }
            });
        }
        else {
            super.userEventTriggered(ctx,evt);
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("channelActive");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("channelInactive");
    }
}
