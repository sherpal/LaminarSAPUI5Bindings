package demo.facades.highlightjs

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("highlight.js/lib/languages/scala", JSImport.Default)
object hljsScala extends HljsLanguage
