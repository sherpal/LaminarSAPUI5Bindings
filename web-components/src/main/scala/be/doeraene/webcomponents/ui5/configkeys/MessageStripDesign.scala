package be.doeraene.webcomponents.ui5.configkeys

sealed trait MessageStripDesign {
  def value: String = toString
}

object MessageStripDesign extends EnumerationString[MessageStripDesign] {
  case object Information extends MessageStripDesign
  case object Positive    extends MessageStripDesign
  case object Negative    extends MessageStripDesign
  case object Critical    extends MessageStripDesign

  @deprecated("Warning MessageStripDesign was renamed to Critical", since = "2.0.0")
  def Warning: MessageStripDesign = Critical

  override def valueOf(value: MessageStripDesign): String = value.value

  override val allValues: List[MessageStripDesign] = deriveAllValues
}
