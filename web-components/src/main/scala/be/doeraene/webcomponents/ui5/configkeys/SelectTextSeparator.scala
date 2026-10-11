package be.doeraene.webcomponents.ui5.configkeys

/** Defines the separator types for Select component two-column layout.
  *
  * @since UI5 2.16.0
  */
sealed trait SelectTextSeparator {
  final def value: String = toString
}

object SelectTextSeparator extends EnumerationString[SelectTextSeparator] {

  /** Will show bullet(·) as separator on two columns layout when Select is in read-only mode. */
  case object Bullet       extends SelectTextSeparator
  /** Will show N-dash(–) as separator on two columns layout when Select is in read-only mode. */
  case object Dash         extends SelectTextSeparator
  /** Will show vertical line(|) as separator on two columns layout when Select is in read-only mode. */
  case object VerticalLine extends SelectTextSeparator

  val allValues: List[SelectTextSeparator] = deriveAllValues

  def valueOf(value: SelectTextSeparator): String = value.value

}
