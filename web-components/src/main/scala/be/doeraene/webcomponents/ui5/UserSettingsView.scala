package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** Attributes shared by all the views of a [[UserSettingsItem]]. */
trait UserSettingsViewAttributes extends HasText {

  /** Whether the view is secondary. Only relevant for "page" views (children of the [[UserSettingsItem]]), it controls
    * the visibility of the back button.
    */
  lazy val secondary: HtmlAttr[Boolean] = htmlAttr("secondary", BooleanAsAttrPresenceCodec)

  /** Whether the view is selected. There can be just one selected view at a time. */
  lazy val selected: HtmlAttr[Boolean] = htmlAttr("selected", BooleanAsAttrPresenceCodec)
}

/** The ui5-user-settings-view represents a view displayed in the [[UserSettingsItem]]. Its children are the content of
  * the view.
  *
  * @since UI5 2.8.0
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/UserSettingsView/">the doc</a> for more
  *   information.
  */
object UserSettingsView extends WebComponent with UserSettingsViewAttributes {

  @js.native
  trait RawElement extends js.Object {
    def selected: Boolean        = js.native
    def text: js.UndefOr[String] = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/UserSettingsView.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-user-settings-view")

  object slots {}

  object events {}

}
