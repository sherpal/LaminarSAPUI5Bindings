package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName, PopupAccessibleRole, ValueState}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The ui5-dialog component is used to temporarily display some information in a size-limited window in front of the
  * regular app screen.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Dialog/">the doc</a> for more information.
  */
object Dialog extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def show(): Unit = js.native

    def applyFocus(): Unit = js.native

    def close(): Unit = js.native

    def isOpen(): Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Dialog.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-dialog")

  lazy val accessibleRole: HtmlAttr[PopupAccessibleRole] =
    htmlAttr("accessible-role", PopupAccessibleRole.AsStringCodec)
  lazy val draggable: HtmlAttr[Boolean]           = htmlAttr("draggable", BooleanAsAttrPresenceCodec)
  lazy val headerText: HtmlAttr[String]           = htmlAttr("header-text", StringAsIsCodec)
  lazy val initialFocus: HtmlAttr[String]         = htmlAttr("initial-focus", StringAsIsCodec)
  lazy val open: HtmlAttr[Boolean]                = htmlAttr("open", BooleanAsAttrPresenceCodec)
  lazy val preventFocusRestore: HtmlAttr[Boolean] = htmlAttr("prevent-focus-restore", BooleanAsAttrPresenceCodec)
  lazy val resizable: HtmlAttr[Boolean]           = htmlAttr("resizable", BooleanAsAttrPresenceCodec)
  lazy val state: HtmlAttr[ValueState]            = htmlAttr("state", ValueState.AsStringCodec)
  lazy val stretch: HtmlAttr[Boolean]             = htmlAttr("stretch", BooleanAsAttrPresenceCodec)

  //noinspection TypeAnnotation
  object slots {
    val footer: Slot = new Slot("footer")
    val header: Slot = new Slot("header")
  }

  def getDialogById(id: String): Option[Ref] =
    Option(dom.document.getElementById(id)).map(_.asInstanceOf[Ref])

  /** [[Observer]] you can feed to open the Dialog. */
  val showObserver: Observer[Ref] = Observer(_.show())

  def showFromEvents(openerEvents: EventStream[Unit]): Mod[ReactiveHtmlElement[Ref]] =
    inContext[ReactiveHtmlElement[Ref]](el => openerEvents.mapTo(el.ref) --> showObserver)

  /** [[Observer]] you can feed a [[Dialog]] ref to close it. */
  val closeObserver: Observer[Ref] = Observer(_.close())

  def closeFromEvents(closeEvents: EventStream[Unit]): Mod[ReactiveHtmlElement[Ref]] =
    inContext[ReactiveHtmlElement[Ref]](el => closeEvents.mapTo(el.ref) --> closeObserver)

  /** [[Observer]] you can feed a [[Dialog]] ref to apply focus to it. */
  val applyFocusObserver: Observer[Ref] = Observer(_.applyFocus())

}
