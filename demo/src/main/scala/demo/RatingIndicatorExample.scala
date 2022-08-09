package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example}

object RatingIndicatorExample extends Example("RatingIndicator") {

  def component: HtmlElement = div(
    styleTag("""
    |div > ui5-rating-indicator {
    |  margin-right: 2em;
    |}
    |""".stripMargin),
    DemoPanel(
      "Basic Rating Indicator",
      div(
        RatingIndicator(_.events.onChange.map(_.target.value) --> Observer(println)),
        RatingIndicator(_.value := 3),
        RatingIndicator(_.value := 3.7)
      )
    ),
    DemoPanel(
      "Rating Indicator With Different Max Value",
      div(
        RatingIndicator(_.max := 10, _.value := 5),
        RatingIndicator(_.max := 3, _.value := 3)
      )
    ),
    DemoPanel(
      "Disabled Rating Indicator",
      div(
        RatingIndicator(_.value := 4, _.disabled := true),
        RatingIndicator(_.max := 10, _.value := 5, _.disabled := true),
        RatingIndicator(_.value := 6, _.max := 6, _.disabled := true)
      )
    ),
    DemoPanel(
      "Readonly Rating Indicator",
      div(
        RatingIndicator(_.value := 4, _.readonly := true),
        RatingIndicator(_.max := 10, _.value := 5, _.readonly := true),
        RatingIndicator(_.value := 6, _.max := 6, _.readonly := true)
      )
    )
  )

}
