package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.eventtypes.{HasDetail, HasSelectedIndex}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, IntAsStringCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.CarouselPageIndicatorType
import com.raquo.laminar.codecs.Codec

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

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-carousel")

  lazy val accessibleName: HtmlAttr[String] =
    htmlAttr("accessible-name", StringAsIsCodec)
  lazy val accessibleNameRef: HtmlAttr[String] =
    htmlAttr("accessible-name-ref", StringAsIsCodec)

  lazy val arrowsPlacement: HtmlAttr[CarouselArrowsPlacement] =
    htmlAttr("arrows-placement", CarouselArrowsPlacement.AsStringCodec)

  lazy val cyclic: HtmlAttr[Boolean] = htmlAttr("cyclic", BooleanAsAttrPresenceCodec)

  lazy val hideNavigationArrows: HtmlAttr[Boolean] =
    htmlAttr("hide-navigation-arrows", BooleanAsAttrPresenceCodec)

  lazy val hidePageIndicator: HtmlAttr[Boolean] =
    htmlAttr("hide-page-indicator", BooleanAsAttrPresenceCodec)

  case class ItemsPerPage(small: Int, medium: Int, large: Int, extraLarge: Int)

  object ItemsPerPageAsStringCodec extends Codec[ItemsPerPage, String] {
    def decode(domValue: String): ItemsPerPage = ???

    def encode(v: ItemsPerPage): String = s"S${v.small} M${v.medium} L${v.large} XL${v.extraLarge}"
  }

  lazy val itemsPerPage: HtmlAttr[ItemsPerPage] = htmlAttr("items-per-page", ItemsPerPageAsStringCodec)

  @scala.annotation.compileTimeOnly("Items per page are now managed through the unique itemsPerPage key")
  def itemsPerPageL: HtmlAttr[Int] = ???
  @scala.annotation.compileTimeOnly("Items per page are now managed through the unique itemsPerPage key")
  def itemsPerPageM: HtmlAttr[Int] = ???
  @scala.annotation.compileTimeOnly("Items per page are now managed through the unique itemsPerPage key")
  def itemsPerPageS: HtmlAttr[Int] = ???

  lazy val pageIndicatorType: HtmlAttr[CarouselPageIndicatorType] =
    CarouselPageIndicatorType.asHtmlAttr("page-indicator-type")

  @deprecated("pageIndicatorStyle has been renamed to pageIndicatorType", since = "2.0.0")
  def pageIndicatorStyle: HtmlAttr[CarouselPageIndicatorType] = pageIndicatorType

  object slots {}

  object events {
    val onNavigate: EventProp[dom.Event & HasDetail[HasSelectedIndex]] = new EventProp("navigate")
  }

  def getCarouselById(carouselId: String): Option[dom.HTMLElement & RawElement] =
    Option(dom.document.getElementById(carouselId)).map(_.asInstanceOf[dom.HTMLElement & RawElement])

  def getCarousels: List[dom.HTMLElement & RawElement] =
    dom.document.getElementsByTagName("ui5-carousel").toList.map(_.asInstanceOf[dom.HTMLElement & RawElement])

}
