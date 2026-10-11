package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.IconName
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.ListItemClickDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** A ui5-search-item is a list item, used for displaying search suggestions inside a [[Search]].
  *
  * Note: this component is experimental in UI5 and its API may change.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/SearchItem/">the doc</a> for more information.
  */
object SearchItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def selected: Boolean        = js.native
    def text: js.UndefOr[String] = js.native
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

  /** Defines whether the search item is deletable (a delete button is then displayed). */
  lazy val deletable: HtmlAttr[Boolean] = htmlAttr("deletable", BooleanAsAttrPresenceCodec)

  object slots {
    val image: Slot = Slot("image")

    /** Additional actions (e.g. [[Button]]s) displayed at the end of the item. */
    val actions: Slot = Slot("actions")
  }

  object events {

    /** Fired when the delete button of a `deletable` item is pressed. */
    val onDelete: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("delete")

    /** Fired when the item is activated (since UI5 2.23.0). */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ListItemClickDetail]] = new EventProp("click")
  }

}
