package server.login;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import common.Config;
import common.Out;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.timeout.ReadTimeoutException;
import server.login.Header.HeaderType;

public class Decoder extends LengthFieldBasedFrameDecoder {
	
	public Decoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment,
			int initialBytesToStrip) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
		// TODO Auto-generated constructor stub
	}




	private Map<String, Boolean> nodeCheck=new HashMap<>();


	

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		ByteBuf frame=(ByteBuf)super.decode(ctx, in);
		if(frame==null) {
			return null;
		}
		
		NettyMessage message=new NettyMessage();
		Header header=new Header();
		header.crcCode=frame.readInt();
		header.length=frame.readInt();
		header.sessionID=frame.readLong();
		header.type=frame.readByte();
		
		byte[] body=new byte[frame.readableBytes()];
		frame.readBytes(body);
		message.header=header;
		message.body=body;
		
		InetSocketAddress remoteAddress=(InetSocketAddress)ctx.channel().remoteAddress();
		
		HeaderType headerType=HeaderType.getHeaderType(header.type);
		if(headerType==null) {
			throw new Exception("找不到header type定义！");
		}
		switch (headerType) {
		case WorkReq:
			
			break;
		case WorkRes:
					
			break;
		case WorkOneWay:
			
			break;
		case LoginReq:
			Out.debug(remoteAddress.getAddress().getHostAddress());
			Out.debug(remoteAddress.toString());
			
			//如果开启白名单，检测
			if(Config.LoginServer.WhiteList.Enable) {
				if(!Arrays.asList(Config.LoginServer.WhiteList.List).contains(remoteAddress.getAddress().getHostAddress()))
				{
					ctx.writeAndFlush(new NettyMessage(header.sessionID,HeaderType.LoginRes,new byte[] {-1}));
					break;
				}
			}
			//检测重复登录
			if(nodeCheck.containsKey(remoteAddress.toString())) {
				ctx.writeAndFlush(new NettyMessage(header.sessionID,HeaderType.LoginRes,new byte[] {-1}));
				break;
			}
			ctx.writeAndFlush(new NettyMessage(header.sessionID,HeaderType.LoginRes,new byte[] {0}));
			Out.debug("loginres");
			break;
		case LoginRes:
			
			break;
		case BeatReq:
			
			break;
		case BeatRes:
			
			break;
		case LogoutReq:
			
			break;
		default:
			break;
		}
		
		return null;
	}
	

	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
		Out.debug(cause);
		if(cause instanceof ReadTimeoutException) {
			InetSocketAddress remoteAddress=(InetSocketAddress)ctx.channel().remoteAddress();
			
			nodeCheck.remove(remoteAddress.toString());
			Out.debug("已移除出节点列表");
			ctx.close();
		}
		
		
        //ctx.fireExceptionCaught(cause);
    }
}