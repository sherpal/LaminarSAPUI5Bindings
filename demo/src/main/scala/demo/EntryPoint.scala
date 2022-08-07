package demo

import be.doeraene.webcomponents.ui5.*
import com.raquo.laminar.api.L.*
import org.scalajs.dom
import org.scalajs.dom.URL
import demo.helpers.Example

object EntryPoint {
  def main(args: Array[String]): Unit = {
    val componentsDemo: List[Example] = List(
      AvatarExample,
      BadgeExample,
      BarExample,
      BreadcrumbsExample,
      BusyIndicatorExample,
      ButtonExample,
      CardExample,
      CarouselExample,
      CheckBoxExample,
      ColourPaletteExample,
      ColourPalettePopoverExample,
      ColourPickerExample,
      ComboBoxExample,
      DatePickerExample,
      DateRangePickerExample,
      DateTimePickerExample,
      DialogExample,
      DynamicSideContentExample,
      FileUploaderExample,
      FlexibleColumnLayoutExample,
      InputExample
    ).sorted

    val componentName = new URL(dom.document.location.href).pathname.dropWhile(_ == '/')

    render(
      dom.document.getElementById("root"),
      if componentName == "" then
        div(
          h1("Please chose one of the following component below:"),
          ul(
            componentsDemo.map(_.name).map(componentName => li(a(componentName, href := s"/$componentName")))
          )
        )
      else
        div(
          padding := "10px",
          componentsDemo.find(_.name == componentName).map(_.component).getOrElse(div("Not Found"))
        )
    )
  }
}
