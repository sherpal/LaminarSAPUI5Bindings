package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.NavigationLayoutMode
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-navigation-layout is a container component that can be used to create a layout with a header, a side
  * navigation and a content area.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/components/fiori/NavigationLayout/">the doc</a> for more
  *   information.
  */
object NavigationLayout extends WebComponent with HasIcon {

  @js.native
  trait RawElement extends js.Object {
    def isSideCollapsed(): Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/NavigationLayout.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-navigation-layout")

  lazy val mode: HtmlAttr[NavigationLayoutMode] = htmlAttr("mode", NavigationLayoutMode.AsStringCodec)

  object slots {
    val header: Slot      = Slot("header")
    val sideContent: Slot = Slot("sideContent")
  }

  object events {}

}
