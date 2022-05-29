package be.doeraene.webcomponents.ui5.configkeys

trait BreadcrumbsDesign {
  def value: String = toString
}

object BreadcrumbsDesign extends EnumerationString[BreadcrumbsDesign] {
  case object Standard extends BreadcrumbsDesign
  case object NoCurrentPage extends BreadcrumbsDesign

  val allValues: List[BreadcrumbsDesign] = List(Standard, NoCurrentPage)

  def valueOf(value: BreadcrumbsDesign): String = value.value
}
