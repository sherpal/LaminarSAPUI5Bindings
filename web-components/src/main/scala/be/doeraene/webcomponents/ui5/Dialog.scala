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

/** The ui5-dialog component is used to temporarily display some information in a size-limited window in front of the
  * regular app screen.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Dialog/">the doc</a> for more information.
  */
object Dialog {

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

  type Ref         = dom.html.Element with RawElement
  type ModFunction = Dialog.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-dialog")

  val id: ReactiveProp[String, String] = idAttr

  val headerText: ReactiveHtmlAttr[String] = customHtmlAttr("header-text", StringAsIsCodec)

  val resizable: ReactiveHtmlAttr[Boolean] = customHtmlAttr("resizable", BooleanAsAttrPresenceCodec)
  val stretch: ReactiveHtmlAttr[Boolean]   = customHtmlAttr("stretch", BooleanAsAttrPresenceCodec)
  val draggable: ReactiveHtmlAttr[Boolean] = customHtmlAttr("draggable", BooleanAsAttrPresenceCodec)
  val open: ReactiveHtmlAttr[Boolean]      = customHtmlAttr("open", BooleanAsAttrPresenceCodec)
  val preventFocusRestore: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("prevent-focus-restore", BooleanAsAttrPresenceCodec)
  val initialFocus: ReactiveHtmlAttr[String] = customHtmlAttr("initial-focus", StringAsIsCodec)

  //noinspection TypeAnnotation
  object slots {
    val footer: Slot = new Slot("footer")
    val header: Slot = new Slot("header")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Dialog)): _*)

  def getDialogById(id: String): Option[dom.HTMLElement & RawElement] =
    Option(dom.document.getElementById(id)).map(_.asInstanceOf[dom.HTMLElement & RawElement])

}
