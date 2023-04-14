package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.eventtypes.{HasDetail, HasSelectedIndex}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, IntAsStringCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.HtmlTag
import com.raquo.laminar.keys.HtmlAttr
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

  type Ref = dom.html.Element with RawElement

  protected val tag: HtmlTag[Ref] = htmlTag("ui5-carousel")

  lazy val arrowsPlacement: HtmlAttr[CarouselArrowsPlacement] =
    htmlAttr("arrows-placement", CarouselArrowsPlacement.AsStringCodec)

  lazy val cyclic: HtmlAttr[Boolean] = htmlAttr("cyclic", BooleanAsAttrPresenceCodec)

  lazy val hideNavigationArrows: HtmlAttr[Boolean] =
    htmlAttr("hide-navigation-arrows", BooleanAsAttrPresenceCodec)

  lazy val hidePageIndicator: HtmlAttr[Boolean] =
    htmlAttr("hide-page-indicator", BooleanAsAttrPresenceCodec)

  lazy val itemsPerPageL: HtmlAttr[Int] = htmlAttr("items-per-page-l", IntAsStringCodec)
  lazy val itemsPerPageM: HtmlAttr[Int] = htmlAttr("items-per-page-m", IntAsStringCodec)
  lazy val itemsPerPageS: HtmlAttr[Int] = htmlAttr("items-per-page-s", IntAsStringCodec)

  object slots {}

  object events {
    val onNavigate: EventProp[dom.Event with HasDetail[HasSelectedIndex]] = new EventProp("navigate")
  }

  def getCarouselById(carouselId: String): Option[dom.HTMLElement with RawElement] =
    Option(dom.document.getElementById(carouselId)).map(_.asInstanceOf[dom.HTMLElement with RawElement])

  def getCarousels: List[dom.HTMLElement with RawElement] =
    dom.document.getElementsByTagName("ui5-carousel").toList.map(_.asInstanceOf[dom.HTMLElement with RawElement])

}
