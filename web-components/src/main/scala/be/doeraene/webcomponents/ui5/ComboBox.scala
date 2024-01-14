package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, ComboBoxFilter, IconName, ValueState}
import be.doeraene.webcomponents.ui5.eventtypes.{EventWithPreciseTarget, HasDetail, HasItem}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-combobox component represents a drop-down menu with a list of the available options and a text input field
  * to narrow down the options. It is commonly used to enable users to select an option from a predefined list.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/ComboBox/">the doc</a> for more
  *   information.
  */
object ComboBox extends WebComponent with HasAccessibleName with HasValue {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/ComboBox.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-combobox")

  lazy val disabled: HtmlAttr[Boolean]      = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val filter: HtmlAttr[ComboBoxFilter] = htmlAttr("filter", ComboBoxFilter.AsStringCodec)
  lazy val loading: HtmlAttr[Boolean]       = htmlAttr("loading", BooleanAsAttrPresenceCodec)
  lazy val placeholder: HtmlAttr[String]    = htmlAttr("placeholder", StringAsIsCodec)
  lazy val readonly: HtmlAttr[Boolean]      = htmlAttr("readonly", BooleanAsAttrPresenceCodec)
  lazy val required: HtmlAttr[Boolean]      = htmlAttr("required", BooleanAsAttrPresenceCodec)
  lazy val valueState: HtmlAttr[ValueState] = htmlAttr("value-state", ValueState.AsStringCodec)
  lazy val noTypeahead: HtmlAttr[Boolean]   = htmlAttr("no-typeahead", BooleanAsAttrPresenceCodec)

  object slots {
    val default: Slot           = new Slot("default")
    val icon: Slot              = new Slot("icon")
    val valueStateMessage: Slot = new Slot("valueStateMessage")
  }

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
    val onInput: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("input")
    val onSelectionChange: EventProp[dom.Event with HasDetail[HasItem[ComboBoxItem.Ref]]] = new EventProp(
      "selection-change"
    )
  }

  def item: ComboBoxItem.type       = ComboBoxItem
  def group: ComboBoxGroupItem.type = ComboBoxGroupItem
}
