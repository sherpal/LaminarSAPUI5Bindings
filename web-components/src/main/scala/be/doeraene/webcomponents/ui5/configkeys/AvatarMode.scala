package be.doeraene.webcomponents.ui5.configkeys

/** Different Avatar modes.
  *
  * @since UI5 2.20.0
  */
sealed trait AvatarMode {
  final def value: String = toString
}

object AvatarMode extends EnumerationString[AvatarMode] {

  /** Decorative mode. Configures the component to internally render role="presentation" and aria-hidden="true", making it purely decorative without semantic content or interactivity. */
  case object Decorative  extends AvatarMode
  /** Image mode (by default). Configures the component to internally render role="img". */
  case object Image       extends AvatarMode
  /** Interactive mode. Configures the component to internally render role="button". This mode also supports focus and enables keyboard interaction. */
  case object Interactive extends AvatarMode

  val allValues: List[AvatarMode] = deriveAllValues

  def valueOf(value: AvatarMode): String = value.value

}
