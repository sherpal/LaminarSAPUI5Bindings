package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{TabContainerDesign, TabLayout, TabsOverflowMode}
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.MoveEventDetail
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget

/** Tab container
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/TabContainer/">the doc</a> for more
  *   information.
  */
object TabContainer extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    @JSName("allItems")
    def allItemsJs: js.Array[js.Object] = js.native
  }

  object RawElement {
    extension (element: RawElement) def allItems: List[js.Object] = element.allItemsJs.toList
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/TabContainer.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-tabcontainer")

  lazy val disabled: HtmlAttr[Boolean]    = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val collapsed: HtmlAttr[Boolean]   = htmlAttr("collapsed", BooleanAsAttrPresenceCodec)
  lazy val tabLayout: HtmlAttr[TabLayout] = htmlAttr("tab-layout", TabLayout.AsStringCodec)
  lazy val overflowMode: HtmlAttr[TabsOverflowMode] =
    htmlAttr("overflow-mode", TabsOverflowMode.AsStringCodec)
  lazy val contentBackgroundDesign: HtmlAttr[TabContainerDesign] =
    htmlAttr("content-background-design", TabContainerDesign.AsStringCodec)
  lazy val headerBackgroundDesign: HtmlAttr[TabContainerDesign] =
    htmlAttr("header-background-design", TabContainerDesign.AsStringCodec)

  @scala.annotation.compileTimeOnly("fixed property of TabContainer has been removed")
  def fixed: HtmlAttr[Boolean] = ???

  @deprecated("tabsOverflowMode property has been renamed to overflowMode", since = "2.0.0")
  def tabsOverflowMode: HtmlAttr[TabsOverflowMode] = overflowMode

  @scala.annotation.compileTimeOnly("showOverflow property has been removed. Use the overflowButton slot instead.")
  def showOverflow: HtmlAttr[Boolean] = ???

  object slots {
    val overflowButton: Slot      = Slot("overflowButton")
    val startOverflowButton: Slot = Slot("startOverflowButton")
  }

  object events {
    @js.native
    sealed trait TabSelectDetail extends js.Object {
      def tab: TabContainer.tab.Ref = js.native

      def tabIndex: Int = js.native
    }

    val onTabSelect: EventProp[EventWithPreciseTarget[Ref] & HasDetail[TabSelectDetail]] = new EventProp("tab-select")

    lazy val onMove: EventProp[EventWithPreciseTarget[Ref] & HasDetail[MoveEventDetail[tab.Ref]]] = new EventProp(
      "move"
    )
    lazy val onMoveOver: EventProp[EventWithPreciseTarget[Ref] & HasDetail[MoveEventDetail[tab.Ref]]] = new EventProp(
      "move-over"
    )
  }

  def tab: Tab.type = Tab

  @js.native
  @JSImport("@ui5/webcomponents/dist/TabSeparator.js", JSImport.Default)
  object TabSeparatorSupport extends js.Object

  def tabSeparator: HtmlElement = {
    TabSeparatorSupport
    htmlTag("ui5-tab-separator").apply()
  }

}
