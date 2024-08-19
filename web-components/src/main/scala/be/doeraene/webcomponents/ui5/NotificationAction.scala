package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.ButtonDesign
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-notification-action represents an abstract action, used in the ui5-li-notification and the
  * ui5-li-notification-group items.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/NotificationListItem/">the doc</a> for more
  *   information.
  */
object NotificationAction extends WebComponent with HasText with HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  // todo: migration notification action
  // @js.native
  // @JSImport("@ui5/webcomponents-fiori/dist/NotificationAction.js", JSImport.Default)
  // object RawImport extends js.Object

  // // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  // used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-notification-action")

  lazy val design: HtmlAttr[ButtonDesign] = htmlAttr("design", ButtonDesign.AsStringCodec)

  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {}

}
