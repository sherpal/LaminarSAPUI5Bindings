package be.doeraene.webcomponents.ui5.configkeys

sealed trait TitleLevel {
  final def value: String = toString
}

object TitleLevel extends EnumerationString[TitleLevel] {
  case object H1 extends TitleLevel
  case object H2 extends TitleLevel
  case object H3 extends TitleLevel
  case object H4 extends TitleLevel
  case object H5 extends TitleLevel
  case object H6 extends TitleLevel

  val allValues: List[TitleLevel] = deriveAllValues

  def valueOf(value: TitleLevel): String = value.value
}
