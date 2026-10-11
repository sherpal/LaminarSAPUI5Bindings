package be.doeraene.webcomponents.ui5.configkeys

sealed trait WizardContentLayout {
  def value: String = toString
}

object WizardContentLayout extends EnumerationString[WizardContentLayout] {

  /** Display the content of all the steps, one below the other (default). */
  case object MultipleSteps extends WizardContentLayout

  /** Display only the content of the selected step. */
  case object SingleStep extends WizardContentLayout

  @deprecated("MultiSteps was not a valid UI5 value, use MultipleSteps instead", since = "2.27.2")
  def MultiSteps: WizardContentLayout = MultipleSteps

  @deprecated("SingleSteps was not a valid UI5 value, use SingleStep instead", since = "2.27.2")
  def SingleSteps: WizardContentLayout = SingleStep

  val allValues: List[WizardContentLayout] = List(MultipleSteps, SingleStep)

  def valueOf(value: WizardContentLayout): String = value.value
}
