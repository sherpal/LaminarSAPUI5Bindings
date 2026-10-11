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

/** The ui5-user-settings-notifications-view-group groups [[UserSettingsNotificationsViewItem]]s inside a
  * [[UserSettingsNotificationsView]]. Its header renders as a bold section title with a separator line below.
  *
  * @since UI5 2.27.0
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/UserSettingsNotificationsViewGroup/">the doc</a> for
  *   more information.
  */
object UserSettingsNotificationsViewGroup extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/UserSettingsNotificationsViewGroup.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-user-settings-notifications-view-group")

  lazy val headerText: HtmlAttr[String]           = htmlAttr("header-text", StringAsIsCodec)
  lazy val headerAccessibleName: HtmlAttr[String] = htmlAttr("header-accessible-name", StringAsIsCodec)

  /** Defines if the text of the group header should wrap when it's too long. */
  lazy val wrappingType: HtmlAttr[WrappingType] = htmlAttr("wrapping-type", WrappingType.AsStringCodec)

  object slots {

    /** Custom header (overrides `headerText`). */
    val header: Slot = Slot("header")
  }

  object events {
    type MoveDetail = MoveEventDetail[UserSettingsNotificationsViewItem.Ref]

    /** Fired when a movable list item is dropped onto a drop target. */
    val onMove: EventProp[EventWithPreciseTarget[Ref] & HasDetail[MoveDetail]] = new EventProp("move")

    /** Fired when a movable list item is moved over a potential drop target. Prevent it if the position is valid. */
    val onMoveOver: EventProp[EventWithPreciseTarget[Ref] & HasDetail[MoveDetail]] = new EventProp("move-over")
  }

  def item: UserSettingsNotificationsViewItem.type = UserSettingsNotificationsViewItem

}
