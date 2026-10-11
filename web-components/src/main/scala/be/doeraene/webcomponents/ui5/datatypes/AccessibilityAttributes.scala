package be.doeraene.webcomponents.ui5.datatypes

import be.doeraene.webcomponents.ui5.configkeys.AriaHasPopup
import com.raquo.laminar.api.L
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.AsIsCodec

import scala.scalajs.js

/** Additional accessibility attributes that can be given to the `accessibilityAttributes` property of many components.
  *
  * Each component only takes into account a subset of these fields. Refer to the documentation of the
  * `accessibilityAttributes` member of each component to know which ones.
  *
  * Note that `accessibilityAttributes` is a property (not an attribute), so it can only be set programmatically
  * (which is what the Laminar bindings do for you).
  *
  * Example:
  * {{{
  *   Button(_.accessibilityAttributes := AccessibilityAttributes(hasPopup = AriaHasPopup.Menu, expanded = false))
  * }}}
  */
trait AccessibilityAttributes extends js.Object {
  var ariaSetsize: js.UndefOr[Int]         = js.undefined
  var ariaPosinset: js.UndefOr[Int]        = js.undefined
  var ariaLabel: js.UndefOr[String]        = js.undefined
  var controls: js.UndefOr[String]         = js.undefined
  var expanded: js.UndefOr[Boolean]        = js.undefined
  var hasPopup: js.UndefOr[String]         = js.undefined
  var name: js.UndefOr[String]             = js.undefined
  var role: js.UndefOr[String]             = js.undefined
  var ariaKeyShortcuts: js.UndefOr[String] = js.undefined
  var ariaCurrent: js.UndefOr[String]      = js.undefined
  var current: js.UndefOr[String]          = js.undefined
  var roleDescription: js.UndefOr[String]  = js.undefined
  var title: js.UndefOr[String]            = js.undefined
}

object AccessibilityAttributes {

  /** Creates an [[AccessibilityAttributes]] instance, only setting the fields that you specify.
    *
    * @param ariaSetsize
    *   number of items in the current set of list items or tree items (when not all of them are in the DOM)
    * @param ariaPosinset
    *   position of the item in the set of list items or tree items
    * @param ariaLabel
    *   accessible ARIA name of the component
    * @param controls
    *   id (or ids, separated by spaces) of the element(s) whose contents or presence are controlled by the component.
    *   Must be lowercase.
    * @param expanded
    *   whether the component, or another grouping element it controls, is currently expanded or collapsed
    * @param hasPopup
    *   type of the popup that the component can trigger
    * @param name
    *   accessible name
    * @param role
    *   ARIA role
    * @param ariaKeyShortcuts
    *   keyboard shortcuts that activate or give focus to the component
    * @param current
    *   the current item within a container or set of related elements (e.g., "page", "step", "true", ...)
    * @param title
    *   tooltip of the element
    */
  def apply(
      ariaSetsize: js.UndefOr[Int] = js.undefined,
      ariaPosinset: js.UndefOr[Int] = js.undefined,
      ariaLabel: js.UndefOr[String] = js.undefined,
      controls: js.UndefOr[String] = js.undefined,
      expanded: js.UndefOr[Boolean] = js.undefined,
      hasPopup: js.UndefOr[AriaHasPopup] = js.undefined,
      name: js.UndefOr[String] = js.undefined,
      role: js.UndefOr[String] = js.undefined,
      ariaKeyShortcuts: js.UndefOr[String] = js.undefined,
      ariaCurrent: js.UndefOr[String] = js.undefined,
      current: js.UndefOr[String] = js.undefined,
      roleDescription: js.UndefOr[String] = js.undefined,
      title: js.UndefOr[String] = js.undefined
  ): AccessibilityAttributes = {
    val attributes = new AccessibilityAttributes {}
    ariaSetsize.foreach(attributes.ariaSetsize = _)
    ariaPosinset.foreach(attributes.ariaPosinset = _)
    ariaLabel.foreach(attributes.ariaLabel = _)
    controls.foreach(attributes.controls = _)
    expanded.foreach(attributes.expanded = _)
    hasPopup.foreach(popup => attributes.hasPopup = popup.value)
    name.foreach(attributes.name = _)
    role.foreach(attributes.role = _)
    ariaKeyShortcuts.foreach(attributes.ariaKeyShortcuts = _)
    ariaCurrent.foreach(attributes.ariaCurrent = _)
    current.foreach(attributes.current = _)
    roleDescription.foreach(attributes.roleDescription = _)
    title.foreach(attributes.title = _)
    attributes
  }

