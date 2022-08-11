package demo.facades.highlightjs

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("highlight.js/lib/core", JSImport.Default)
object hljs extends js.Object {

  def highlightElement(element: dom.HTMLElement): Unit = js.native

  def registerLanguage(name: String, language: HljsLanguage): Unit = js.native

}
