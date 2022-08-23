package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.ui5.configkeys.TitleLevel
import be.doeraene.webcomponents.ui5.configkeys.WrappingType
import be.doeraene.webcomponents.WebComponent

/** The ui5-title component is used to display titles inside a page. It is a simple, large-sized text with explicit
  * header/title semantics.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Title/">the doc</a> for more information.
  */
object Title extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/Title.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-title")

  lazy val level: ReactiveHtmlAttr[TitleLevel]          = customHtmlAttr("level", TitleLevel.AsStringCodec)
  lazy val wrappingType: ReactiveHtmlAttr[WrappingType] = customHtmlAttr("wrapping-type", WrappingType.AsStringCodec)

  object slots {}

  object events {}

  /** Creates Title of H1 level. */
  def h1(mods: (ModFunction | Mod[ReactiveHtmlElement[Ref]])*): HtmlElement = apply(
    mods :+ (_.level := TitleLevel.H1): _*
  )

  /** Creates Title of H2 level. */
  def h2(mods: (ModFunction | Mod[ReactiveHtmlElement[Ref]])*): HtmlElement = apply(
    mods :+ (_.level := TitleLevel.H2): _*
  )

  /** Creates Title of H3 level. */
  def h3(mods: (ModFunction | Mod[ReactiveHtmlElement[Ref]])*): HtmlElement = apply(
    mods :+ (_.level := TitleLevel.H3): _*
  )

  /** Creates Title of H4 level. */
  def h4(mods: (ModFunction | Mod[ReactiveHtmlElement[Ref]])*): HtmlElement = apply(
    mods :+ (_.level := TitleLevel.H4): _*
  )

  /** Creates Title of H4 level. */
  def h5(mods: (ModFunction | Mod[ReactiveHtmlElement[Ref]])*): HtmlElement = apply(
    mods :+ (_.level := TitleLevel.H5): _*
  )

  /** Creates Title of H6 level. */
  def h6(mods: (ModFunction | Mod[ReactiveHtmlElement[Ref]])*): HtmlElement = apply(
    mods :+ (_.level := TitleLevel.H6): _*
  )

}
