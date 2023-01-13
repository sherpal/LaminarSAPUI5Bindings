package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName, ValueState}
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr, ReactiveProp, ReactiveStyle}
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
object Select extends WebComponent with HasIcon with HasAccessibleName with HasName {

  @js.native
  trait RawElement extends js.Object {
    def selectedOption: dom.HTMLElement = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Select.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-select")

  lazy val disabled: HtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val required: HtmlAttr[Boolean] = customHtmlAttr("required", BooleanAsAttrPresenceCodec)

  lazy val valueState: HtmlAttr[ValueState] = customHtmlAttr("value-state", ValueState.AsStringCodec)

  object slots {
    val valueStateMessage: Slot = new Slot("valueStateMessage")
  }

  object events {

    @js.native
    sealed trait HasSelectedOption extends js.Any {
      def selectedOption: option.Ref = js.native
    }

    val onChange = new EventProp[dom.Event & HasDetail[HasSelectedOption]]("change")
  }

  

  def option: SelectOption.type = SelectOption

}
