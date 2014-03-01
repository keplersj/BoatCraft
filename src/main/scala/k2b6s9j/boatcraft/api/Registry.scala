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
      BoatCraft log error("Was unable to register: " + registrar.toString)
  }

}
