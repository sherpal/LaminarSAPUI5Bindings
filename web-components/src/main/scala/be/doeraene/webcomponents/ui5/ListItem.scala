package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ListItemType, ValueState}
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.internal.Slot

/** The ui5-li represents the simplest type of item for a ui5-list. This is a list item, providing the most common use
  * cases such as text, image and icon.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/List/">the doc</a> for more information.
  */
object ListItem extends WebComponent with HasIcon with HasDescription with HasAdditionalText {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/StandardListItem.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-li")

  lazy val additionalTextState: ReactiveHtmlAttr[ValueState] =
    customHtmlAttr("additional-text-state", ValueState.AsStringCodec)

  lazy val iconEnd: ReactiveHtmlAttr[Boolean] = customHtmlAttr("icon-end", BooleanAsAttrPresenceCodec)
  lazy val image: ReactiveHtmlAttr[String]    = customHtmlAttr("image", StringAsIsCodec)

  lazy val tpe: ReactiveHtmlAttr[ListItemType] = customHtmlAttr("type", ListItemType.AsStringCodec)

  lazy val selected: ReactiveHtmlAttr[Boolean] = customHtmlAttr("selected", BooleanAsAttrPresenceCodec)

  object slots {
    val deleteButton: Slot = Slot("deleteButton")
  }
  
  object events {}
  

}
