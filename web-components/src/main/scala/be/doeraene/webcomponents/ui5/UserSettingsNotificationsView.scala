package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-user-settings-notifications-view is a view displayed in the [[UserSettingsItem]] that lists notification
  * preferences. Its children are [[UserSettingsNotificationsViewItem]]s, optionally grouped in
  * [[UserSettingsNotificationsViewGroup]]s.
  *
  * When a `navigable` item is clicked, the view drills into a sibling `secondary` view of its parent
  * [[UserSettingsItem]] (the one whose id is the item's `itemKey`, or else the first secondary one). This can be
  * prevented via the `item-click` event. Listen to the (bubbling) `switch-change` event of the items to be notified
  * when a switch is toggled.
  *
  * @since UI5 2.27.0
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/UserSettingsNotificationsView/">the doc</a> for
  *   more information.
  */
object UserSettingsNotificationsView extends WebComponent with UserSettingsViewAttributes {

  @js.native
  trait RawElement extends js.Object {
    def selected: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/UserSettingsNotificationsView.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-user-settings-notifications-view")

  object slots {

    /** Additional content (e.g. a [[MessageStrip]]) displayed above the items list. */
    val additionalContent: Slot = Slot("additionalContent")

    /** Header items ([[UserSettingsNotificationsViewItem]]s) rendered above the grouped items, e.g. for product-level
      * toggles such as "Allow Notifications".
      */
    val headerItems: Slot = Slot("headerItems")
  }

  object events {

    trait ItemClickDetail extends js.Object {
      def item: UserSettingsNotificationsViewItem.Ref
    }

    /** Fired when a navigable item is clicked. Prevent it to skip the built-in drill-in to the secondary view. */
    val onItemClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ItemClickDetail]] = new EventProp("item-click")
  }

  def item: UserSettingsNotificationsViewItem.type   = UserSettingsNotificationsViewItem
  def group: UserSettingsNotificationsViewGroup.type = UserSettingsNotificationsViewGroup

}
