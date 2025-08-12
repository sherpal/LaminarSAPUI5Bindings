package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.IntAsStringCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.configkeys.IconName
import be.doeraene.webcomponents.ui5.configkeys.TableGrowingMode

object TableGrowing extends WebComponent {

  @js.native
  trait RawElement extends js.Object:
    def rowKey: String = js.native

  @js.native
  @JSImport("@ui5/webcomponents/dist/TableGrowing.js", JSImport.Default)
  object RawImport extends js.Object
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-table-growing")

  lazy val mode: HtmlAttr[TableGrowingMode] = TableGrowingMode.asHtmlAttr("mode")
  lazy val text: HtmlAttr[String]           = htmlAttr("text", StringAsIsCodec)
  lazy val subtext: HtmlAttr[String]        = htmlAttr("subtext", StringAsIsCodec)

  object slots {}

  object events {
    val onLoadMore: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("load-more")
  }

}
