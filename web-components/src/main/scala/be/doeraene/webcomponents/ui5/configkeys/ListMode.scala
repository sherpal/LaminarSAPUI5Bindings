package be.doeraene.webcomponents.ui5.configkeys

sealed trait ListMode {
  final def value: String = toString
}

object ListMode extends EnumerationString[ListMode] {

  sealed trait UploadCollectionMode { self: ListMode =>
    def uploadCollectionModeValue = self.value
  }
  object UploadCollectionMode extends EnumerationString[UploadCollectionMode] {
    def valueOf(value: UploadCollectionMode): String = value.uploadCollectionModeValue
    val allValues                                    = deriveAllValues
  }

  case object None              extends ListMode with UploadCollectionMode
  case object Single            extends ListMode with UploadCollectionMode
  case object SingleStart extends ListMode with UploadCollectionMode
  case object SingleEnd   extends ListMode with UploadCollectionMode
  case object SingleAuto  extends ListMode with UploadCollectionMode
  case object Multiple    extends ListMode with UploadCollectionMode
  case object Delete      extends ListMode

  @deprecated("SingleSelect ListMode has been renamed to Single")
  def SingleSelect: ListMode = Single
  @deprecated("MultiSelect ListMode has been renamed to Multiple")
  def MultiSelect: ListMode = Multiple
  @deprecated("SingleSelectBegin is not a valid UI5 2.x value, use SingleStart instead", since = "2.27.0")
  def SingleSelectBegin: ListMode & UploadCollectionMode = SingleStart
  @deprecated("SingleSelectEnd is not a valid UI5 2.x value, use SingleEnd instead", since = "2.27.0")
  def SingleSelectEnd: ListMode & UploadCollectionMode = SingleEnd
  @deprecated("SingleSelectAuto is not a valid UI5 2.x value, use SingleAuto instead", since = "2.27.0")
  def SingleSelectAuto: ListMode & UploadCollectionMode = SingleAuto

  val allValues: List[ListMode] = deriveAllValues

  def valueOf(value: ListMode): String = value.value

}
