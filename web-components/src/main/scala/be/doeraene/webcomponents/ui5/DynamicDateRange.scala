package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ButtonDesign
import be.doeraene.webcomponents.ui5.configkeys.CalendarType
import be.doeraene.webcomponents.ui5.configkeys.IconName
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.EventProcessor
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import java.time.LocalDate
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.annotation.JSName
import com.raquo.laminar.codecs.Codec

/** The ui5-dynamic-date-range component provides a flexible interface to define date ranges using a combination of
  * absolute dates, relative intervals, and preset ranges (e.g., "Today", "Yesterday", etc.). It allows users to select
  * a date range from a predefined set of options or enter custom dates.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/components/main/DynamicDateRange/">the doc</a> for more
  *   information.
  */
object DynamicDateRange extends WebComponent with HasAccessibleName with HasName with HasValue {

  trait DynamicDateRangeValue extends js.Object {
    def operator: String
    def values: js.Array[js.Date] | js.Array[Double]
    def toDates: js.Array[js.Date]
  }

  object DynamicDateRangeValue {
    extension (value: DynamicDateRangeValue)
      def dates: (js.Date, js.Date) =
        val values = value.toDates
        (values(0), values(1))
  }

  @js.native
  trait RawElement extends js.Object {
    def value: DynamicDateRangeValue = js.native
  }

  object RawElement {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/DynamicDateRange.js", JSImport.Default)
  object RawImport extends js.Object

  sealed trait DynamicDateRangeOperatorImport extends js.Object
  @js.native @JSImport("@ui5/webcomponents/dist/dynamic-date-range-options/Today.js", JSImport.Default)
  object TodayImport extends DynamicDateRangeOperatorImport
  @js.native @JSImport("@ui5/webcomponents/dist/dynamic-date-range-options/Yesterday.js", JSImport.Default)
  object YesterdayImport extends DynamicDateRangeOperatorImport
  @js.native @JSImport("@ui5/webcomponents/dist/dynamic-date-range-options/Tomorrow.js", JSImport.Default)
  object TomorrowImport extends DynamicDateRangeOperatorImport
  @js.native @JSImport("@ui5/webcomponents/dist/dynamic-date-range-options/DateRange.js", JSImport.Default)
  object DateRangeImport extends DynamicDateRangeOperatorImport
  @js.native @JSImport("@ui5/webcomponents/dist/dynamic-date-range-options/SingleDate.js", JSImport.Default)
  object DateImport extends DynamicDateRangeOperatorImport

  opaque type DynamicDateRangeOperator = String
  object DynamicDateRangeOperator {
    def Today: DynamicDateRangeOperator = {
      TodayImport
      "TODAY"
    }
    def Tomorrow: DynamicDateRangeOperator = {
      TomorrowImport
      "TOMORROW"
    }
    def Yesterday: DynamicDateRangeOperator = {
      YesterdayImport
      "YESTERDAY"
    }
    def Date: DynamicDateRangeOperator = {
      DateImport
      "DATE"
    }
    def DateRange: DynamicDateRangeOperator = {
      DateRangeImport
      "DATERANGE"
    }
  }

  private object DynamicDateRangeOperatorCodec extends Codec[Seq[DynamicDateRangeOperator], String] {
    override def decode(domValue: String): Seq[DynamicDateRangeOperator]   = domValue.split(",").map(_.trim).toVector
    override def encode(scalaValue: Seq[DynamicDateRangeOperator]): String = scalaValue.mkString(", ")
  }

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-dynamic-date-range")

  lazy val options: HtmlAttr[Seq[DynamicDateRangeOperator]] = htmlAttr("options", DynamicDateRangeOperatorCodec)

  object slots {}

  object events {
    sealed trait ChangeEventData extends js.Object {
      def values: js.UndefOr[js.Array[js.Date]]
      def operator: DynamicDateRangeOperator
    }

    val onChange = new EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasDetail.HasValue[ChangeEventData]]]("change")
  }

}
