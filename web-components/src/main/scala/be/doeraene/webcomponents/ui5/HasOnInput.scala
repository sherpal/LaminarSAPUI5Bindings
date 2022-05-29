package be.doeraene.webcomponents.ui5

import com.raquo.laminar.api.L.EventProp
import org.scalajs.dom

trait HasOnInput {
  val onInput = new EventProp[dom.Event]("input")
}
