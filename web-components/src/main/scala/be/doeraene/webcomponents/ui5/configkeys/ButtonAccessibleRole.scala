package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.laminar.codecs.Codec

sealed trait ButtonAccessibleRole {
  def value: String = toString
}

object ButtonAccessibleRole extends EnumerationString[ButtonAccessibleRole] {

  case object Button extends ButtonAccessibleRole
  case object Link   extends ButtonAccessibleRole

  val allValues: List[ButtonAccessibleRole] = deriveAllValues

  def valueOf(value: ButtonAccessibleRole): String = value.value

}
