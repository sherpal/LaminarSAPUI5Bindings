package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-view-settings-dialog-custom-tab defines a custom tab of a [[ViewSettingsDialog]] (to be put in its
  * `customTabs` slot). Its children are the content of the tab.
  *
  * The icon (which defaults to `action-settings`) is displayed in the segmented button allowing to switch between the
  * tabs of the dialog.
  *
  * @since UI5 2.22.0
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/ViewSettingsDialogCustomTab/">the doc</a> for more
  *   information.
  */
object ViewSettingsDialogCustomTab extends WebComponent with HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/ViewSettingsDialogCustomTab.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-view-settings-dialog-custom-tab")

  /** Title displayed in the dialog header when this tab is selected. */
  lazy val titleText: HtmlAttr[String] = htmlAttr("title-text", StringAsIsCodec)

  /** Tooltip of the tab button in the segmented button. */
  lazy val tooltip: HtmlAttr[String] = htmlAttr("tooltip", StringAsIsCodec)

  object slots {}

  object events {}

}
