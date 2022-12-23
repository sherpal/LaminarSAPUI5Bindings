package demo.helpers

import com.raquo.laminar.api.L.*
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** An instance [[Example]] is bound to a specific component, and is used to display its functionalities.
  */
trait Example(val name: String) {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement

  def completeComponent = div(
    Title(_.level := TitleLevel.H1, name),
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

  def mtgImageWarning = MessageStrip(
    _.design := MessageStripDesign.Warning,
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
    IconName.card,
    IconName.sound
  )

}

object Example {
  given Ordering[Example] = Ordering.by(_.name)

  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/clear-all.js", JSImport.Default)
  object `clear-all` extends js.Object

  `clear-all`


  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/accidental-leave.js", JSImport.Default)
  object `accidental-leave` extends js.Object

  `accidental-leave`


  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/activity-items.js", JSImport.Default)
  object `activity-items` extends js.Object

  `activity-items`


  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/arrow-bottom.js", JSImport.Default)
  object `arrow-bottom` extends js.Object

  `arrow-bottom`


  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/step.js", JSImport.Default)
  object `step` extends js.Object

  `step`


  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/loan.js", JSImport.Default)
  object `loan` extends js.Object

  `loan`


  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/heart.js", JSImport.Default)
  object `heart` extends js.Object

  `heart`


  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/bar-chart.js", JSImport.Default)
  object `bar-chart` extends js.Object

  `bar-chart`


  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/hello-world.js", JSImport.Default)
  object `hello-world` extends js.Object

  `hello-world`


  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/arrow-top.js", JSImport.Default)
  object `arrow-top` extends js.Object

  `arrow-top`


  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/card.js", JSImport.Default)
  object `card` extends js.Object

  `card`


  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/sound.js", JSImport.Default)
  object `sound` extends js.Object

  `sound`

}
