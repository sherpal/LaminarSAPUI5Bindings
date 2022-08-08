package demo.helpers

import com.raquo.laminar.api.L.*
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*

/** An instance [[Example]] is bound to a specific component, and is used to display its functionalities.
  */
trait Example(val name: String) {

  def component: HtmlElement

  def missing: HtmlElement = MessageStrip(
    _ => s"$name is currently missing. Don't hesitate to contribue!",
    _.design := MessageStripDesign.Negative,
    _.hideCloseButton := true
  )

  val loginFormClass = "login-form"

  def styleTagForLoginFormClass = styleTag(s"""
                                              |.$loginFormClass > div {
                                              |    display: grid;
                                              |    width: 15rem;
                                              |    margin-bottom: 0.5rem;
                                              |}
                                              |""".stripMargin)

}

object Example {
  given Ordering[Example] = Ordering.by(_.name)
}