package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{
  PopoverHorizontalAlign,
  PopoverPlacementType,
  PopoverVerticalAlign,
  PopupAccessibleRole
}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
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
    var opener: js.UndefOr[dom.HTMLElement | String] = js.native

    def open: Boolean = js.native

    def applyFocus(): Unit = js.native

  }

  object RawElement {
    extension (rawElement: RawElement) {
      @deprecated("close method is replaced by using the open property", since = "2.0.0")
      def close(): Unit =
        rawElement.asInstanceOf[js.Dynamic].updateDynamic("open")(false)

      @deprecated("showAt method is replaced by using the open and opener property", since = "2.0.0")
      def showAt(opener: dom.HTMLElement): Unit = {
        rawElement.asInstanceOf[js.Dynamic].updateDynamic("opener")(opener)
        scala.scalajs.js.timers.setTimeout(0) {
          rawElement.asInstanceOf[js.Dynamic].updateDynamic("open")(true)
        }
      }

      @deprecated("isOpen has been removed, use the open property instead.")
      def isOpen(): Boolean = rawElement.open
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Popover.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-popover")

  lazy val accessibleRole: HtmlAttr[PopupAccessibleRole] =
    htmlAttr("accessible-role", PopupAccessibleRole.AsStringCodec)

  lazy val allowTargetOverlap: HtmlAttr[Boolean] =
    htmlAttr("allow-target-overlap", BooleanAsAttrPresenceCodec)

  lazy val headerText: HtmlAttr[String] = htmlAttr("header-text", StringAsIsCodec)

  lazy val hideArrow: HtmlAttr[Boolean] = htmlAttr("hide-arrow", BooleanAsAttrPresenceCodec)

  lazy val horizontalAlign: HtmlAttr[PopoverHorizontalAlign] =
    PopoverHorizontalAlign.asHtmlAttr("horizontal-align")

  lazy val modal: HtmlAttr[Boolean] = htmlAttr("modal", BooleanAsAttrPresenceCodec)

  /** id of the element that opens the popover */
  lazy val openerId: HtmlAttr[String] = htmlAttr("opener", StringAsIsCodec)

  lazy val placement: HtmlAttr[PopoverPlacementType] =
    PopoverPlacementType.asHtmlAttr("placement")

  lazy val verticalAlign: HtmlAttr[PopoverVerticalAlign] =
    htmlAttr("vertical-align", PopoverVerticalAlign.AsStringCodec)

  lazy val initialFocus: HtmlAttr[String] = htmlAttr("initial-focus", StringAsIsCodec)

  lazy val open: HtmlAttr[Boolean] = htmlAttr("open", BooleanAsAttrPresenceCodec)

  lazy val preventFocusRestore: HtmlAttr[Boolean] =
    htmlAttr("prevent-focus-restore", BooleanAsAttrPresenceCodec)

  lazy val preventInitialFocus: HtmlAttr[Boolean] =
    htmlAttr("prevent-initial-focus", BooleanAsAttrPresenceCodec)

  @scala.annotation.compileTimeOnly("hideBackdrop property has been removed.")
  def hideBackdrop: HtmlAttr[Boolean] = ???

  @deprecated("opener has been renamed to openerId", since = "2.0.0")
  def opener: HtmlAttr[String] = openerId

  @deprecated("placementType has been renamed to placement", since = "2.0.0")
  def placementType: HtmlAttr[PopoverPlacementType] = placement

  object slots {
    def header: Slot = new Slot("header")
    def footer: Slot = new Slot("footer")
  }

  object events {
    val onClose: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("close")
    val onOpen: EventProp[EventWithPreciseTarget[Ref]]  = new EventProp("open")

    @deprecated("onAfterClose has been renamed to onClose", since = "2.0.0")
    def onAfterClose: EventProp[EventWithPreciseTarget[Ref]] = onClose
    @deprecated("onAfterOpen has been renamed to onOpen", since = "2.0.0")
    def onAfterOpen: EventProp[EventWithPreciseTarget[Ref]] = onOpen

    trait BeforeCloseInfo extends js.Object {
      def escPressed: Boolean
    }

    val onBeforeClose: EventProp[EventWithPreciseTarget[Ref] & HasDetail[BeforeCloseInfo]] = new EventProp(
      "before-close"
    )
    val onBeforeOpen: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("before-open")
  }

  def getPopoverById(id: String): Option[Ref] =
    Option(dom.document.getElementById(id)).map(_.asInstanceOf[dom.HTMLElement & RawElement])

  /** [[Observer]] you can feed a popover ref and a [[dom.HTMLElement]] to open the popover at the element. */
  def showAtObserver: Observer[(Ref, dom.HTMLElement)] = Observer { (ref, opener) =>
    val dyn = ref.asInstanceOf[js.Dynamic]
    ref.opener = opener
    js.timers.setTimeout(0) {
      dyn.updateDynamic("open")(true)
    }
  }

  /** [[Mod]] for [[Popover]]s opening them each time the stream emits an opener [[dom.HTMLElement]] */
  def showAtFromEvents(openerEvents: EventStream[dom.HTMLElement]): Mod[ReactiveHtmlElement[Ref]] =
    inContext[ReactiveHtmlElement[Ref]](el => openerEvents.map(el.ref -> _) --> showAtObserver)

  /** [[Observer]] you can feed a popover ref to close it. */
  @deprecated("closeObserver has been replaced by using the open property", since = "2.0.0")
  def closeObserver: Observer[Ref] = Observer(_.asInstanceOf[js.Dynamic].updateDynamic("open")(false))

  /** [[Mod]] for [[Popover]]s closing them each time the stream emits. */
  @deprecated("closeFromEvents has been replaced by using the open property", since = "2.0.0")
  def closeFromEvents(closeEvents: EventStream[Unit]): Mod[ReactiveHtmlElement[Ref]] =
    open <-- closeEvents.mapTo(false)

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
      open <-- openerAndCloseEvents.collect { case None => false }
    )

  /** [[Observer]] you can feed a popover ref to apply focus to it. */
  val applyFocusObserver: Observer[Ref] = Observer(_.applyFocus())

}
