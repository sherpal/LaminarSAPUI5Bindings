package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{TabLayout, TabsOverflowMode}
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
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

/** Tab container
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/TabContainer/">the doc</a> for more
  *   information.
  */
object TabContainer extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/TabContainer.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-tabcontainer")

  lazy val disabled: ReactiveHtmlAttr[Boolean]     = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val collapsed: ReactiveHtmlAttr[Boolean]    = customHtmlAttr("collapsed", BooleanAsAttrPresenceCodec)
  lazy val fixed: ReactiveHtmlAttr[Boolean]        = customHtmlAttr("fixed", BooleanAsAttrPresenceCodec)
  lazy val showOverflow: ReactiveHtmlAttr[Boolean] = customHtmlAttr("show-overflow", BooleanAsAttrPresenceCodec)
  lazy val tabLayout: ReactiveHtmlAttr[TabLayout]  = customHtmlAttr("tab-layout", TabLayout.AsStringCodec)
  lazy val tabsOverflowMode: ReactiveHtmlAttr[TabsOverflowMode] =
    customHtmlAttr("tabs-overflow-mode", TabsOverflowMode.AsStringCodec)

  object slots {
    val overflowButton: Slot      = new Slot("overflowButton")
    val startOverflowButton: Slot = new Slot("startOverflowButton")
  }

  object events {
    @js.native
    sealed trait TabSelectDetail extends js.Object {
      def tab: TabContainer.tab.Ref = js.native

      def tabIndex: Int = js.native
    }

    val onTabSelect: EventProp[dom.Event & HasDetail[TabSelectDetail]] = new EventProp("tab-select")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(TabContainer)): _*)

  def tab: Tab.type = Tab

  @js.native
  @JSImport("@ui5/webcomponents/dist/TabSeparator.js", JSImport.Default)
  object TabSeparatorSupport extends js.Object

  def tabSeparator: HtmlElement = {
    TabSeparatorSupport
    customHtmlTag("ui5-tab-separator").apply()
  }

}
