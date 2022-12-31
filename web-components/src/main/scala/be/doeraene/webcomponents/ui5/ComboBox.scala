package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, ComboBoxFilter, IconName, ValueState}
import be.doeraene.webcomponents.ui5.eventtypes.{EventWithPreciseTarget, HasDetail, HasItem}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
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
  object RawImport extends WebComponent.WithMetadata

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-combobox")

  lazy val disabled: ReactiveHtmlAttr[Boolean]      = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val filter: ReactiveHtmlAttr[ComboBoxFilter] = customHtmlAttr("filter", ComboBoxFilter.AsStringCodec)
  lazy val loading: ReactiveHtmlAttr[Boolean]       = customHtmlAttr("loading", BooleanAsAttrPresenceCodec)
  lazy val placeholder: ReactiveHtmlAttr[String]    = customHtmlAttr("placeholder", StringAsIsCodec)
  lazy val readonly: ReactiveHtmlAttr[Boolean]      = customHtmlAttr("readonly", BooleanAsAttrPresenceCodec)
  lazy val required: ReactiveHtmlAttr[Boolean]      = customHtmlAttr("required", BooleanAsAttrPresenceCodec)
  lazy val valueState: ReactiveHtmlAttr[ValueState] = customHtmlAttr("value-state", ValueState.AsStringCodec)

  object slots {
    val default: Slot           = new Slot("default")
    val icon: Slot              = new Slot("icon")
    val valueStateMessage: Slot = new Slot("valueStateMessage")
  }

  object events {
    val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")
    val onInput: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("input")
    val onSelectionChange: EventProp[dom.Event & HasDetail[HasItem[ComboBoxItem.Ref]]] = new EventProp(
      "selection-change"
    )
  }

  

  def item: ComboBoxItem.type       = ComboBoxItem
  def group: ComboBoxGroupItem.type = ComboBoxGroupItem
}
