package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

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
    def open: Boolean = js.native
  }

  object RawElement {
    extension (rawElement: RawElement) {
      @deprecated("The close method has been replaced by using the open property", since = "2.0.0")
      def close(): Unit =
        rawElement.asInstanceOf[js.Dynamic].updateDynamic("open")(false)

      @deprecated("The show method has been replaced by using the open property", since = "2.0.0")
      def show(): Unit =
        rawElement.asInstanceOf[js.Dynamic].updateDynamic("open")(true)
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/BarcodeScannerDialog.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-barcode-scanner-dialog")

  lazy val open: HtmlAttr[Boolean] = htmlAttr("open", BooleanAsAttrPresenceCodec)

  object slots {
    val header: Slot = Slot("header")
    val footer: Slot = Slot("footer")
  }

  object events {
    trait ErrorInfo extends js.Object {
      def message: String
    }

    val onScanError: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ErrorInfo]] = new EventProp("scan-error")

    trait SuccessInfo extends js.Object {
      def text: String
      def rawBytes: js.typedarray.Uint8Array
    }

    val onScanSuccess: EventProp[EventWithPreciseTarget[Ref] & HasDetail[SuccessInfo]] = new EventProp(
      "scan-success"
    )

    val onOpen: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("open")
    val onClose: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("close")
  }

  /** You can feed this [[Observer]] with a barcode scanner [[Ref]]s in order to close it. */
  @deprecated("The closeObserver has been replaced by using the open property", since = "2.0.0")
  def closeObserver: Observer[Ref] = Observer(_.close())

  /** Can be used as modifier to close the Barcode Scanner every time the stream emits. */
  @deprecated("The closeOnEvents has been replaced by using the open property", since = "2.0.0")
  def closeOnEvents(stream: EventStream[Unit]): Mod[ReactiveHtmlElement[Ref]] =
    inContext[ReactiveHtmlElement[Ref]](el => stream.mapTo(el.ref) --> closeObserver)

  /** You can feed this [[Observer]] with a barcode scanner [[Ref]]s in order to open it. */
  @deprecated("The showObserver has been replaced by using the open property", since = "2.0.0")
  val showObserver: Observer[Ref] = Observer(_.show())

  /** Can be used as modifier to open the Barcode Scanner every time the stream emits. */
  @deprecated("The showOnEvents has been replaced by using the open property", since = "2.0.0")
  def showOnEvents(stream: EventStream[Unit]): Mod[ReactiveHtmlElement[Ref]] =
    inContext[ReactiveHtmlElement[Ref]](el => stream.mapTo(el.ref) --> showObserver)

}
