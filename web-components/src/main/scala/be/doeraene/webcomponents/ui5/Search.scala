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

/** A ui5-search is an input with suggestions, used for user search.
  */
object Search extends WebComponent with HasValue with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {
    def value: String = js.native
  }

  object RawElement {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/Search.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-search")

  lazy val loading: HtmlAttr[Boolean]              = htmlAttr("loading", BooleanAsAttrPresenceCodec)
  lazy val noTypeahead: HtmlAttr[Boolean]          = htmlAttr("no-typeahead", BooleanAsAttrPresenceCodec)
  lazy val open: HtmlAttr[Boolean]                 = htmlAttr("open", BooleanAsAttrPresenceCodec)
  lazy val showClearIcon: HtmlAttr[Boolean]        = htmlAttr("show-clear-icon", BooleanAsAttrPresenceCodec)
  lazy val placeholder: HtmlAttr[String]           = htmlAttr("placeholder", StringAsIsCodec)
  lazy val accessibleDescription: HtmlAttr[String] = htmlAttr("accessible-description", StringAsIsCodec)

  object slots {
    val action: Slot       = Slot("action")
    val illustration: Slot = Slot("illustration")
    val messageArea: Slot  = Slot("messageArea")
    val scopes: Slot       = Slot("scopes")
    val filterButton: Slot = Slot("filterButton")
  }

  object events {
    val onOpen: EventProp[EventWithPreciseTarget[Ref]]        = new EventProp("open")
    val onClose: EventProp[EventWithPreciseTarget[Ref]]       = new EventProp("close")
    val onInput: EventProp[EventWithPreciseTarget[Ref]]       = new EventProp("input")
    val onScopeChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("scope-change")
    val onSearch: EventProp[EventWithPreciseTarget[Ref]]      = new EventProp("Search")
  }

  def item: SearchItem.type       = SearchItem
  def group: SearchItemGroup.type = SearchItemGroup
  def scope: SearchScope.type     = SearchScope

}
