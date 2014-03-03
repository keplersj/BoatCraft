package k2b6s9j.boatcraft.core.packets



//TODO: Fill Documentation
/**
 *
 */
trait IPacket
{
  //TODO: Fill Documentation
  /**
   *
   * @param buffer
   */
  def readBytes(buffer: ByteBuf)

  //TODO: Fill Documentation
  /**
   *
   * @param buffer
   */
  def writeBytes(buffer: ByteBuf)
}