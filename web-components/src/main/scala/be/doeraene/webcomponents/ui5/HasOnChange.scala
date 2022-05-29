package be.doeraene.webcomponents.ui5

import com.raquo.laminar.api.L.EventProp
import org.scalajs.dom

trait HasOnChange {
  val onChange = new EventProp[dom.Event]("change")
}
