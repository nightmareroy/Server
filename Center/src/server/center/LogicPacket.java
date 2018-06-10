package server.center;

import java.util.Arrays;

import com.google.protobuf.MessageOrBuilder;

import common.DataUtil;
import io.netty.channel.Channel;


public class LogicPacket extends Packet {
	public LogicPacket(Channel channel) throws Exception{
		super(channel);
		
		if(body.length<4)
		{
			throw new Exception("body is too small!");
		}
		
		byte[] routeLengthByte=Arrays.copyOfRange(body, 0, 4);
		int routeLength=DataUtil.bytes2int(routeLengthByte);
		
		if(body.length<4 + routeLength)
		{
			throw new Exception("body is too small!");
		}
		
		byte[] routeByte=Arrays.copyOfRange(body, 4, 4 + routeLength);
		
		route=new String(routeByte);
		
		
		byte[] protobufByte=Arrays.copyOfRange(body, 4, body.length-4);
		
		
	}
	public String route;
//	public 
	public MessageOrBuilder content;
	
	
}
