package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.WebComponent

/** The BarcodeScannerDialog component provides barcode scanning functionality for all devices that support the
  * MediaDevices.getUserMedia() native API. Opening the dialog launches the device camera and scans for known barcode
  * formats.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/BarcodeScannerDialog/">the doc</a> for more
  *   information.
  */
object BarcodeScannerDialog extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def close(): Unit = js.native
    def show(): Unit  = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/BarcodeScannerDialog.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = BarcodeScannerDialog.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-barcode-scanner-dialog")

  object slots {}

  object events {
    trait ErrorInfo extends js.Object {
      def message: String
    }

    val onScanError: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ErrorInfo]] = new EventProp("scan-error")

    trait SuccessInfo extends js.Object {
      def text: String
      def rawBytes: js.typedarray.Uint8Array
    }

    val onScanSuccess: EventProp[EventWithPreciseTarget[Ref] & HasDetail[SuccessInfo]] = new EventProp("scan-success")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(BarcodeScannerDialog)): _*)

  /** You can feed this [[Observer]] with a barcode scanner [[Ref]]s in order to close it. */
  val closeObserver: Observer[Ref] = Observer(_.close())

  /** Can be used as modifier to close the Barcode Scanner every time the stream emits. */
  def closeOnEvents(stream: EventStream[Unit]): Mod[ReactiveHtmlElement[Ref]] =
    inContext[ReactiveHtmlElement[Ref]](el => stream.mapTo(el.ref) --> closeObserver)

  /** You can feed this [[Observer]] with a barcode scanner [[Ref]]s in order to open it. */
  val showObserver: Observer[Ref] = Observer(_.show())

  /** Can be used as modifier to open the Barcode Scanner every time the stream emits. */
  def showOnEvents(stream: EventStream[Unit]): Mod[ReactiveHtmlElement[Ref]] =
    inContext[ReactiveHtmlElement[Ref]](el => stream.mapTo(el.ref) --> showObserver)

}
