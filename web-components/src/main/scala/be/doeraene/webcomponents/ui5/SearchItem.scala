package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.HasItem
import be.doeraene.webcomponents.ui5.eventtypes.HasTargetRef
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.IntAsStringCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.configkeys.IconName

/** A ui5-search-item is a list item, used for displaying search suggestions
  */
object SearchItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def selected: Boolean = js.native
  }

  object RawElement {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/SearchItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-search-item")

  lazy val text: HtmlAttr[String]        = htmlAttr("text", StringAsIsCodec)
  lazy val description: HtmlAttr[String] = htmlAttr("description", StringAsIsCodec)
  lazy val icon: HtmlAttr[IconName]      = htmlAttr("icon", IconName.AsStringCodec)
  lazy val selected: HtmlAttr[Boolean]   = htmlAttr("selected", BooleanAsAttrPresenceCodec)
  lazy val scopeName: HtmlAttr[String]   = htmlAttr("scope-name", StringAsIsCodec)

  object slots {
    val image: Slot = Slot("image")
  }

  object events {
    val onDelete: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("delete")
  }

}
