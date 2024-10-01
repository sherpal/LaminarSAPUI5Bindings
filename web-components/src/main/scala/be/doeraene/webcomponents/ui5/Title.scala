package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
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

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-title")

  lazy val level: HtmlAttr[TitleLevel]          = htmlAttr("level", TitleLevel.AsStringCodec)
  lazy val size: HtmlAttr[TitleLevel]           = htmlAttr("size", TitleLevel.AsStringCodec)
  lazy val wrappingType: HtmlAttr[WrappingType] = htmlAttr("wrapping-type", WrappingType.AsStringCodec)

  object slots {}

  object events {}

  /** Creates Title of H1 size. */
  def h1(mods: (ModFunction | Mod[HtmlElement])*): HtmlElement = apply(
    mods :+ (_.size := TitleLevel.H1)*
  )

  /** Creates Title of H2 size. */
  def h2(mods: (ModFunction | Mod[HtmlElement])*): HtmlElement = apply(
    mods :+ (_.size := TitleLevel.H2)*
  )

  /** Creates Title of H3 size. */
  def h3(mods: (ModFunction | Mod[HtmlElement])*): HtmlElement = apply(
    mods :+ (_.size := TitleLevel.H3)*
  )

  /** Creates Title of H4 size. */
  def h4(mods: (ModFunction | Mod[HtmlElement])*): HtmlElement = apply(
    mods :+ (_.size := TitleLevel.H4)*
  )

  /** Creates Title of H4 size. */
  def h5(mods: (ModFunction | Mod[HtmlElement])*): HtmlElement = apply(
    mods :+ (_.size := TitleLevel.H5)*
  )

  /** Creates Title of H6 size. */
  def h6(mods: (ModFunction | Mod[HtmlElement])*): HtmlElement = apply(
    mods :+ (_.size := TitleLevel.H6)*
  )

}
