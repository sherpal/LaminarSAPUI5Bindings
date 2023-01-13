package be.doeraene.webcomponents

import com.raquo.laminar.api.L.*

import org.scalajs.dom
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.HtmlTag

/** Marker trait that all web components inherit.
  *
  * This can allow you to implement some shenanigans and abstract over some thins.
  */
trait WebComponent {
  val id: HtmlProp[String] = idAttr

  type Ref <: dom.HTMLElement

  type ModFunction = this.type => Mod[ReactiveHtmlElement[Ref]]
  type ComponentMod = ModFunction | Mod[ReactiveHtmlElement[Ref]]

  protected def tag: HtmlTag[Ref]

  /** Instantiate this component using the specified modifiers.
    *
    * Modifiers can be the usual Laminar modifiers, or they can be functions from this component to a modifier. Allowing
    * these functions is very practical to access the reactive attributes of the component, with the `_.reactiveAttr`
    * syntax.
    */
  final def apply(mods: ComponentMod*): HtmlElement = tag(
    mods
      .map {
        case mod: Mod[_ >: ReactiveHtmlElement[Ref]]                      => (_: this.type) => mod
        case mod: Function[_ >: this.type, _ <: ReactiveHtmlElement[Ref]] => mod
      }
      .map(_(this)): _*
  )

  /** Same as [[apply]], but accept only [[ModFunction]]s.
    *
    * This function is only there for people using the library with Scala 2.13.
    */
  final def of(mods: ModFunction*): HtmlElement = tag(mods.map(_(this)): _*)

}
