package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.AvatarGroupType
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.WebComponent

/** Displays a group of avatars arranged horizontally. It is useful to visually showcase a group of related avatars,
  * such as, project team members or employees. The component allows you to display the avatars in different sizes,
  * depending on your use case.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/AvatarGroup/">the doc</a> for more
  *   information.
  */
object AvatarGroup extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    @JSName("colorScheme")
    def colourSchemeJS: js.Array[String] = js.native

    @JSName("hiddenItems")
    def hiddenItemsJS: js.Array[Avatar.Ref] = js.native
  }

  object RawElement {
    extension (element: RawElement)
      def colourScheme: List[String]    = element.colourSchemeJS.toList
      def hiddenItems: List[Avatar.Ref] = element.hiddenItemsJS.toList
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/AvatarGroup.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = AvatarGroup.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-avatar-group")

  val tpe: ReactiveHtmlAttr[AvatarGroupType] = customHtmlAttr("type", AvatarGroupType.AsStringCodec)

  object slots {
    val overflowButton: Slot = Slot("overflowButton")
  }

  object events {
    trait AvatarClickInfo extends js.Object {
      def targetRef: dom.HTMLElement
      def overflowButtonClicked: Boolean
    }

    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[AvatarClickInfo]] = new EventProp("click")

    val onOverflow: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("overflow")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(AvatarGroup)): _*)

  def avatar: Avatar.type = Avatar

}
