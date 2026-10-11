package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.IconName
import be.doeraene.webcomponents.ui5.configkeys.SelectTextSeparator
import be.doeraene.webcomponents.ui5.configkeys.ValueState
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

/** The ui5-select component is used to create a drop-down list. The items inside the ui5-select define the available
  * options by using the ui5-option component (or ui5-option-custom). Options can be grouped using ui5-option-group.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/Select/">the doc</a> for more information.
  */
object Select extends WebComponent with HasAccessibleName with HasName with HasValue {

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
  lazy val tooltip: HtmlAttr[String]   = htmlAttr("tooltip", StringAsIsCodec)

  lazy val accessibleDescription: HtmlAttr[String]    = htmlAttr("accessible-description", StringAsIsCodec)
  lazy val accessibleDescriptionRef: HtmlAttr[String] = htmlAttr("accessible-description-ref", StringAsIsCodec)

  lazy val valueState: HtmlAttr[ValueState] = ValueState.asHtmlAttr("value-state")

  /** Separator used between the text and the additional text of options (two-column layout) when the Select is
    * read-only. Since UI5 2.16.0.
    */
  lazy val textSeparator: HtmlAttr[SelectTextSeparator] = SelectTextSeparator.asHtmlAttr("text-separator")

  @scala.annotation.compileTimeOnly(
    "ui5-select does not have an icon attribute (put icons on its options instead, using `Select.option(_.icon := ...)`)."
  )
  def icon: HtmlAttr[IconName] = ???

  @scala.annotation.compileTimeOnly(
    "ui5-select does not have an icon attribute (put icons on its options instead, using `Select.option(_.icon := ...)`)."
  )
  def iconString: HtmlAttr[String] = ???

  object slots {
    val valueStateMessage: Slot = new Slot("valueStateMessage")

    /** HTML element displayed in the input part of the component, representing the selected option. */
    val label: Slot = new Slot("label")
  }

  object events {

    @js.native
    sealed trait HasSelectedOption extends js.Any {
      def selectedOption: option.Ref = js.native
    }

    val onChange = new EventProp[dom.Event & HasDetail[HasSelectedOption]]("change")

    /** Fired when the user navigates through the options, but the selection is not finalized, or when pressing ESC
      * key to revert the current selection.
      */
    val onLiveChange = new EventProp[dom.Event & HasDetail[HasSelectedOption]]("live-change")

    val onClose = new EventProp[dom.Event]("close")
    val onOpen  = new EventProp[dom.Event]("open")
  }

  def option: SelectOption.type             = SelectOption
  def optionCustom: SelectOptionCustom.type = SelectOptionCustom
  def optionGroup: OptionGroup.type         = OptionGroup

}
