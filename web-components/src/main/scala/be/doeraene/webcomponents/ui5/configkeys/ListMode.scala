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
  case object SingleSelectBegin extends ListMode with UploadCollectionMode
  case object SingleSelectEnd   extends ListMode with UploadCollectionMode
  case object SingleSelectAuto  extends ListMode with UploadCollectionMode
  case object Multiple          extends ListMode with UploadCollectionMode
  case object Delete            extends ListMode

  @deprecated("SingleSelect ListMode has been renamed to Single")
  def SingleSelect: ListMode = Single
  @deprecated("MultiSelect ListMode has been renamed to Multiple")
  def MultiSelect: ListMode = Multiple

  val allValues: List[ListMode] = deriveAllValues

  def valueOf(value: ListMode): String = value.value

}
