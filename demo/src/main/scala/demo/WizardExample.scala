package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}
import com.raquo.laminar.nodes.ReactiveHtmlElement

object WizardExample extends Example("Wizard") {

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
        _ => s"Go to step $goTo",
        _.events.onClick.mapTo(goTo) --> currentStepVar.writer,
        _ => hidden <-- hiddenObservable
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
            _ => commonModifiers(1),
            _ => Title.h3(_ => "1. Greeting"),
            _ =>
              MessageStrip(
                _.hideCloseButton := true,
                _.design := MessageStripDesign.Information,
                _ =>
                  "The Wizard control is supposed to break down large tasks, into smaller steps, easier for the " +
                    "user to work with."
              ),
            _ =>
              div(
                "This document is the ultimate authority for Magic: The Gathering® competitive game play. It " +
                  "consists of a series of numbered rules followed by a glossary. Many of the numbered rules are " +
                  "divided into subrules, and each separate rule and subrule of the game has its own number. (Note " +
                  "that subrules skip the letters “l” and “o” due to potential confusion with the numbers “1” and “0”;" +
                  " subrule 704.5k is followed by 704.5m, then 704.5n, then 704.5p, for example.)"
              ),
            _ => goToStepButton(2)
          ),
          _.step(
            _.icon := IconName.employee,
            _.titleText := "2. User name",
            _ => commonModifiers(2),
            _ => Title.h3(_ => "2. User name"),
            _ =>
              div(
                "Changes may have been made to this document since its publication. You can download the most " +
                  "recent version from the Magic rules website at Magic.Wizards.com/Rules. If you have questions, " +
                  "you can get the answers from us at Support.Wizards.com."
              ),
            _ =>
              div(
                className := loginFormClass,
                div(
                  Label(_ => "Fill in your name to continue:", _.required := true),
                  Input(
                    _.events.onChange
                      .map(_.target.value)
                      .filter(_.trim.nonEmpty) --> maybeInfoForStepThreeVar.writer.contramapSome
                  )
                )
              ),
            _ => goToStepButton(3, maybeInfoForStepThreeVar.signal.map(_.isEmpty))
          ),
          _.step(
            _.icon := IconName.`action-settings`,
            _.titleText := "3. User profile",
            _ => commonModifiers(3),
            _ =>
              Title.h3(_ =>
                child.text <-- maybeInfoForStepThreeVar.signal.changes
                  .collect { case Some(name) => name }
                  .map(name => s"3. User profile: $name")
              ),
            _ => div("Here the user could fill some optional settings for their profile..."),
            _ => goToStepButton(4)
          ),
          _.step(
            _.icon := IconName.`chart-table-view`,
            _.titleText := "4. Last Details",
            _ => commonModifiers(4),
            _ => Title.h4(_ => "4. Last Details"),
            _ => div("Here we ask a few last things and then move along with their lives."),
            _ =>
              Button(
                _.design := ButtonDesign.Positive,
                _ => "Finish!",
                _.events.onClick.mapTo(()) --> finishDialogBus.writer
              )
          )
        ),
        Dialog(
          _.showFromEvents(finishDialogBus.events),
          _.closeFromEvents(finishDialogCloseBus.events),
          _ => div("Process finished!"),
          _ =>
            Button(
              _.design := ButtonDesign.Emphasized,
              _ => "Done",
              _.events.onClick.mapTo(()) --> finishDialogCloseBus.writer
            )
        )
      )
      //-- End
    }
  )

}
