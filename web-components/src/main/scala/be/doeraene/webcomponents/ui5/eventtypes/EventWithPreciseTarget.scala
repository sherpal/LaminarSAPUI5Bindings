package be.doeraene.webcomponents.ui5.eventtypes

import org.scalajs.dom
import org.scalajs.dom.{EventInit, EventTarget}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal
class EventWithPreciseTarget[T <: EventTarget](typeArg: String, init: js.UndefOr[EventInit] = js.undefined)
    extends dom.Event(typeArg, init) {

  override def target: T = js.native

}
