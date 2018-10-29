//package com.heart_netty3;
//
//import org.jboss.netty.channel.*;
//import org.jboss.netty.handler.timeout.IdleState;
//import org.jboss.netty.handler.timeout.IdleStateEvent;
//
//public class HellowHandler extends SimpleChannelHandler {
//    @Override
//    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
//        super.messageReceived(ctx, e);
//        System.out.println(e.getMessage()+"666666666");
//    }
//
//    @Override
//    public void handleUpstream(final ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
//        super.handleUpstream(ctx, e);
//        if(e instanceof IdleStateEvent){
//            if (((IdleStateEvent) e).getState()== IdleState.ALL_IDLE){
//                    System.out.println("玩家下线");
//                    //关闭会话
//                ChannelFuture write =ctx.getChannel().write("time out");
//                write.addListener(new ChannelFutureListener() {
//                    @Override
//                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                        ctx.getChannel().close();
//                    }
//                });
//            }
//        }
//        else {
//            super.handleUpstream(ctx,e);//正常状态继续向下传递
//        }
//    }
//}