  /** Builds the `accessibilityAttributes` [[HtmlProp]] for components whose accessibility attributes are "flat" (that
    * is, almost all of them).
    */
  def asHtmlProp: HtmlProp[AccessibilityAttributes, AccessibilityAttributes] =
    L.htmlProp("accessibilityAttributes", AsIsCodec())

}

/** Accessibility attributes for the `ui5-list` component.
  *
  * @param growingButton
  *   `name` and `description` of the "More" button, when `growing` is `Button`.
  */
trait ListAccessibilityAttributes extends js.Object {
  var growingButton: js.UndefOr[ListAccessibilityAttributes.GrowingButton] = js.undefined
}

object ListAccessibilityAttributes {
  trait GrowingButton extends js.Object {
    var name: js.UndefOr[String]        = js.undefined
    var description: js.UndefOr[String] = js.undefined
  }

  def apply(
      growingButtonName: js.UndefOr[String] = js.undefined,
      growingButtonDescription: js.UndefOr[String] = js.undefined
  ): ListAccessibilityAttributes = {
    val button = new GrowingButton {}
    growingButtonName.foreach(button.name = _)
    growingButtonDescription.foreach(button.description = _)
    val attributes = new ListAccessibilityAttributes {}
    attributes.growingButton = button
    attributes
  }
}

/** Accessibility attributes for the `ui5-split-button` component.
  *
  * @param root
  *   attributes for the main button. Supported fields: `hasPopup`, `title`, `ariaKeyShortcuts`
  * @param arrowButton
  *   attributes for the arrow button. Supported fields: `hasPopup`, `expanded`, `title`
  */
trait SplitButtonAccessibilityAttributes extends js.Object {
  var root: js.UndefOr[AccessibilityAttributes]        = js.undefined
  var arrowButton: js.UndefOr[AccessibilityAttributes] = js.undefined
}

object SplitButtonAccessibilityAttributes {
  def apply(
      root: js.UndefOr[AccessibilityAttributes] = js.undefined,
      arrowButton: js.UndefOr[AccessibilityAttributes] = js.undefined
  ): SplitButtonAccessibilityAttributes = {
    val attributes = new SplitButtonAccessibilityAttributes {}
    root.foreach(attributes.root = _)
    arrowButton.foreach(attributes.arrowButton = _)
    attributes
  }
}

/** Accessibility attributes for the `ui5-dynamic-page` component. Each area supports the fields `role` and `name`. */
trait DynamicPageAccessibilityAttributes extends js.Object {
  var root: js.UndefOr[AccessibilityAttributes]    = js.undefined
  var header: js.UndefOr[AccessibilityAttributes]  = js.undefined
  var content: js.UndefOr[AccessibilityAttributes] = js.undefined
  var footer: js.UndefOr[AccessibilityAttributes]  = js.undefined
}

object DynamicPageAccessibilityAttributes {
  def apply(
      root: js.UndefOr[AccessibilityAttributes] = js.undefined,
      header: js.UndefOr[AccessibilityAttributes] = js.undefined,
      content: js.UndefOr[AccessibilityAttributes] = js.undefined,
      footer: js.UndefOr[AccessibilityAttributes] = js.undefined
  ): DynamicPageAccessibilityAttributes = {
    val attributes = new DynamicPageAccessibilityAttributes {}
    root.foreach(attributes.root = _)
    header.foreach(attributes.header = _)
    content.foreach(attributes.content = _)
    footer.foreach(attributes.footer = _)
    attributes
  }
}

/** Accessibility attributes for the `ui5-dynamic-side-content` component. Each area supports the fields `ariaLabel`
  * and `role`.
  */
trait DynamicSideContentAccessibilityAttributes extends js.Object {
  var mainContent: js.UndefOr[AccessibilityAttributes] = js.undefined
  var sideContent: js.UndefOr[AccessibilityAttributes] = js.undefined
}

