package be.doeraene.webcomponents.ui5.configkeys

sealed trait FCLLayout {
  def value: String = toString
}

object FCLLayout extends EnumerationString[FCLLayout] {

  case object OneColumn extends FCLLayout
  case object TwoColumnsStartExpanded extends FCLLayout
  case object TwoColumnsMidExpanded extends FCLLayout
  case object ThreeColumnsMidExpanded extends FCLLayout
  case object ThreeColumnsEndExpanded extends FCLLayout
  case object ThreeColumnsStartExpandedEndHidden extends FCLLayout
  case object ThreeColumnsMidExpandedEndHidden extends FCLLayout
  case object MidColumnFullScreen extends FCLLayout
  case object EndColumnFullScreen extends FCLLayout

  override val allValues: List[FCLLayout] = List(
    OneColumn,
    TwoColumnsStartExpanded,
    TwoColumnsMidExpanded,
    ThreeColumnsMidExpanded,
    ThreeColumnsEndExpanded,
    ThreeColumnsStartExpandedEndHidden,
    ThreeColumnsMidExpandedEndHidden,
    MidColumnFullScreen,
    EndColumnFullScreen
  )

  override def valueOf(value: FCLLayout): String = value.value

  type StringFCLLayout = "OneColumn" | "TwoColumnsStartExpanded" | "TwoColumnsMidExpanded" | "ThreeColumnsMidExpanded" |
    "ThreeColumnsEndExpanded" | "ThreeColumnsStartExpandedEndHidden" | "ThreeColumnsMidExpandedEndHidden" |
    "MidColumnFullScreen" | "EndColumnFullScreen"

}
