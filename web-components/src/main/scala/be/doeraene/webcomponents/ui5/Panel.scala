package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName, PanelAccessibleRole, TitleLevel}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-panel component is a container which has a header and a content area and is used for grouping and displaying
  * information. It can be collapsed to save space on the screen.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Panel/">the doc</a> for more information.
  */
object Panel extends WebComponent with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {
    var collapsed: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Panel.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-panel")

  lazy val accessibleRole: HtmlAttr[PanelAccessibleRole] =
    htmlAttr("accessible-role", PanelAccessibleRole.AsStringCodec)

  lazy val collapsed: HtmlAttr[Boolean] = htmlAttr("collapsed", BooleanAsAttrPresenceCodec)
  lazy val fixed: HtmlAttr[Boolean]     = htmlAttr("fixed", BooleanAsAttrPresenceCodec)

  lazy val headerLevel: HtmlAttr[TitleLevel] = htmlAttr("header-level", TitleLevel.AsStringCodec)
  lazy val headerText: HtmlAttr[String]      = htmlAttr("header-text", StringAsIsCodec)

  lazy val noAnimation: HtmlAttr[Boolean] = htmlAttr("no-animation", BooleanAsAttrPresenceCodec)

  object slots {
    val header: Slot = new Slot("header")
  }

  object events {
    val onToggle: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("toggle")
  }

  

}
