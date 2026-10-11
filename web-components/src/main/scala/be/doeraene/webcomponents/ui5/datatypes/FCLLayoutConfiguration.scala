package be.doeraene.webcomponents.ui5.datatypes

import be.doeraene.webcomponents.ui5.configkeys.{FCLLayout, FCLMedia}
import com.raquo.laminar.api.L
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.Codec

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*

/** Custom column proportions of a [[be.doeraene.webcomponents.ui5.FlexibleColumnLayout]], per screen size and per
  * layout (the `layoutsConfiguration` property, since UI5 2.16.0).
  *
  * Each entry maps an [[FCLLayout]] to its column widths (start, mid, end), as CSS widths. For example
  * `List("30%", "40%", "30%")` or `List("70%", "30%", "0px")`.
  *
  *   - The proportions should be given in percentages and should add up to 100%.
  *   - Hidden columns are marked as "0px" (or "0%").
  *   - If the proportions do not match the layout (e.g. 2 visible columns for the `OneColumn` layout), the default
  *     proportions are used instead.
  *   - There is no custom configuration for phones, where a column always takes 100% of the width.
  *
  * When the user resizes the columns by dragging a separator, the component fires the `layout-configuration-change`
  * event. You can use [[updated]] to integrate the new column layout into your own configuration.
  *
  * Example:
  * {{{
  *   FlexibleColumnLayout(
  *     _.layoutsConfiguration := FCLLayoutConfiguration(
  *       desktop = Map(FCLLayout.TwoColumnsMidExpanded -> List("30%", "70%", "0px"))
  *     )
  *   )
  * }}}
  *
  * @param tablet
  *   custom column layouts on tablets
  * @param desktop
  *   custom column layouts on desktops
  */
final case class FCLLayoutConfiguration(
    tablet: Map[FCLLayout, List[String]] = Map.empty,
    desktop: Map[FCLLayout, List[String]] = Map.empty
) {

  /** Sets the column widths of the given layout for the given media. The [[FCLMedia.Phone]] media is ignored, since it
    * does not support custom configurations.
    */
  def updated(media: FCLMedia, layout: FCLLayout, columnLayout: List[String]): FCLLayoutConfiguration = media match {
    case FCLMedia.Phone   => this
    case FCLMedia.Tablet  => copy(tablet = tablet + (layout -> columnLayout))
    case FCLMedia.Desktop => copy(desktop = desktop + (layout -> columnLayout))
  }

  /** Sets the column widths of the given layout on tablets. */
  def withTabletLayout(layout: FCLLayout, columnLayout: List[String]): FCLLayoutConfiguration =
    updated(FCLMedia.Tablet, layout, columnLayout)

  /** Sets the column widths of the given layout on desktops. */
  def withDesktopLayout(layout: FCLLayout, columnLayout: List[String]): FCLLayoutConfiguration =
    updated(FCLMedia.Desktop, layout, columnLayout)

  /** Converts this configuration to the JS object expected by UI5. */
  def toJS: js.Object = {
    def mediaToJS(layouts: Map[FCLLayout, List[String]]): js.Dictionary[js.Object] =
      js.Dictionary(layouts.toSeq.map { case (layout, columnLayout) =>
        layout.value -> js.Dynamic.literal(layout = columnLayout.toJSArray)
      }*)

    val result = js.Dictionary.empty[js.Any]
    if tablet.nonEmpty then result(FCLMedia.Tablet.value) = mediaToJS(tablet)
    if desktop.nonEmpty then result(FCLMedia.Desktop.value) = mediaToJS(desktop)
    result.asInstanceOf[js.Object]
  }

}

object FCLLayoutConfiguration {

  /** No custom configuration: the default column proportions are used everywhere. */
  val empty: FCLLayoutConfiguration = FCLLayoutConfiguration()

  /** Converts a column width coming from UI5 (a CSS width string, or the number 0) to a CSS width string. */
  def columnWidthFromJS(width: String | Int | Double): String = (width: Any) match {
    case s: String => s
    case n         => s"${n}px"
  }

  /** Parses the JS `layoutsConfiguration` object of a FlexibleColumnLayout. Unknown entries are ignored. */
  def fromJS(obj: js.Object): FCLLayoutConfiguration = {
    def mediaFromJS(media: FCLMedia): Map[FCLLayout, List[String]] =
      obj
        .asInstanceOf[js.Dictionary[js.UndefOr[js.Dictionary[js.UndefOr[js.Dynamic]]]]]
        .get(media.value)
        .flatMap(_.toOption)
        .fold(Map.empty[FCLLayout, List[String]]) { layouts =>
          layouts.toMap.flatMap { case (layoutName, maybeConfig) =>
            for {
              layout       <- FCLLayout.fromString(layoutName)
              config       <- maybeConfig.toOption
              columnLayout <- config.layout.asInstanceOf[js.UndefOr[js.Array[String | Int]]].toOption
            } yield layout -> columnLayout.toList.map(columnWidthFromJS)
          }
        }

    if obj == null || js.isUndefined(obj) then empty
    else FCLLayoutConfiguration(tablet = mediaFromJS(FCLMedia.Tablet), desktop = mediaFromJS(FCLMedia.Desktop))
  }

  object AsJSCodec extends Codec[FCLLayoutConfiguration, js.Object] {
    def decode(domValue: js.Object): FCLLayoutConfiguration   = fromJS(domValue)
    def encode(scalaValue: FCLLayoutConfiguration): js.Object = scalaValue.toJS
  }

  /** `layoutsConfiguration` is a property (not an attribute), taking a JS object. */
  def asHtmlProp: HtmlProp[FCLLayoutConfiguration, js.Object] = L.htmlProp("layoutsConfiguration", AsJSCodec)

}
