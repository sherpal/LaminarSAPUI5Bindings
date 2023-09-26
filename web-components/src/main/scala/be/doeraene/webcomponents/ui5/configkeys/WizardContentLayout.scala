package be.doeraene.webcomponents.ui5.configkeys

sealed trait WizardContentLayout {
  def value: String = toString
}

object WizardContentLayout extends EnumerationString[WizardContentLayout] {
  case object MultiSteps  extends WizardContentLayout
  case object SingleSteps extends WizardContentLayout

  val allValues: List[WizardContentLayout] = List(MultiSteps, SingleSteps)

  def valueOf(value: WizardContentLayout): String = value.value
}
