package demo.helpers

import com.raquo.laminar.api.L.*
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import scala.concurrent.ExecutionContext.Implicits.global

/** An instance [[Example]] is bound to a specific component, and is used to display its functionalities.
  */
trait Example(val name: String) {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement

  def completeComponent = div(
    Title(_.level := TitleLevel.H1, _ => name),
    div(
      "You can see the source code ",
      Link(
        _ => "here",
        _.href := s"https://github.com/sherpal/LaminarSAPUI5Bindings/tree/master/demo/src/main/scala/demo/${name}Example.scala",
        _.target := LinkTarget._blank
      ),
      "."
    ),
    div(
      child <-- EventStream
        .fromFuture(FetchDemoPanelFromGithub.fetchAllDemoPanelInfo(name))
        .startWith(FetchDemoPanelFromGithub.CompleteDemoPanelInfo(None, Map.empty))
        .map(info => component(using info))
    )
  )

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
