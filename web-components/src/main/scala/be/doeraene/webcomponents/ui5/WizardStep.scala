package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.*
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.{HtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** A component that represents a logical step as part of the ui5-wizard. It is meant to aggregate arbitrary HTML
  * elements that form the content of a single step.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Wizard/">the doc</a> for more information.
  */
object WizardStep extends WebComponent with HasIcon {

  @js.native
  trait RawElement extends js.Object {}

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-wizard-step")

  lazy val branching: HtmlAttr[Boolean] = customHtmlAttr("branching", BooleanAsAttrPresenceCodec)

  lazy val disabled: HtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)

  lazy val selected: HtmlAttr[Boolean] = customHtmlAttr("selected", BooleanAsAttrPresenceCodec)

  lazy val subtitleText: HtmlAttr[String] = customHtmlAttr("subtitle-text", StringAsIsCodec)

  lazy val titleText: HtmlAttr[String] = customHtmlAttr("title-text", StringAsIsCodec)

  object slots {}

  object events {}

}
