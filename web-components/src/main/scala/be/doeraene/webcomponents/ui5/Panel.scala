package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName, PanelAccessibleRole, TitleLevel}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-panel component is a container which has a header and a content area and is used for grouping and displaying
  * information. It can be collapsed to save space on the screen.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Panel/">the doc</a> for more information.
  */
object Panel extends HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {
    var collapsed: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Panel.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = Panel.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-panel")

  val id: ReactiveProp[String, String] = idAttr

  val accessibleRole: ReactiveHtmlAttr[PanelAccessibleRole] =
    customHtmlAttr("accessible-role", PanelAccessibleRole.AsStringCodec)

  val collapsed: ReactiveHtmlAttr[Boolean] = customHtmlAttr("collapsed", BooleanAsAttrPresenceCodec)
  val fixed: ReactiveHtmlAttr[Boolean]     = customHtmlAttr("fixed", BooleanAsAttrPresenceCodec)

  val headerLevel: ReactiveHtmlAttr[TitleLevel] = customHtmlAttr("header-level", TitleLevel.AsStringCodec)
  val headerText: ReactiveHtmlAttr[String]      = customHtmlAttr("header-text", StringAsIsCodec)

  val noAnimation: ReactiveHtmlAttr[Boolean] = customHtmlAttr("no-animation", BooleanAsAttrPresenceCodec)

  object slots {
    val header: Slot = new Slot("header")
  }

  object events {
    val onToggle: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("toggle")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Panel)): _*)

}
