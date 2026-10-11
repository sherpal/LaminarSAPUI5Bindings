package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.IconName
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-input-icon component represents an interactive icon that can be placed inside an [[Input]] (or
  * [[MultiInput]]), in its `icon` slot. Unlike the standard [[Icon]], this component provides button-like behavior with
  * hover, focus, and active states, matching the visual style of the input's built-in clear icon.
  *
  * Use it for interactive icons that users can click (e.g., search, voice input, camera). For decorative icons, use
  * [[Icon]] instead.
  *
  * Since UI5 2.25.0.
  *
  * @example
  *   {{{
  *   Input(_.slots.icon := Input.inputIcon(_.name := IconName.search, _.events.onClick --> ...))
  *   }}}
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/InputIcon/">the doc</a> for more information.
  */
object InputIcon extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def name: js.UndefOr[String] = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/InputIcon.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-input-icon")

  /** Name of the icon to display. */
  lazy val name: HtmlAttr[IconName] = htmlAttr("name", IconName.AsStringCodec)

  /** Name of the icon to display, as a raw string (e.g. for icons from other collections). */
  lazy val nameString: HtmlAttr[String] = htmlAttr("name", StringAsIsCodec)

  /** Accessible name of the icon (defaults to the icon's own accessible name). */
  lazy val accessibleName: HtmlAttr[String] = htmlAttr("accessible-name", StringAsIsCodec)

  object slots {}

  object events {

    /** Fired when the icon is activated (mouse click, Enter or Space). */
    val onClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("click")
  }

}
