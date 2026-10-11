package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** A ui5-search is an input with suggestions, used for user search.
  *
  * It consists of an optional scope select (see the `scopes` slot and `scopeValue`), the input field, a clear button,
  * a search button and a list of suggestions ([[SearchItem]]s and [[SearchItemGroup]]s, as children).
  *
  * Note: this component is experimental in UI5 and its API may change.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/Search/">the doc</a> for more information.
  */
object Search extends WebComponent with HasValue with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {
    def value: String = js.native

    def open: Boolean = js.native

    def scopeValue: js.UndefOr[String] = js.native
  }

  object RawElement {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/Search.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-search")

  /** Indicates whether a loading indicator should be shown in the popup. */
  lazy val loading: HtmlAttr[Boolean] = htmlAttr("loading", BooleanAsAttrPresenceCodec)

  /** Indicates whether a loading indicator should be shown in the input field. */
  lazy val fieldLoading: HtmlAttr[Boolean] = htmlAttr("field-loading", BooleanAsAttrPresenceCodec)

  lazy val noTypeahead: HtmlAttr[Boolean]          = htmlAttr("no-typeahead", BooleanAsAttrPresenceCodec)
  lazy val open: HtmlAttr[Boolean]                 = htmlAttr("open", BooleanAsAttrPresenceCodec)
  lazy val showClearIcon: HtmlAttr[Boolean]        = htmlAttr("show-clear-icon", BooleanAsAttrPresenceCodec)
  lazy val placeholder: HtmlAttr[String]           = htmlAttr("placeholder", StringAsIsCodec)
  lazy val accessibleDescription: HtmlAttr[String] = htmlAttr("accessible-description", StringAsIsCodec)

  /** Defines the value of the selected scope, i.e., the `value` of the corresponding [[SearchScope]]. */
  lazy val scopeValue: HtmlAttr[String] = htmlAttr("scope-value", StringAsIsCodec)

  object slots {
    val action: Slot       = Slot("action")
    val illustration: Slot = Slot("illustration")
    val messageArea: Slot  = Slot("messageArea")
    val scopes: Slot       = Slot("scopes")
    val filterButton: Slot = Slot("filterButton")
  }

  object events {

    trait ScopeChangeDetail extends js.Object {
      def scope: js.UndefOr[SearchScope.Ref]
    }

    trait SearchDetail extends js.Object {

      /** The suggestion item that triggered the search, if any. */
      def item: js.UndefOr[SearchItem.Ref]
    }

    val onOpen: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("open")
    val onClose: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("close")
    val onInput: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("input")

    val onScopeChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ScopeChangeDetail]] =
      new EventProp("scope-change")

    /** Fired when the user triggers a search (search button, Enter, or selection of a suggestion item). */
    val onSearch: EventProp[EventWithPreciseTarget[Ref] & HasDetail[SearchDetail]] = new EventProp("search")
  }

  def item: SearchItem.type                 = SearchItem
  def group: SearchItemGroup.type           = SearchItemGroup
  def scope: SearchScope.type               = SearchScope
  def messageArea: SearchMessageArea.type   = SearchMessageArea
  def showMoreItem: SearchItemShowMore.type = SearchItemShowMore

}
