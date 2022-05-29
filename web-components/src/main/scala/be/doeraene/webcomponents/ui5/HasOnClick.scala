package be.doeraene.webcomponents.ui5

import com.raquo.laminar.api.L.EventProp
import org.scalajs.dom

trait HasOnClick {
  val onClick = new EventProp[dom.MouseEvent]("click")
}
