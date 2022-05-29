package be.doeraene.webcomponents.ui5.configkeys

sealed trait TabLayout {
  final def value: String = toString
}

object TabLayout extends EnumerationString[TabLayout] {
  case object Standard extends TabLayout
  case object Inline extends TabLayout

  val allValues: List[TabLayout] = List(Standard, Inline)

  def valueOf(value: TabLayout): String = value.value
}
