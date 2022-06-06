package be.doeraene.webcomponents.ui5.configkeys

trait TitleLevel {
  final def value: String = toString
}

object TitleLevel extends EnumerationString[TitleLevel] {
  object H1 extends TitleLevel
  object H2 extends TitleLevel
  object H3 extends TitleLevel
  object H4 extends TitleLevel
  object H5 extends TitleLevel
  object H6 extends TitleLevel

  val allValues: List[TitleLevel] = List(H1, H2, H3, H4, H5, H6)

  def valueOf(value: TitleLevel): String = value.value
}
