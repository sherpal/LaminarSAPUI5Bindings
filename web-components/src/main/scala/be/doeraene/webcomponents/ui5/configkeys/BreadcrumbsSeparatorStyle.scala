package be.doeraene.webcomponents.ui5.configkeys

trait BreadcrumbsSeparatorStyle {
  final def value: String = toString
}

object BreadcrumbsSeparatorStyle extends EnumerationString[BreadcrumbsSeparatorStyle] {

  case object Slash extends BreadcrumbsSeparatorStyle
  case object BackSlash extends BreadcrumbsSeparatorStyle
  case object DoubleBackSlash extends BreadcrumbsSeparatorStyle
  case object DoubleGreaterThan extends BreadcrumbsSeparatorStyle
  case object DoubleSlash extends BreadcrumbsSeparatorStyle
  case object GreaterThan extends BreadcrumbsSeparatorStyle

  val allValues: List[BreadcrumbsSeparatorStyle] = List(
    Slash,
    BackSlash,
    DoubleBackSlash,
    DoubleGreaterThan,
    DoubleSlash,
    GreaterThan
  )

  def valueOf(value: BreadcrumbsSeparatorStyle): String = value.value

}
