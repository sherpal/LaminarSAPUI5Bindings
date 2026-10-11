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

/** The ui5-user-settings-appearance-view is a view displayed in the [[UserSettingsItem]], used to choose a theme. Its
  * children are [[UserSettingsAppearanceViewItem]]s, optionally grouped in [[UserSettingsAppearanceViewGroup]]s.
  *
  * @since UI5 2.17.0
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/UserSettingsAppearanceView/">the doc</a> for more
  *   information.
  */
object UserSettingsAppearanceView extends WebComponent with UserSettingsViewAttributes {

  @js.native
  trait RawElement extends js.Object {
    def selected: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/UserSettingsAppearanceView.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-user-settings-appearance-view")

  object slots {

    /** Additional content displayed below the items list. */
    val additionalContent: Slot = Slot("additionalContent")
  }

  object events {

    trait SelectionChangeDetail extends js.Object {
      def item: UserSettingsAppearanceViewItem.Ref
    }

    /** Fired when an item is selected. Can be prevented. */
    val onSelectionChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[SelectionChangeDetail]] =
      new EventProp("selection-change")
  }

  def item: UserSettingsAppearanceViewItem.type   = UserSettingsAppearanceViewItem
  def group: UserSettingsAppearanceViewGroup.type = UserSettingsAppearanceViewGroup

}
