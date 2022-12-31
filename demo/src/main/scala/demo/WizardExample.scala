package demo

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import com.raquo.laminar.nodes.ReactiveHtmlElement

object WizardExample extends Example("Wizard") {

  def webComponent: WebComponent = Wizard

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    styleTagForLoginFormClass,
    DemoPanel("Wizard") {
      //-- Begin: Wizard
      val currentStepVar: Var[Int] = Var(1)

      // Singal indicating whether the specified step number is currently selected.
      def isSelectedStepSignal(stepNumber: Int) = currentStepVar.signal.map(_ == stepNumber)

      // Biggest step that was seen up to now
      val maxSeenStep = currentStepVar.signal.foldLeft(identity)(_ max _)

      // Modifiers for all the steps
      def commonModifiers(stepNumber: Int): Mod[ReactiveHtmlElement[WizardStep.Ref]] = List[WizardStep.ModFunction](
        _.selected <-- isSelectedStepSignal(stepNumber),
        _.disabled <-- maxSeenStep.map(stepNumber > _),
        _ => dataAttr("step-number") := stepNumber.toString
      ).map(_(WizardStep))

      // Creates a button that, when clicking, go to the specified step. Can be hidden temporarily with the observable
      def goToStepButton(goTo: Int, hiddenObservable: Observable[Boolean] = Val(false)) = Button(
        _.design := ButtonDesign.Emphasized,
        s"Go to step $goTo",
        _.events.onClick.mapTo(goTo) --> currentStepVar.writer,
        hidden <-- hiddenObservable
      )

      // User will not be able to go to step 3 before this is filled.
      val maybeInfoForStepThreeVar: Var[Option[String]] = Var(None)

      // This bus will be fed at the very end when the process is done.
      val finishDialogBus: EventBus[Unit]      = new EventBus
      val finishDialogCloseBus: EventBus[Unit] = new EventBus

      div(
        Wizard(
          _.events.onStepChange.map(_.detail.step.dataset("stepNumber").toInt) --> currentStepVar.writer,
          _.step(
            _.icon := IconName.home,
            _.titleText := "Greeting",
            commonModifiers(1),
            Title.h3("1. Greeting"),
                          MessageStrip(
                _.hideCloseButton := true,
                _.design := MessageStripDesign.Information,
                                  "The Wizard control is supposed to break down large tasks, into smaller steps, easier for the " +
                    "user to work with."
              ),
                          div(
                "This document is the ultimate authority for Magic: The Gathering® competitive game play. It " +
                  "consists of a series of numbered rules followed by a glossary. Many of the numbered rules are " +
                  "divided into subrules, and each separate rule and subrule of the game has its own number. (Note " +
                  "that subrules skip the letters “l” and “o” due to potential confusion with the numbers “1” and “0”;" +
                  " subrule 704.5k is followed by 704.5m, then 704.5n, then 704.5p, for example.)"
              ),
            goToStepButton(2)
          ),
          _.step(
            _.icon := IconName.employee,
            _.titleText := "2. User name",
            commonModifiers(2),
            Title.h3("2. User name"),
                          div(
                "Changes may have been made to this document since its publication. You can download the most " +
                  "recent version from the Magic rules website at Magic.Wizards.com/Rules. If you have questions, " +
                  "you can get the answers from us at Support.Wizards.com."
              ),
                          div(
                className := loginFormClass,
                div(
                  Label("Fill in your name to continue:", _.required := true),
                  Input(
                    _.events.onChange
                      .map(_.target.value)
                      .filter(_.trim.nonEmpty) --> maybeInfoForStepThreeVar.writer.contramapSome
                  )
                )
              ),
            goToStepButton(3, maybeInfoForStepThreeVar.signal.map(_.isEmpty))
          ),
          _.step(
            _.icon := IconName.`action-settings`,
            _.titleText := "3. User profile",
            commonModifiers(3),
                          Title.h3(                child.text <-- maybeInfoForStepThreeVar.signal.changes
                  .collect { case Some(name) => name }
                  .map(name => s"3. User profile: $name")
              ),
            div("Here the user could fill some optional settings for their profile..."),
            goToStepButton(4)
          ),
          _.step(
            _.icon := IconName.`chart-table-view`,
            _.titleText := "4. Last Details",
            commonModifiers(4),
            Title.h4("4. Last Details"),
            div("Here we ask a few last things and then move along with their lives."),
                          Button(
                _.design := ButtonDesign.Positive,
                "Finish!",
                _.events.onClick.mapTo(()) --> finishDialogBus.writer
              )
          )
        ),
        Dialog(
          _.showFromEvents(finishDialogBus.events),
          _.closeFromEvents(finishDialogCloseBus.events),
          div("Process finished!"),
                      Button(
              _.design := ButtonDesign.Emphasized,
              "Done",
              _.events.onClick.mapTo(()) --> finishDialogCloseBus.writer
            )
        )
      )
      //-- End
    }
  )

}
