package demo.helpers

import com.raquo.laminar.api.L.*

/** An instance [[Example]] is bound to a specific component, and is used to display its functionalities.
  */
trait Example(val name: String) {

  def component: HtmlElement

}

object Example {
  given Ordering[Example] = Ordering.by(_.name)
}
