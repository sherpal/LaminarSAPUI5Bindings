package be.doeraene.webcomponents.ui5.configkeys

sealed trait SemanticColour {
  def value: String = toString
}

object SemanticColour extends EnumerationString[SemanticColour] {

  case object Default extends SemanticColour
  case object Neutral extends SemanticColour
  case object Positive extends SemanticColour
  case object Critical extends SemanticColour
  case object Negative extends SemanticColour

  val allValues: List[SemanticColour] = List(
    Default,
    Neutral,
    Positive,
    Critical,
    Negative
  )

  def valueOf(value: SemanticColour): String = value.value

}
