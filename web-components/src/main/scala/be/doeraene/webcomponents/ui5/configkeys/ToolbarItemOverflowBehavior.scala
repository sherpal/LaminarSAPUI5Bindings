package be.doeraene.webcomponents.ui5.configkeys

/** Defines the priority of the toolbar item to go inside overflow popover.
  */
sealed trait ToolbarItemOverflowBehavior {
  final def value: String = toString
}

object ToolbarItemOverflowBehavior extends EnumerationString[ToolbarItemOverflowBehavior] {

  /** When set, the item will be always part of the overflow part of ui5-toolbar. */
  case object AlwaysOverflow extends ToolbarItemOverflowBehavior
  /** The item is presented inside the toolbar and goes in the popover, when there is not enough space. */
  case object Default        extends ToolbarItemOverflowBehavior
  /** When set, the item will never go to the overflow popover. */
  case object NeverOverflow  extends ToolbarItemOverflowBehavior

  val allValues: List[ToolbarItemOverflowBehavior] = deriveAllValues

  def valueOf(value: ToolbarItemOverflowBehavior): String = value.value

}
