package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ListMode, ListSeparator}
import be.doeraene.webcomponents.ui5.internal.Slot
import be.doeraene.webcomponents.ui5.eventtypes.*
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-list component allows displaying a list of items, advanced keyboard handling support for navigating between
  * items, and predefined modes to improve the development efficiency.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/List/">the doc</a> for more information.
  */
object UList {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/List.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = UList.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-list")

  val id: ReactiveProp[String, String] = idAttr

  val busy: ReactiveHtmlAttr[Boolean]             = customHtmlAttr("busy", BooleanAsAttrPresenceCodec)
  val headerText: ReactiveHtmlAttr[String]        = customHtmlAttr("header-text", StringAsIsCodec)
  val mode: ReactiveHtmlAttr[ListMode]            = customHtmlAttr("mode", ListMode.AsStringCodec)
  val separators: ReactiveHtmlAttr[ListSeparator] = customHtmlAttr("separators", ListSeparator.AsStringCodec)
  val noDataText: ReactiveHtmlAttr[String]        = customHtmlAttr("no-data-text", StringAsIsCodec)

  object events {
    val onItemClick = new EventProp[dom.Event & HasDetail[HasItem[Li.Ref]]]("item-click")
  }

  object slots {
    val header: Slot = new Slot("header")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(UList)): _*)

  val Li: ListItem.type = ListItem

}
