package be.doeraene.webcomponents

import com.raquo.laminar.api.L.*
import com.raquo.laminar.keys.ReactiveProp

/** Marker trait that all web components inherit.
  *
  * This can allow you to implement some shenanigans and abstract over some thins.
  */
trait WebComponent {
  val id: ReactiveProp[String, String] = idAttr
}
