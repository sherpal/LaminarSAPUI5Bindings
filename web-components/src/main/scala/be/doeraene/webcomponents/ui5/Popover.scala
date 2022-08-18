package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{PopoverHorizontalAlign, PopoverPlacementType, PopoverVerticalAlign}
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

/** The ui5-popover component displays additional information for an object in a compact way and without leaving the
  * page.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Popover/">the doc</a> for more information.
  */
object Popover extends WebComponent with HasAccessibleName {

  @js.native
  trait RawElement extends js.Object {
    def showAt(opener: dom.HTMLElement): Unit = js.native

    def applyFocus(): Unit = js.native

    def close(): Unit = js.native

    def isOpen(): Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Popover.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-popover")

  lazy val allowTargetOverlap: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("allow-target-overlap", BooleanAsAttrPresenceCodec)

  lazy val headerText: ReactiveHtmlAttr[String] = customHtmlAttr("header-text", StringAsIsCodec)

  lazy val hideArrow: ReactiveHtmlAttr[Boolean] = customHtmlAttr("hide-arrow", BooleanAsAttrPresenceCodec)

  lazy val hideBackdrop: ReactiveHtmlAttr[Boolean] = customHtmlAttr("hide-backdrop", BooleanAsAttrPresenceCodec)

  lazy val horizontalAlign: ReactiveHtmlAttr[PopoverHorizontalAlign] =
    customHtmlAttr("horizontal-align", PopoverHorizontalAlign.AsStringCodec)

  lazy val modal: ReactiveHtmlAttr[Boolean] = customHtmlAttr("modal", BooleanAsAttrPresenceCodec)

  /** id of the element that opens the popover */
  lazy val opener: ReactiveHtmlAttr[String] = customHtmlAttr("opener", StringAsIsCodec)

  lazy val placementType: ReactiveHtmlAttr[PopoverPlacementType] =
    customHtmlAttr("placement-type", PopoverPlacementType.AsStringCodec)

  lazy val verticalAlign: ReactiveHtmlAttr[PopoverVerticalAlign] =
    customHtmlAttr("vertical-align", PopoverVerticalAlign.AsStringCodec)

  lazy val initialFocus: ReactiveHtmlAttr[String] = customHtmlAttr("initial-focus", StringAsIsCodec)

  lazy val open: ReactiveHtmlAttr[Boolean] = customHtmlAttr("open", BooleanAsAttrPresenceCodec)

  lazy val preventFocusRestore: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("prevent-focus-restore", BooleanAsAttrPresenceCodec)

  object slots {
    def header: Slot = new Slot("header")
    def footer: Slot = new Slot("footer")
  }

  object events {
    val onAfterClose: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("after-close")
    val onAfterOpen: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("after-open")

    trait BeforeCloseInfo extends js.Object {
      def escPressed: Boolean
    }

    val onBeforeClose: EventProp[EventWithPreciseTarget[Ref] & HasDetail[BeforeCloseInfo]] = new EventProp(
      "before-close"
    )
    val onBeforeOpen: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("before-open")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Popover)): _*)

  def getPopoverById(id: String): Option[Ref] =
    Option(dom.document.getElementById(id)).map(_.asInstanceOf[dom.HTMLElement & RawElement])

  /** [[Observer]] you can feed a popover ref and a [[dom.HTMLElement]] to open the popover at the element. */
  val showAtObserver: Observer[(Ref, dom.HTMLElement)] = Observer(_ showAt _)

  /** [[Mod]] for [[Popover]]s opening them each time the stream emits an opener [[dom.HTMLElement]] */
  def showAtFromEvents(openerEvents: EventStream[dom.HTMLElement]): Mod[ReactiveHtmlElement[Ref]] =
    inContext[ReactiveHtmlElement[Ref]](el => openerEvents.map(el.ref -> _) --> showAtObserver)

  /** [[Observer]] you can feed a popover ref to close it. */
  val closeObserver: Observer[Ref] = Observer(_.close())

  /** [[Mod]] for [[Popover]]s closing them each time the stream emits. */
  def closeFromEvents(closeEvents: EventStream[Unit]): Mod[ReactiveHtmlElement[Ref]] =
    inContext[ReactiveHtmlElement[Ref]](el => closeEvents.mapTo(el.ref) --> closeObserver)

  /** [[Observer]] you can feed a popover ref to apply focus to it. */
  val applyFocusObserver: Observer[Ref] = Observer(_.applyFocus())

}
