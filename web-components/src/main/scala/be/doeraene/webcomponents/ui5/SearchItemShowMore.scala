package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.eventtypes.ListItemClickDetail
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.IntAsStringCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** A ui5-search-item-show-more is a special list item, used in a [[Search]], that acts as a button to progressively
  * reveal additional (overflow) items within a group.
  *
  * Note: this component is experimental in UI5 and its API may change.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/fiori/SearchItemShowMore/">the doc</a> for more
  *   information.
  */
object SearchItemShowMore extends WebComponent {

  @js.native
  trait RawElement extends js.Object {
    def selected: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents-fiori/dist/SearchItemShowMore.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-search-item-show-more")

  /** Number of additional items available to show. If set, the item displays "Show more (N)", otherwise "Show more".
    */
  lazy val itemsToShowCount: HtmlAttr[Int] = htmlAttr("items-to-show-count", IntAsStringCodec)

  lazy val selected: HtmlAttr[Boolean] = htmlAttr("selected", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {

    @js.native
    trait ClickDetail extends ListItemClickDetail {

      /** Whether the item was activated with the keyboard. */
      def fromKeyboard: Boolean = js.native
    }

    /** Fired when the item is activated, either with a mouse/tap or by pressing the Enter or Space keys. */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ClickDetail]] = new EventProp("click")
  }

}
