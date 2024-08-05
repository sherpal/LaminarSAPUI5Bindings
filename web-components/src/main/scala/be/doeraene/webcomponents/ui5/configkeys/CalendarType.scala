package be.doeraene.webcomponents.ui5.configkeys

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

sealed trait CalendarType {
  def value: String = toString

  /** You can call this object wherever in your code to be sure that you have support for this [[CalendarType]] */
  def importObject: CalendarType.CalendarTypeImporter[this.type]
}

object CalendarType extends EnumerationString[CalendarType] {

  // /!\ To use any but the Gregorian type, you need to call one of the imports below anywhere in your code.

  case object Gregorian extends CalendarType {
    def importObject: CalendarType.CalendarTypeImporter[this.type] = GregorianImport
  }
  case object Buddhist extends CalendarType {
    def importObject: CalendarType.CalendarTypeImporter[this.type] = BuddhistImport
  }
  case object Islamic extends CalendarType {
    def importObject: CalendarType.CalendarTypeImporter[this.type] = IslamicImport
  }
  case object Japanese extends CalendarType {
    def importObject: CalendarType.CalendarTypeImporter[this.type] = JapaneseImport
  }
  case object Persian extends CalendarType {
    def importObject: CalendarType.CalendarTypeImporter[this.type] = PersianImport
  }

  /** Marker trait to specify the the object is used to import a specific calendar. */
  sealed trait CalendarTypeImporter[For <: CalendarType] extends js.Object

  // Gregorian calendar is imported by default, so this object is here only for consistency
  object GregorianImport extends CalendarTypeImporter[Gregorian.type]

  @js.native
  @JSImport("@ui5/webcomponents-localization/dist/features/calendar/Buddhist.js", JSImport.Namespace)
  object BuddhistImport extends CalendarTypeImporter[Buddhist.type]

  @js.native
  @JSImport("@ui5/webcomponents-localization/dist/features/calendar/Islamic.js", JSImport.Namespace)
  object IslamicImport extends CalendarTypeImporter[Islamic.type]

  @js.native
  @JSImport("@ui5/webcomponents-localization/dist/features/calendar/Japanese.js", JSImport.Namespace)
  object JapaneseImport extends CalendarTypeImporter[Japanese.type]

  @js.native
  @JSImport("@ui5/webcomponents-localization/dist/features/calendar/Persian.js", JSImport.Namespace)
  object PersianImport extends CalendarTypeImporter[Persian.type]

  val allValues: List[CalendarType] = deriveAllValues

  def valueOf(value: CalendarType): String = value.value
}
