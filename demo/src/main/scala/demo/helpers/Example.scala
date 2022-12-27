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
    IconName.`action-settings`,
    IconName.`value-help`,
    IconName.home,
    IconName.palette,
    IconName.contacts,
    IconName.`business-objects-experience`,
    IconName.`add-document`,
    IconName.`add-folder`,
    IconName.`open-folder`,
    IconName.save,
    IconName.`journey-arrive`,
    IconName.card,
    IconName.sound,
    IconName.`nutrition-activity`,
    IconName.world,
    IconName.flight
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

  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/nutrition-activity.js", JSImport.Default)
  object `nutrition-activity` extends js.Object

  `nutrition-activity`


  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/world.js", JSImport.Default)
  object `world` extends js.Object

  `world`

  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/action-settings.js", JSImport.Default)
  object `action-settings` extends js.Object

  `action-settings`


  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/add-document.js", JSImport.Default)
  object `add-document` extends js.Object

  `add-document`


  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/add-folder.js", JSImport.Default)
  object `add-folder` extends js.Object

  `add-folder`


  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/open-folder.js", JSImport.Default)
  object `open-folder` extends js.Object

  `open-folder`


  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/save.js", JSImport.Default)
  object `save` extends js.Object

  `save`


  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/journey-arrive.js", JSImport.Default)
  object `journey-arrive` extends js.Object

  `journey-arrive`

  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/palette.js", JSImport.Default)
  object palette extends js.Object

  palette

  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/value-help.js", JSImport.Default)
  object `value-help` extends js.Object

  `value-help`

  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/home.js", JSImport.Default)
  object `home` extends js.Object

  `home`
  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/flight.js", JSImport.Default)
  object `flight` extends js.Object

  `flight`
  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/contacts.js", JSImport.Default)
  object `contacts` extends js.Object

  `contacts`

  @js.native
  @JSImport("@ui5/webcomponents-icons/dist/business-objects-experience.js", JSImport.Default)
  object `business-objects-experience` extends js.Object

  `business-objects-experience`


}
