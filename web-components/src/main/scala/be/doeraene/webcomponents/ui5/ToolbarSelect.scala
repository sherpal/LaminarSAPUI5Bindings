package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.ui5.eventtypes.{EventWithPreciseTarget, HasDetail}
import be.doeraene.webcomponents.ui5.internal.Slot
import be.doeraene.webcomponents.ui5.scaladsl.csssize.CSSSize
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-toolbar-select represents a select, used in the [[Toolbar]].
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/ToolbarSelect/">the doc</a> for more information.
  */
object ToolbarSelect extends WebComponent with HasAccessibleName with HasValue with HasToolbarOverflow {

  @js.native
  trait RawElement extends js.Object {
    def value: String = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/ToolbarSelect.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-toolbar-select")

  lazy val disabled: HtmlAttr[Boolean]      = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val valueState: HtmlAttr[ValueState] = htmlAttr("value-state", ValueState.AsStringCodec)
  lazy val widthJS: HtmlAttr[String]        = htmlAttr("width", StringAsIsCodec)
  lazy val width: HtmlAttr[CSSSize]         = htmlAttr("width", CSSSize.AsStringCodec)

  object slots {

    /** Label of the select (shown in the overflow popover). @since UI5 2.15.0 */
    val label: Slot = new Slot("label")
  }

  object events {

    @js.native
    trait ChangeDetail extends js.Object {

      /** The selected option (the inner ui5-option) */
      def selectedOption: dom.HTMLElement = js.native

      /** The selected [[ToolbarSelectOption]] (since UI5 2.25.0) */
      def selectedToolbarOption: js.UndefOr[ToolbarSelectOption.Ref] = js.native
    }

    lazy val onChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ChangeDetail]] = new EventProp("change")
    lazy val onClose: EventProp[EventWithPreciseTarget[Ref]]                            = new EventProp("close")
    lazy val onOpen: EventProp[EventWithPreciseTarget[Ref]]                             = new EventProp("open")
  }

  def option: ToolbarSelectOption.type = ToolbarSelectOption

}
