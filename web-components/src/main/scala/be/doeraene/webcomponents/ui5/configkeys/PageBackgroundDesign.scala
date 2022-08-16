package be.doeraene.webcomponents.ui5.configkeys

sealed trait PageBackgroundDesign {
  def value: String = toString
}

object PageBackgroundDesign extends EnumerationString[PageBackgroundDesign] {
  case object Solid extends PageBackgroundDesign
  case object Transparent extends PageBackgroundDesign
  case object ListPage extends PageBackgroundDesign {
    override def value: String = "List"
  }

  val allValues: List[PageBackgroundDesign] = List(Solid, Transparent, ListPage)

  def valueOf(value: PageBackgroundDesign): String = value.value
}
