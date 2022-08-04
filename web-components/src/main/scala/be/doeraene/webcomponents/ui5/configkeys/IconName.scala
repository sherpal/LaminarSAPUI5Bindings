package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.domtypes.generic.codecs.Codec

sealed trait IconName

//noinspection NoTargetNameAnnotationForOperatorLikeDefinition
object IconName extends EnumerationString[IconName] {
  case object `action-settings` extends IconName
  case object add extends IconName
  case object `add-document` extends IconName
  case object customer extends IconName
  case object disconnected extends IconName
  case object download extends IconName
  case object edit extends IconName
  case object error extends IconName
  case object `exit-full-screen` extends IconName
  case object flight extends IconName
  case object group extends IconName
  case object home extends IconName
  case object `internet-browser` extends IconName
  case object log extends IconName
  case object menu extends IconName
  case object `money-bills` extends IconName
  case object `overview-chart` extends IconName
  case object theater extends IconName
  case object upload extends IconName

  val allValues: List[IconName] =
    List(
      `action-settings`,
      add,
      `add-document`,
      customer,
      disconnected,
      download,
      edit,
      error,
      `exit-full-screen`,
      flight,
      group,
      home,
      `internet-browser`,
      log,
      menu,
      `overview-chart`,
      theater,
      upload
    )

  def valueOf(value: IconName): String = value.toString

}
