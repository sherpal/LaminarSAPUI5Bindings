package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.*
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

/** The ui5-wizard helps users to complete a complex task by dividing it into sections and guiding them through it. It
  * has two main areas - a navigation area at the top showing the step sequence and a content area below it.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Wizard/">the doc</a> for more information.
  */
object Wizard extends WebComponent {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/Wizard.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = customHtmlTag("ui5-wizard")

  object slots {}

  object events {
    trait StepChangeInfo extends js.Object {
      def step: WizardStep.Ref
      def previousStep: WizardStep.Ref
      def changeWithClick: Boolean
    }

    val onStepChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[StepChangeInfo]] = new EventProp("step-change")
  }

  

  def step: WizardStep.type = WizardStep

}
