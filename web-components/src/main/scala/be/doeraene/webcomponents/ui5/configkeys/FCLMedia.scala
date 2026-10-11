package be.doeraene.webcomponents.ui5.configkeys

/** Screen size ("media") categories used by the [[be.doeraene.webcomponents.ui5.FlexibleColumnLayout]], e.g. in the
  * `layout-configuration-change` event.
  */
sealed trait FCLMedia {
  def value: String
}

object FCLMedia extends EnumerationString[FCLMedia] {

  case object Phone extends FCLMedia {
    def value: String = "phone"
  }

  case object Tablet extends FCLMedia {
    def value: String = "tablet"
  }

  case object Desktop extends FCLMedia {
    def value: String = "desktop"
  }

  val allValues: List[FCLMedia] = deriveAllValues

  def valueOf(value: FCLMedia): String = value.value

  type StringFCLMedia = "phone" | "tablet" | "desktop"

}
