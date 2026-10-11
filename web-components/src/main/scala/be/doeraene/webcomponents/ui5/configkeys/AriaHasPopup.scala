package be.doeraene.webcomponents.ui5.configkeys

/** Different types of popup that an element can trigger, used in the `hasPopup` field of the `accessibilityAttributes`
  * property of many components.
  */
sealed trait AriaHasPopup {
  def value: String
}

object AriaHasPopup extends EnumerationString[AriaHasPopup] {

  case object Dialog  extends AriaHasPopup { def value: String = "dialog" }
  case object Grid    extends AriaHasPopup { def value: String = "grid" }
  case object ListBox extends AriaHasPopup { def value: String = "listbox" }
  case object Menu    extends AriaHasPopup { def value: String = "menu" }
  case object Tree    extends AriaHasPopup { def value: String = "tree" }

  val allValues: List[AriaHasPopup] = deriveAllValues

  def valueOf(value: AriaHasPopup): String = value.value

}
