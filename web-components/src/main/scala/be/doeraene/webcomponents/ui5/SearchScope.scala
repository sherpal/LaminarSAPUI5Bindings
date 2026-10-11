package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.IconName
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-search-scope represents the options for the scope in a [[Search]] (to put in its `scopes` slot).
  *
  * Note: this component is experimental in UI5 and its API may change.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/SearchScope/">the doc</a> for more information.
  */
object SearchScope extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def text: String               = js.native
    def value: js.UndefOr[String] = js.native
  }

  object RawElement {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/SearchScope.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-search-scope")

  lazy val text: HtmlAttr[String] = htmlAttr("text", StringAsIsCodec)

  /** Defines the value of the scope. The selected scope is the one whose value is the `scopeValue` of the [[Search]].
    */
  lazy val value: HtmlAttr[String] = htmlAttr("value", StringAsIsCodec)

  @scala.annotation.compileTimeOnly(
    "The selected attribute of ui5-search-scope has been removed. Give a value to each SearchScope (`_.value := ...`) and set `_.scopeValue` on the Search (or ShellBarSearch) instead."
  )
  def selected: HtmlAttr[Boolean] = ???

  @scala.annotation.compileTimeOnly("ui5-search-scope has no description attribute.")
  def description: HtmlAttr[String] = ???

  @scala.annotation.compileTimeOnly("ui5-search-scope has no icon attribute.")
  def icon: HtmlAttr[IconName] = ???

  @scala.annotation.compileTimeOnly("ui5-search-scope has no scope-name attribute. Use value instead.")
  def scopeName: HtmlAttr[String] = ???

  object slots {
    @scala.annotation.compileTimeOnly("ui5-search-scope has no image slot.")
    def image: Slot = ???
  }

  object events {
    @scala.annotation.compileTimeOnly("ui5-search-scope has no delete event.")
    def onDelete: EventProp[EventWithPreciseTarget[Ref]] = ???
  }

}
