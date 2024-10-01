package demo.helpers

import com.raquo.laminar.api.L.*
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.html

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** An instance [[Example]] is bound to a specific component, and is used to display its functionalities.
  */
trait Example(val name: String) {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement

  def completeComponent: HtmlElement = div(
    padding := "5px",
    Title.h1(name),
    div(
      "You can see the source code ",
      Link(
        "here",
        _.href := s"https://github.com/sherpal/LaminarSAPUI5Bindings/tree/master/demo/src/main/scala/demo/${name}Example.scala",
        _.target := LinkTarget._blank
      ),
      ". Click ",
      Link(_.href := "?", "here"),
      " to go back home."
    ),
    div(
      child <-- EventStream
        .fromFuture(FetchDemoPanelFromGithub.fetchAllDemoPanelInfo(name))
        .startWith(FetchDemoPanelFromGithub.CompleteDemoPanelInfo(None, Map.empty))
        .map(info => component(using info))
    )
  )

  def missing: HtmlElement = MessageStrip(
    s"$name is currently missing. Don't hesitate to contribue!",
    _.design          := MessageStripDesign.Negative,
    _.hideCloseButton := true
  )

  val loginFormClass = "login-form"

  def styleTagForLoginFormClass: ReactiveHtmlElement[html.Style] = styleTag(s"""
                                              |.$loginFormClass > div {
                                              |    display: grid;
                                              |    width: 15rem;
                                              |    margin-bottom: 0.5rem;
                                              |}
                                              |""".stripMargin)

  def mtgImageWarning: HtmlElement = MessageStrip(
    _.design := MessageStripDesign.Critical,
    "All images displayed on this page are the property of Wizard of the Coast."
  )

  def someIconValues: List[IconName] = List(
    IconName.`clear-all`,
    IconName.`accidental-leave`,
    IconName.`activity-items`,
    IconName.`arrow-bottom`,
    IconName.step,
    IconName.loan,
    IconName.heart,
    IconName.`bar-chart`,
    IconName.`hello-world`,
    IconName.`arrow-top`,
    IconName.`action-settings`,
    IconName.`value-help`,
    IconName.home,
    IconName.palette,
    IconName.contacts
  )

}

object Example {
  given Ordering[Example] = Ordering.by(_.name)
}
