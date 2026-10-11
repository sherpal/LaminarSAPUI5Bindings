package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.WrappingType
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.MoveEventDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-search-item-group is a type of suggestion item, that can be used to split the [[SearchItem]] suggestions of
  * a [[Search]] into groups.
  *
  * Note: this component is experimental in UI5 and its API may change.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/SearchItemGroup/">the doc</a> for more
  *   information.
  */
object SearchItemGroup extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  object RawElement

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/SearchItemGroup.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-search-item-group")

  /** Defines the header text of the group. */
  lazy val headerText: HtmlAttr[String]           = htmlAttr("header-text", StringAsIsCodec)
  lazy val headerAccessibleName: HtmlAttr[String] = htmlAttr("header-accessible-name", StringAsIsCodec)

  /** Defines if the text of the group header should wrap when it's too long. */
  lazy val wrappingType: HtmlAttr[WrappingType] = htmlAttr("wrapping-type", WrappingType.AsStringCodec)

  @scala.annotation.compileTimeOnly("ui5-search-item-group has no text attribute. Use headerText instead.")
  def text: HtmlAttr[String] = ???

  @scala.annotation.compileTimeOnly("ui5-search-item-group has no selected attribute.")
  def selected: HtmlAttr[Boolean] = ???

  object slots {

    /** Custom header item (replaces the default header built from `headerText`). */
    val header: Slot = Slot("header")
  }

  object events {
    val onMove: EventProp[EventWithPreciseTarget[Ref] & HasDetail[MoveEventDetail[dom.html.Element]]] =
      new EventProp("move")
    val onMoveOver: EventProp[EventWithPreciseTarget[Ref] & HasDetail[MoveEventDetail[dom.html.Element]]] =
      new EventProp("move-over")
  }

  def item: SearchItem.type = SearchItem

}
