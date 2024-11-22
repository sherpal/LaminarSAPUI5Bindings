package be.doeraene.webcomponents.ui5.configkeys

sealed trait TabContainerDesign {
  def value: String = toString
}

object TabContainerDesign extends EnumerationString[TabContainerDesign] {

  case object Solid       extends TabContainerDesign
  case object Transparent extends TabContainerDesign
  case object Translucent extends TabContainerDesign

  val allValues: List[TabContainerDesign] = deriveAllValues

  def valueOf(value: TabContainerDesign): String = value.value

}