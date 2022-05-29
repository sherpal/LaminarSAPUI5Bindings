package be.doeraene.webcomponents.ui5.configkeys

/** Defines the HTML type of the component. Available options are: Text, Email, Number, Password, Tel, and URL.
  */
sealed trait InputType {
  def value: String
}

object InputType extends EnumerationString[InputType] {
  case object Text extends InputType {
    override def value: String = "Text"
  }
  case object Email extends InputType {
    override def value: String = "Email"
  }
  case object Number extends InputType {
    override def value: String = "Number"
  }
  case object Password extends InputType {
    override def value: String = "Password"
  }
  case object Tel extends InputType {
    override def value: String = "Tel"
  }
  case object Url extends InputType {
    override def value: String = "Url"
  }

  val allValues: List[InputType] = List(Text, Email, Number, Password, Tel, Url)

  def valueOf(value: InputType): String = value.value
}
