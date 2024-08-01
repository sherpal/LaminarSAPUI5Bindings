package be.doeraene.webcomponents.ui5.configkeys

sealed trait IconMode {
  def value: String = toString
}

object IconMode extends EnumerationString[IconMode] {

  case object Image      extends IconMode
  case object Decorative extends IconMode
  case object Iteractive extends IconMode

  override val allValues: List[IconMode] = deriveAllValues

  override def valueOf(value: IconMode): String = value.value

}
