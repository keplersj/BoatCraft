package k2b6s9j.boatcraft.core.packets





//TODO: Fill Documentation
/**
 *
 */
object ChannelHandler extends FMLIndexedMessageToMessageCodec[IPacket]
{
  //TODO: Fill Documentation
  /**
   *
   * @param ctx
   * @param packet
   * @param data
   */
  override def encodeInto(ctx: ChannelHandlerContext, packet: IPacket, data: ByteBuf)
    {
        packet writeBytes data
    }

  //TODO: Fill Documentation
  /**
   *
   * @param ctx
   * @param data
   * @param packet
   */
    override def decodeInto(ctx: ChannelHandlerContext, data: ByteBuf, packet: IPacket)
    {
        packet readBytes data
    }
}