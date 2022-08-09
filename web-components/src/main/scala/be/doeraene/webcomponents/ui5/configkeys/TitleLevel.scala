package be.doeraene.webcomponents.ui5.configkeys

trait TitleLevel {
  final def value: String = toString
}

object TitleLevel extends EnumerationString[TitleLevel] {
  case object H1 extends TitleLevel
  case object H2 extends TitleLevel
  case object H3 extends TitleLevel
  case object H4 extends TitleLevel
  case object H5 extends TitleLevel
  case object H6 extends TitleLevel

  val allValues: List[TitleLevel] = List(H1, H2, H3, H4, H5, H6)

  def valueOf(value: TitleLevel): String = value.value
}
