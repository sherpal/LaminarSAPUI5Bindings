package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ButtonAccessibleRole
import be.doeraene.webcomponents.ui5.configkeys.ButtonDesign
import be.doeraene.webcomponents.ui5.configkeys.ButtonType
import be.doeraene.webcomponents.ui5.configkeys.IconName
import be.doeraene.webcomponents.ui5.datatypes.AccessibilityAttributes
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-button component represents a simple push button. It enables users to trigger actions by clicking or tapping
  * the ui5-button, or by pressing certain keyboard keys, such as Enter.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/Button/">the doc</a> for more information.
  */
object Button extends WebComponent with HasIcon with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Button.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-button")

  lazy val accessibleRole: HtmlAttr[ButtonAccessibleRole] =
    htmlAttr("accessible-role", ButtonAccessibleRole.AsStringCodec)

  lazy val design: HtmlAttr[ButtonDesign]          = htmlAttr("design", ButtonDesign.AsStringCodec)
  lazy val disabled: HtmlAttr[Boolean]             = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val endIcon: HtmlAttr[IconName]             = htmlAttr("end-icon", IconName.AsStringCodec)
  lazy val iconOnly: HtmlAttr[Boolean]             = htmlAttr("icon-only", BooleanAsAttrPresenceCodec)
  lazy val tooltip: HtmlAttr[String]               = htmlAttr("tooltip", StringAsIsCodec)
  lazy val tpe: HtmlAttr[ButtonType]               = htmlAttr("type", ButtonType.AsStringCodec)
  lazy val accessibleDescription: HtmlAttr[String] = htmlAttr("accessible-description", StringAsIsCodec)

  /** Id of the form element with which the button is associated (only relevant when `tpe` is `Submit` or `Reset`).
    *
    * @since UI5 2.21.0
    */
  lazy val form: HtmlAttr[String] = htmlAttr("form", StringAsIsCodec)

  /** When `true`, a busy indicator is displayed on the button (and it can't be clicked).
    *
    * @since UI5 2.13.0
    */
  lazy val loading: HtmlAttr[Boolean] = htmlAttr("loading", BooleanAsAttrPresenceCodec)

  /** Delay before the busy indicator is shown when `loading` is `true` (default: 1000 ms).
    *
    * @since UI5 2.13.0
    */
  lazy val loadingDelay: HtmlAttr[FiniteDuration] = htmlAttr("loading-delay", FiniteDurationCodec)

  /** Additional accessibility attributes. Supported fields: `expanded`, `hasPopup`, `controls`, `ariaKeyShortcuts` and
    * `ariaLabel`.
    */
  lazy val accessibilityAttributes: HtmlProp[AccessibilityAttributes, AccessibilityAttributes] =
    AccessibilityAttributes.asHtmlProp

  @deprecated(
    "Set the \"type\" property to \"Submit\" to achieve the same result. The \"submits\" property is ignored if \"type\" is set to any value other than \"Button\".",
    since = "1.15.0"
  )
  lazy val submits: HtmlAttr[Boolean] = htmlAttr("submits", BooleanAsAttrPresenceCodec)

  @scala.annotation.compileTimeOnly(
    """iconEnd has been removed and replaced by the new endIcon mecanic. If you previously had `Button(_.icon := IconName.upload, _.iconEnd := true, "Upload")`, you now have to replace by `Button(_.endIcon := IconName.upload, "Upload")`"""
  )
  def iconEnd: HtmlAttr[Boolean] = ???

  object slots {
    val badge: Slot = Slot("badge")
  }

  object events {
    trait ClickDetail extends js.Object {

      /** The original (native) event that triggered the click. */
      def originalEvent: dom.Event

      def altKey: Boolean
      def ctrlKey: Boolean
      def metaKey: Boolean
      def shiftKey: Boolean
    }

    /** Fired when the component is activated either with a mouse/tap or by using the Enter or Space key.
      *
      * Note: the event will not be fired if the `disabled` (or `loading`) property is set to `true`. Calling
      * `preventDefault()` on the event prevents form submission/reset (when `tpe` is `Submit`/`Reset`).
      */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ClickDetail]] = new EventProp("click")
  }

}
