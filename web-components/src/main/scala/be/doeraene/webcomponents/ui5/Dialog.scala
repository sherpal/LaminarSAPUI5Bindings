package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ButtonDesign
import be.doeraene.webcomponents.ui5.configkeys.IconName
import be.doeraene.webcomponents.ui5.configkeys.PopupAccessibleRole
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
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

/** The ui5-dialog component is used to temporarily display some information in a size-limited window in front of the
  * regular app screen.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Dialog/">the doc</a> for more information.
  */
object Dialog extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def open: Boolean = js.native

    def applyFocus(): Unit = js.native
  }

  object RawElement {
    extension (rawElement: RawElement) {
      @deprecated(
        "The show and close public methods have been removed. Use the public property open instead.",
        since = "2.0.0"
      )
      def show(): Unit = rawElement.asInstanceOf[js.Dynamic].updateDynamic("open")(true)

      @deprecated(
        "The show and close public methods have been removed. Use the public property open instead.",
        since = "2.0.0"
      )
      def close(): Unit = rawElement.asInstanceOf[js.Dynamic].updateDynamic("open")(false)

      @deprecated("The isOpen method has been removed. Use the public property open instead.", since = "2.0.0")
      def isOpen(): Boolean = rawElement.open
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Dialog.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-dialog")

  lazy val accessibleRole: HtmlAttr[PopupAccessibleRole] =
    htmlAttr("accessible-role", PopupAccessibleRole.AsStringCodec)
  lazy val draggable: HtmlAttr[Boolean]               = htmlAttr("draggable", BooleanAsAttrPresenceCodec)
  lazy val headerText: HtmlAttr[String]               = htmlAttr("header-text", StringAsIsCodec)
  lazy val initialFocus: HtmlAttr[String]             = htmlAttr("initial-focus", StringAsIsCodec)
  lazy val open: HtmlAttr[Boolean]                    = htmlAttr("open", BooleanAsAttrPresenceCodec)
  lazy val preventFocusRestore: HtmlAttr[Boolean]     = htmlAttr("prevent-focus-restore", BooleanAsAttrPresenceCodec)
  lazy val resizable: HtmlAttr[Boolean]               = htmlAttr("resizable", BooleanAsAttrPresenceCodec)
  lazy val state: HtmlAttr[ValueState]                = htmlAttr("state", ValueState.AsStringCodec)
  lazy val stretch: HtmlAttr[Boolean]                 = htmlAttr("stretch", BooleanAsAttrPresenceCodec)
  lazy val accessibleName: HtmlAttr[String]           = htmlAttr("accessible-name", StringAsIsCodec)
  lazy val accessibleNameRef: HtmlAttr[String]        = htmlAttr("accessible-name-ref", StringAsIsCodec)
  lazy val accessibleDescription: HtmlAttr[String]    = htmlAttr("accessible-description", StringAsIsCodec)
  lazy val accessibleDescriptionRef: HtmlAttr[String] = htmlAttr("accessible-description-ref", StringAsIsCodec)

  //noinspection TypeAnnotation
  object slots {
    val footer: Slot = new Slot("footer")
    val header: Slot = new Slot("header")
  }

  object events {
    lazy val onOpen: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("open")
    lazy val onClose: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("close")
  }

  def getDialogById(id: String): Option[Ref] =
    Option(dom.document.getElementById(id)).map(_.asInstanceOf[Ref])

  /** [[Observer]] you can feed to open the Dialog. */
  @deprecated(
    "The show and close public methods have been removed. Use the public property open instead.",
    since = "2.0.0"
  )
  def showObserver: Observer[Ref] = Observer(_.show())

  def showFromEvents(openerEvents: EventStream[Unit]): Mod[ReactiveHtmlElement[Ref]] =
    open <-- openerEvents.mapTo(true)

  /** [[Observer]] you can feed a [[Dialog]] ref to close it. */
  @deprecated(
    "The show and close public methods have been removed. Use the public property open instead.",
    since = "2.0.0"
  )
  def closeObserver: Observer[Ref] = Observer(_.close())

  def closeFromEvents(closeEvents: EventStream[Unit]): Mod[ReactiveHtmlElement[Ref]] =
    open <-- closeEvents.mapTo(false)

  /** [[Observer]] you can feed a [[Dialog]] ref to apply focus to it. */
  val applyFocusObserver: Observer[Ref] = Observer(_.applyFocus())

}
