package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName, ValueState}
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-select component is used to create a drop-down list. The items inside the ui5-select define the available
  * options by using the ui5-option component.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Select/">the doc</a> for more information.
  */
object Select extends WebComponent with HasIcon with HasAccessibleName with HasName with HasValue {

  @js.native
  trait RawElement extends js.Object {
    def selectedOption: dom.HTMLElement = js.native

    def value: String = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Select.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-select")

  lazy val disabled: HtmlAttr[Boolean] = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val required: HtmlAttr[Boolean] = htmlAttr("required", BooleanAsAttrPresenceCodec)
  lazy val readonly: HtmlAttr[Boolean] = htmlAttr("readonly", BooleanAsAttrPresenceCodec)

  lazy val valueState: HtmlAttr[ValueState] = ValueState.asHtmlAttr("value-state")

  object slots {
    val valueStateMessage: Slot = new Slot("valueStateMessage")
  }

  object events {

    @js.native
    sealed trait HasSelectedOption extends js.Any {
      def selectedOption: option.Ref = js.native
    }

    val onChange = new EventProp[dom.Event & HasDetail[HasSelectedOption]]("change")

    val onClose = new EventProp[dom.Event]("close")
    val onOpen  = new EventProp[dom.Event]("open")
  }

  def option: SelectOption.type = SelectOption
  def optionCustom              = SelectOptionCustom

}
