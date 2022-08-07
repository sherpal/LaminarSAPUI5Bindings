package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.domtypes.generic.codecs.Codec

sealed trait IconName

//noinspection NoTargetNameAnnotationForOperatorLikeDefinition
object IconName extends EnumerationString[IconName] {
  case object accept extends IconName
  case object `action-settings` extends IconName
  case object add extends IconName
  case object `add-document` extends IconName
  case object alert extends IconName
  case object away extends IconName
  case object bookmark extends IconName
  case object call extends IconName
  case object camera extends IconName
  case object cancel extends IconName
  case object cart extends IconName
  case object customer extends IconName
  case object delete extends IconName
  case object disconnected extends IconName
  case object download extends IconName
  case object edit extends IconName
  case object employee extends IconName
  case object error extends IconName
  case object `exit-full-screen` extends IconName
  case object flight extends IconName
  case object group extends IconName
  case object home extends IconName
  case object `internet-browser` extends IconName
  case object log extends IconName
  case object menu extends IconName
  case object `message-warning` extends IconName
  case object `money-bills` extends IconName
  case object `overview-chart` extends IconName
  case object refresh extends IconName
  case object `slim-arrow-bottom` extends IconName
  case object `slim-arrow-left` extends IconName
  case object `slim-arrow-right` extends IconName
  case object `slim-arrow-up` extends IconName
  case object theater extends IconName
  case object upload extends IconName

  val allValues: List[IconName] =
    List(
      accept,
      `action-settings`,
      add,
      `add-document`,
      alert,
      away,
      bookmark,
      call,
      camera,
      cancel,
      cart,
      customer,
      delete,
      disconnected,
      download,
      edit,
      employee,
      error,
      `exit-full-screen`,
      flight,
      group,
      home,
      `internet-browser`,
      log,
      menu,
      `message-warning`,
      `overview-chart`,
      refresh,
      `slim-arrow-bottom`,
      `slim-arrow-left`,
      `slim-arrow-right`,
      `slim-arrow-up`,
      theater,
      upload
    )

  def valueOf(value: IconName): String = value.toString

}
