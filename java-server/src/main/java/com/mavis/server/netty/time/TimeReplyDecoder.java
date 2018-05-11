package com.mavis.server.netty.time;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

public class TimeReplyDecoder extends ReplayingDecoder<Void> {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		out.add(new UnixTime(in.readUnsignedInt()));
	}

}
