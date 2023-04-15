package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{PopoverHorizontalAlign, PopoverPlacementType, PopoverVerticalAlign}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.HtmlAttr
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

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-popover")

  lazy val allowTargetOverlap: HtmlAttr[Boolean] =
    htmlAttr("allow-target-overlap", BooleanAsAttrPresenceCodec)

  lazy val headerText: HtmlAttr[String] = htmlAttr("header-text", StringAsIsCodec)

  lazy val hideArrow: HtmlAttr[Boolean] = htmlAttr("hide-arrow", BooleanAsAttrPresenceCodec)

  lazy val hideBackdrop: HtmlAttr[Boolean] = htmlAttr("hide-backdrop", BooleanAsAttrPresenceCodec)

  lazy val horizontalAlign: HtmlAttr[PopoverHorizontalAlign] =
    htmlAttr("horizontal-align", PopoverHorizontalAlign.AsStringCodec)

  lazy val modal: HtmlAttr[Boolean] = htmlAttr("modal", BooleanAsAttrPresenceCodec)

  /** id of the element that opens the popover */
  lazy val opener: HtmlAttr[String] = htmlAttr("opener", StringAsIsCodec)

  lazy val placementType: HtmlAttr[PopoverPlacementType] =
    htmlAttr("placement-type", PopoverPlacementType.AsStringCodec)

  lazy val verticalAlign: HtmlAttr[PopoverVerticalAlign] =
    htmlAttr("vertical-align", PopoverVerticalAlign.AsStringCodec)

  lazy val initialFocus: HtmlAttr[String] = htmlAttr("initial-focus", StringAsIsCodec)

  lazy val open: HtmlAttr[Boolean] = htmlAttr("open", BooleanAsAttrPresenceCodec)

  lazy val preventFocusRestore: HtmlAttr[Boolean] =
    htmlAttr("prevent-focus-restore", BooleanAsAttrPresenceCodec)

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

    val onBeforeClose: EventProp[EventWithPreciseTarget[Ref] with HasDetail[BeforeCloseInfo]] = new EventProp(
      "before-close"
    )
    val onBeforeOpen: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("before-open")
  }

  def getPopoverById(id: String): Option[Ref] =
    Option(dom.document.getElementById(id)).map(_.asInstanceOf[dom.HTMLElement with RawElement])

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

  /** Combines both showAtFromEvents and closeFromEvents from an event stream emitting maybe an opener.
    *
    * When the event stream emits Some an element, opens the [[Popover]] at that element. Otherwise, closes the
    * [[Popover]]
    */
  def showAtAndCloseFromEvents(
      openerAndCloseEvents: EventStream[Option[dom.HTMLElement]]
  ): Mod[ReactiveHtmlElement[Ref]] =
    List(
      showAtFromEvents(openerAndCloseEvents.collect { case Some(element) => element }),
      closeFromEvents(openerAndCloseEvents.collect { case None => () })
    )

  /** [[Observer]] you can feed a popover ref to apply focus to it. */
  val applyFocusObserver: Observer[Ref] = Observer(_.applyFocus())

}
