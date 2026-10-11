package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.{ToolbarAlign, ToolbarDesign}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-toolbar component is used to create a horizontal layout with items. The items can be overflowing in a
  * popover, when the space is not enough to show all of them.
  *
  * Items can be [[ToolbarButton]], [[ToolbarSelect]], [[ToolbarSeparator]], [[ToolbarSpacer]], or any other component
  * wrapped inside a [[ToolbarItem]].
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/Toolbar/">the doc</a> for more information.
  */
object Toolbar extends WebComponent with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {

    /** Returns whether the overflow popover is open. */
    def isOverflowOpen(): Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Toolbar.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-toolbar")

  lazy val alignContent: HtmlAttr[ToolbarAlign] = ToolbarAlign.asHtmlAttr("align-content")
  lazy val design: HtmlAttr[ToolbarDesign]      = ToolbarDesign.asHtmlAttr("design")

  /** Accessible name of the overflow button.
    *
    * @since UI5 2.22.0
    */
  lazy val overflowButtonAccessibleName: HtmlAttr[String] = htmlAttr("overflow-button-accessible-name", StringAsIsCodec)

  object slots {}

  object events {}

  def button: ToolbarButton.type       = ToolbarButton
  def select: ToolbarSelect.type       = ToolbarSelect
  def separator: ToolbarSeparator.type = ToolbarSeparator
  def spacer: ToolbarSpacer.type       = ToolbarSpacer
  def item: ToolbarItem.type           = ToolbarItem

}
