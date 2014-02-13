package k2b6s9j.boatcraft.core.packets

import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

object ChannelHandler extends FMLIndexedMessageToMessageCodec[IPacket]
{	
    override def encodeInto(ctx: ChannelHandlerContext, packet: IPacket, data: ByteBuf)
    {
        packet writeBytes data
    }
    
    override def decodeInto(ctx: ChannelHandlerContext, data: ByteBuf, packet: IPacket)
    {
        packet readBytes data
    }
}