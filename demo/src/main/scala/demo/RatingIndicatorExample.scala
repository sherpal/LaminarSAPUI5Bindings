package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object RatingIndicatorExample extends Example("RatingIndicator") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    styleTag("""
    |div > ui5-rating-indicator {
    |  margin-right: 2em;
    |}
    |""".stripMargin),
    DemoPanel("Basic Rating Indicator")(
      //-- Begin: Basic Rating Indicator
      div(
        RatingIndicator(_.events.onChange.map(_.target.value) --> Observer(println)),
        RatingIndicator(_.value := 3),
        RatingIndicator(_.value := 3.7)
      )
      //-- End
    ),
    DemoPanel("Rating Indicator With Different Max Value")(
      //-- Begin: Rating Indicator With Different Max Value
      div(
        RatingIndicator(_.max := 10, _.value := 5),
        RatingIndicator(_.max := 3, _.value  := 3)
      )
      //-- End
    ),
    DemoPanel("Disabled Rating Indicator")(
      //-- Begin: Disabled Rating Indicator
      div(
        RatingIndicator(_.value := 4, _.disabled := true),
        RatingIndicator(_.max   := 10, _.value   := 5, _.disabled := true),
        RatingIndicator(_.value := 6, _.max      := 6, _.disabled := true)
      )
      //-- End
    ),
    DemoPanel("Readonly Rating Indicator")(
      //-- Begin: Readonly Rating Indicator
      div(
        RatingIndicator(_.value := 4, _.readonly := true),
        RatingIndicator(_.max   := 10, _.value   := 5, _.readonly := true),
        RatingIndicator(_.value := 6, _.max      := 6, _.readonly := true)
      )
      //-- End
    ),
    DemoPanel("Rating Indicator with tooltip") {
      //-- Begin: Rating Indicator with tooltip
      val valueVar = Var(0)
      def tooltipContent(value: Int): String =
        value match {
          case 0 => "Unspeakably bad"
          case 1 => "Very bad"
          case 2 => "Bad"
          case 3 => "Average"
          case 4 => "Good"
          case 5 => "Very good"
          case _ => ""
        }
      div(
        RatingIndicator(
          _.value   <-- valueVar.signal.distinct.map(_.toDouble),
          _.tooltip <-- valueVar.signal.map(_.toInt).distinct.map(tooltipContent),
          _.events.onChange.map(_.target.value.toInt) --> valueVar.writer
        )
      )
      //-- End
    }
  )

}
