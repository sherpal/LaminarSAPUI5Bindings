package be.doeraene.webcomponents.ui5.configkeys

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

trait CalendarType {
  def value: String = toString
}

object CalendarType extends EnumerationString[CalendarType] {

  // /!\ To use any but the Gregorian type, you need to call one of the imports below anywhere in your code.

  case object Gregorian extends CalendarType
  case object Buddhist extends CalendarType
  case object Islamic extends CalendarType
  case object Japanese extends CalendarType
  case object Persian extends CalendarType

  @js.native
  @JSImport("@ui5/webcomponents-localization/dist/features/calendar/Buddhist.js", JSImport.Default)
  object BuddhistImport extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents-localization/dist/features/calendar/Islamic.js", JSImport.Default)
  object IslamicImport extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents-localization/dist/features/calendar/Japanese.js", JSImport.Default)
  object JapaneseImport extends js.Object

  @js.native
  @JSImport("@ui5/webcomponents-localization/dist/features/calendar/Persian.js", JSImport.Default)
  object PersianImport extends js.Object

  val allValues: List[CalendarType] = List(Gregorian, Buddhist, Islamic, Japanese, Persian)

  def valueOf(value: CalendarType): String = value.value
}