object DynamicSideContentAccessibilityAttributes {
  def apply(
      mainContent: js.UndefOr[AccessibilityAttributes] = js.undefined,
      sideContent: js.UndefOr[AccessibilityAttributes] = js.undefined
  ): DynamicSideContentAccessibilityAttributes = {
    val attributes = new DynamicSideContentAccessibilityAttributes {}
    mainContent.foreach(attributes.mainContent = _)
    sideContent.foreach(attributes.sideContent = _)
    attributes
  }
}

/** Accessibility attributes for the `ui5-flexible-column-layout` component. Each area supports the fields `role` and
  * `name`.
  */
trait FCLAccessibilityAttributes extends js.Object {
  var startColumn: js.UndefOr[AccessibilityAttributes]    = js.undefined
  var midColumn: js.UndefOr[AccessibilityAttributes]      = js.undefined
  var endColumn: js.UndefOr[AccessibilityAttributes]      = js.undefined
  var startSeparator: js.UndefOr[AccessibilityAttributes] = js.undefined
  var endSeparator: js.UndefOr[AccessibilityAttributes]   = js.undefined
}

object FCLAccessibilityAttributes {
  def apply(
      startColumn: js.UndefOr[AccessibilityAttributes] = js.undefined,
      midColumn: js.UndefOr[AccessibilityAttributes] = js.undefined,
      endColumn: js.UndefOr[AccessibilityAttributes] = js.undefined,
      startSeparator: js.UndefOr[AccessibilityAttributes] = js.undefined,
      endSeparator: js.UndefOr[AccessibilityAttributes] = js.undefined
  ): FCLAccessibilityAttributes = {
    val attributes = new FCLAccessibilityAttributes {}
    startColumn.foreach(attributes.startColumn = _)
    midColumn.foreach(attributes.midColumn = _)
    endColumn.foreach(attributes.endColumn = _)
    startSeparator.foreach(attributes.startSeparator = _)
    endSeparator.foreach(attributes.endSeparator = _)
    attributes
  }
}

/** Accessibility attributes for the `ui5-shellbar` component.
  *
  *   - `logo`: supports `role` ("button" or "link") and `name`
  *   - `notifications`, `product`, `search`, `overflow`: support `hasPopup` and `expanded`
  *   - `profile`: supports `name`, `hasPopup` and `expanded`
  *   - `branding`: supports `name`
  */
trait ShellBarAccessibilityAttributes extends js.Object {
  var logo: js.UndefOr[AccessibilityAttributes]          = js.undefined
  var notifications: js.UndefOr[AccessibilityAttributes] = js.undefined
  var profile: js.UndefOr[AccessibilityAttributes]       = js.undefined
  var product: js.UndefOr[AccessibilityAttributes]       = js.undefined
  var search: js.UndefOr[AccessibilityAttributes]        = js.undefined
  var overflow: js.UndefOr[AccessibilityAttributes]      = js.undefined
  var branding: js.UndefOr[AccessibilityAttributes]      = js.undefined
}

object ShellBarAccessibilityAttributes {
  def apply(
      logo: js.UndefOr[AccessibilityAttributes] = js.undefined,
      notifications: js.UndefOr[AccessibilityAttributes] = js.undefined,
      profile: js.UndefOr[AccessibilityAttributes] = js.undefined,
      product: js.UndefOr[AccessibilityAttributes] = js.undefined,
      search: js.UndefOr[AccessibilityAttributes] = js.undefined,
      overflow: js.UndefOr[AccessibilityAttributes] = js.undefined,
      branding: js.UndefOr[AccessibilityAttributes] = js.undefined
  ): ShellBarAccessibilityAttributes = {
    val attributes = new ShellBarAccessibilityAttributes {}
    logo.foreach(attributes.logo = _)
    notifications.foreach(attributes.notifications = _)
    profile.foreach(attributes.profile = _)
    product.foreach(attributes.product = _)
    search.foreach(attributes.search = _)
    overflow.foreach(attributes.overflow = _)
    branding.foreach(attributes.branding = _)
    attributes
  }
}
