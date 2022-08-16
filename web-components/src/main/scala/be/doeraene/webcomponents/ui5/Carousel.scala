package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.eventtypes.{HasDetail, HasSelectedIndex}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, IntAsStringCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent

/** The Carousel allows the user to browse through a set of items. The component is mostly used for showing a gallery of
  * images, but can hold any other HTML element.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Carousel/">the doc</a> for more
  *   information.
  */
object Carousel extends WebComponent {

  //noinspection ScalaUnusedSymbol
  @js.native
  trait RawElement extends js.Object {
    def navigateTo(itemIndex: Int): Unit = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Carousel.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = Carousel.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-carousel")

  val arrowsPlacement: ReactiveHtmlAttr[CarouselArrowsPlacement] =
    customHtmlAttr("arrows-placement", CarouselArrowsPlacement.AsStringCodec)

  val cyclic: ReactiveHtmlAttr[Boolean] = customHtmlAttr("cyclic", BooleanAsAttrPresenceCodec)

  val hideNavigationArrows: ReactiveHtmlAttr[Boolean] =
    customHtmlAttr("hide-navigation-arrows", BooleanAsAttrPresenceCodec)

  val hidePageIndicator: ReactiveHtmlAttr[Boolean] = customHtmlAttr("hide-page-indicator", BooleanAsAttrPresenceCodec)

  val itemsPerPageL: ReactiveHtmlAttr[Int] = customHtmlAttr("items-per-page-l", IntAsStringCodec)
  val itemsPerPageM: ReactiveHtmlAttr[Int] = customHtmlAttr("items-per-page-m", IntAsStringCodec)
  val itemsPerPageS: ReactiveHtmlAttr[Int] = customHtmlAttr("items-per-page-s", IntAsStringCodec)

  object slots {}

  object events {
    val onNavigate: EventProp[dom.Event & HasDetail[HasSelectedIndex]] = new EventProp("navigate")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Carousel)): _*)

  def getCarouselById(carouselId: String): Option[dom.HTMLElement & RawElement] =
    Option(dom.document.getElementById(carouselId)).map(_.asInstanceOf[dom.HTMLElement & RawElement])

  def getCarousels: List[dom.HTMLElement & RawElement] =
    dom.document.getElementsByTagName("ui5-carousel").toList.map(_.asInstanceOf[dom.HTMLElement & RawElement])

}
