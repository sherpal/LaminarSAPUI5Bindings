package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
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
import be.doeraene.webcomponents.ui5.configkeys.ListItemType
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget

/** A component to be used as custom list item within the ui5-list the same way as the standard ui5-li. The component
  * accepts arbitrary HTML content to allow full customization.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/List/">the doc</a> for more information.
  */
object CustomListItem extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/CustomListItem.js", JSImport.Default)
  object RawImport extends WebComponent.WithMetadata

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-li-custom")

  lazy val accessibleName: ReactiveHtmlAttr[String] = customHtmlAttr("accessible-name", StringAsIsCodec)

  lazy val tpe: ReactiveHtmlAttr[ListItemType] = customHtmlAttr("type", ListItemType.AsStringCodec)

  lazy val selected: ReactiveHtmlAttr[Boolean] = customHtmlAttr("selected", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {
    val onDetailClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("detail-click")
  }

  

}
