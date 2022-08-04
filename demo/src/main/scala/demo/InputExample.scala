package demo

import be.doeraene.webcomponents.ui5.*
import com.raquo.laminar.api.L.*

object InputExample {

  def apply(): HtmlElement = Input(_.placeholder := "Fill Me")

}
