package be.doeraene.webcomponents.ui5.configkeys

sealed trait UploadState {
  def value: String = toString
}

object UploadState extends EnumerationString[UploadState] {
  case object Ready extends UploadState
  case object Uploading extends UploadState
  case object Error extends UploadState

  val allValues: List[UploadState] = List(Ready, Uploading, Error)

  def valueOf(value: UploadState): String = value.value
}
