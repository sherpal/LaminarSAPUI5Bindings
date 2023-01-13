package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, ComboBoxFilter, IconName, ValueState}
import be.doeraene.webcomponents.ui5.eventtypes.{EventWithPreciseTarget, HasDetail, HasItems}
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

/** The ui5-combobox component represents a drop-down menu with a list of the available options and a text input field
  * to narrow down the options. It is commonly used to enable users to select an option from a predefined list.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/ComboBox/">the doc</a> for more
  *   information.
  */
object MultiComboBox extends WebComponent with HasAccessibleName with HasValue {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/MultiComboBox.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-multi-combobox")

  lazy val disabled: HtmlAttr[Boolean]      = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val filter: HtmlAttr[ComboBoxFilter] = htmlAttr("filter", ComboBoxFilter.AsStringCodec)
  lazy val placeholder: HtmlAttr[String]    = htmlAttr("placeholder", StringAsIsCodec)
  lazy val readonly: HtmlAttr[Boolean]      = htmlAttr("readonly", BooleanAsAttrPresenceCodec)
  lazy val allowCustomValues: HtmlAttr[Boolean] =
    htmlAttr("allow-custom-values", BooleanAsAttrPresenceCodec)
  lazy val required: HtmlAttr[Boolean]      = htmlAttr("required", BooleanAsAttrPresenceCodec)
  lazy val valueState: HtmlAttr[ValueState] = htmlAttr("value-state", ValueState.AsStringCodec)

  object slots {
    val default: Slot           = new Slot("default")
    val icon: Slot              = new Slot("icon")
    val valueStateMessage: Slot = new Slot("valueStateMessage")
  }

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
    val onInput: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("input")
    val onSelectionChange: EventProp[dom.Event & HasDetail[HasItems[MultiComboBoxItem.Ref]]] = new EventProp(
      "selection-change"
    )
  }

  

  def item: MultiComboBoxItem.type       = MultiComboBoxItem
  def group: MultiComboBoxGroupItem.type = MultiComboBoxGroupItem
}
