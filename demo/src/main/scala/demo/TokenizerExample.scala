package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object TokenizerExample extends Example("Tokenizer") {

  //-- Begin Common
  private val countries =
    List("Argentina", "Belgium", "Bulgaria", "Canada", "Columbia", "Croatia", "Denmark", "France", "Germany", "Italy")
  //-- End Common

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    MessageStrip(
      _.design := MessageStripDesign.Information,
      "The Tokenizer component is experimental in UI5 and its API may change."
    ),
    DemoPanel("Basic Tokenizer") {
      //-- Begin: Basic Tokenizer
      val tokensVar: Var[List[String]] = Var(countries)
      div(
        Tokenizer(
          width := "300px",
          children <-- tokensVar.signal.split(identity)((text, _, _) => Tokenizer.token(_.text := text)),
          _.events.onTokenDelete.map(_.detail.tokens.map(_.text).toSet) --> tokensVar.updater[Set[String]](
            (tokens, deleted) => tokens.filterNot(deleted.contains)
          )
        ),
        Button(
          marginLeft := "1em",
          "Reset tokens",
          _.events.onClick.mapTo(countries) --> tokensVar.writer
        )
      )
      //-- End
    },
    DemoPanel("Readonly and Disabled Tokenizer") {
      //-- Begin: Readonly and Disabled Tokenizer
      div(
        Tokenizer(_.readonly := true, width := "300px", countries.take(4).map(c => Tokenizer.token(_.text := c))),
        Tokenizer(_.disabled := true, width := "300px", countries.take(4).map(c => Tokenizer.token(_.text := c)))
      )
      //-- End
    },
    DemoPanel("Multi-line Tokenizer with Clear All") {
      //-- Begin: Multi-line Tokenizer with Clear All
      val tokensVar: Var[List[String]]   = Var(countries)
      val selectedVar: Var[List[String]] = Var(Nil)
      div(
        Tokenizer(
          _.multiLine    := true,
          _.showClearAll := true,
          width          := "300px",
          children <-- tokensVar.signal.split(identity)((text, _, _) => Tokenizer.token(_.text := text)),
          _.events.onTokenDelete.map(_.detail.tokens.map(_.text).toSet) --> tokensVar.updater[Set[String]](
            (tokens, deleted) => tokens.filterNot(deleted.contains)
          ),
          _.events.onSelectionChange.map(_.detail.tokens.map(_.text)) --> selectedVar.writer
        ),
        div("Selected tokens: ", child.text <-- selectedVar.signal.map(_.mkString(", ")))
      )
      //-- End
    }
  )

}
