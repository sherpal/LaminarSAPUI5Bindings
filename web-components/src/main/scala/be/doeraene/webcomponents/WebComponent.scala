package be.doeraene.webcomponents

import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.ReactiveProp
import com.raquo.laminar.nodes.ReactiveHtmlElement

import org.scalajs.dom

/** Marker trait that all web components inherit.
  *
  * This can allow you to implement some shenanigans and abstract over some thins.
  */
trait WebComponent {
  val id: ReactiveProp[String, String] = idAttr

  type Ref <: dom.HTMLElement

  type ModFunction = this.type => Mod[ReactiveHtmlElement[Ref]]

  protected def tag: HtmlTag[Ref]

  final def apply(mods: (ModFunction | Mod[ReactiveHtmlElement[Ref]])*): HtmlElement = tag(
    mods
      .map {
        case mod: Mod[_ >: ReactiveHtmlElement[Ref]]                      => (_: this.type) => mod
        case mod: Function[_ >: this.type, _ <: ReactiveHtmlElement[Ref]] => mod
      }
      .map(_(this)): _*
  )
}
