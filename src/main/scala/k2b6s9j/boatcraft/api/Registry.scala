package k2b6s9j.boatcraft.api

import k2b6s9j.boatcraft.core.BoatCraft
import k2b6s9j.boatcraft.api.traits._
import java.util.{HashMap, Map}

object Registry {

  var materials: Map[String, Material] = new HashMap[String, Material]
  var modifiers: Map[String, Modifier] = new HashMap[String, Modifier]

  def register(registrar: AnyRef)= registrar match {
    case _: Material =>
      materials put (registrar.toString, registrar.asInstanceOf[Material])
    case _: List[Material] =>
      registrar.asInstanceOf[List[Material]].foreach(m => materials put (m.toString, m))
    case _: Modifier =>
      modifiers put (registrar.toString, registrar.asInstanceOf[Modifier])
    case _: List[Material] =>
      registrar.asInstanceOf[List[Modifier]].foreach(m => modifiers put (m.toString, m))
    case _ =>
      BoatCraft.log.fatal("Was unable to register: " + registrar.toString)
  }

  def unregister(unregistrant: AnyRef) = unregistrant match {
    case _: Material =>
      materials remove unregistrant.toString
    case _: List[Material] =>
      unregistrant.asInstanceOf[List[Material]].foreach(m => materials remove m.toString)
    case _: Modifier =>
      modifiers remove unregistrant.toString
    case _: List[Material] =>
      unregistrant.asInstanceOf[List[Modifier]].foreach(m => modifiers remove m.toString)
    case _ =>
      BoatCraft.log.error("There was nothing to unregister: " + unregistrant.toString)
  }

}
