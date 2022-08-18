package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
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

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-dialog")

  lazy val headerText: ReactiveHtmlAttr[String] = customHtmlAttr("header-text", StringAsIsCodec)

  lazy val resizable: ReactiveHtmlAttr[Boolean] = customHtmlAttr("resizable", BooleanAsAttrPresenceCodec)
  lazy val stretch: ReactiveHtmlAttr[Boolean]   = customHtmlAttr("stretch", BooleanAsAttrPresenceCodec)
  lazy val draggable: ReactiveHtmlAttr[Boolean] = customHtmlAttr("draggable", BooleanAsAttrPresenceCodec)
  lazy val open: ReactiveHtmlAttr[Boolean]      = customHtmlAttr("open", BooleanAsAttrPresenceCodec)
  lazy val preventFocusRestore: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("prevent-focus-restore", BooleanAsAttrPresenceCodec)
  lazy val initialFocus: ReactiveHtmlAttr[String] = customHtmlAttr("initial-focus", StringAsIsCodec)

  //noinspection TypeAnnotation
  object slots {
    val footer: Slot = new Slot("footer")
    val header: Slot = new Slot("header")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Dialog)): _*)

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
