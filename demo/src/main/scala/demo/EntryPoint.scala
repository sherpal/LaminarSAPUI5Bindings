package demo

import be.doeraene.webcomponents.ui5.*
import com.raquo.laminar.api.L.*
import org.scalajs.dom
import org.scalajs.dom.URL

object EntryPoint {
  def main(args: Array[String]): Unit = {
    val componentsDemo: Map[String, () => HtmlElement] = Map(
      "Avatar" -> AvatarExample.apply,
      "Input"  -> InputExample.apply
    )

    val componentName = new URL(dom.document.location.href).pathname.dropWhile(_ == '/')

    render(
      dom.document.getElementById("root"),
      if componentName == "" then
        div(
          h1("Please chose one of the following component below:"),
          ul(
            componentsDemo.keys.toList.map(componentName => li(a(componentName, href := s"/$componentName")))
          )
        )
      else componentsDemo.getOrElse(componentName, () => div("Not Found"))()
    )
  }
}
