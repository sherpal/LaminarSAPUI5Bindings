package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ButtonAccessibleRole
import be.doeraene.webcomponents.ui5.configkeys.ButtonDesign
import be.doeraene.webcomponents.ui5.configkeys.ButtonType
import be.doeraene.webcomponents.ui5.configkeys.IconName
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-button component represents a simple push button. It enables users to trigger actions by clicking or tapping
  * the ui5-button, or by pressing certain keyboard keys, such as Enter.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/components/Button/">the doc</a> for more information.
  */
object Button extends WebComponent with HasIcon {

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
  lazy val submits: HtmlAttr[Boolean]              = htmlAttr("submits", BooleanAsAttrPresenceCodec)
  lazy val tooltip: HtmlAttr[String]               = htmlAttr("tooltip", StringAsIsCodec)
  lazy val tpe: HtmlAttr[ButtonType]               = htmlAttr("type", ButtonType.AsStringCodec)
  lazy val accessibleDescription: HtmlAttr[String] = htmlAttr("accessible-description", StringAsIsCodec)

  @scala.annotation.compileTimeOnly(
    """iconEnd has been removed and replaced by the new endIcon mecanic. If you previously had `Button(_.icon := IconName.upload, _.iconEnd := true, "Upload")`, you now have to replace by `Button(_.endIcon := IconName.upload, "Upload")`"""
  )
  def iconEnd: HtmlAttr[Boolean] = ???

  object slots {
    val badge: Slot = Slot("badge")
  }

  object events {
    val onClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("click")
  }

}
