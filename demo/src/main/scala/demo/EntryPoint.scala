package demo

import be.doeraene.webcomponents.ui5.*
import com.raquo.laminar.api.L.*
import org.scalajs.dom
import org.scalajs.dom.URL
import demo.helpers.Example

object EntryPoint {
  def main(args: Array[String]): Unit = {
    ResponsivePopover
    val componentsDemo: List[Example] = List(
      AvatarExample,
      AvatarGroupExample,
      BadgeExample,
      BarExample,
      BarcodeScannerDialogExample,
      BreadcrumbsExample,
      BusyIndicatorExample,
      ButtonExample,
      CalendarExample,
      CardExample,
      CarouselExample,
      CheckBoxExample,
      ColourPaletteExample,
      ColourPalettePopoverExample,
      ColourPickerExample,
      ComboBoxExample,
      DatePickerExample,
      DateRangePickerExample,
      DateTimePickerExample,
      DialogExample,
      DynamicSideContentExample,
      FileUploaderExample,
      FlexibleColumnLayoutExample,
      IconExample,
      IllustratedMessageExample,
      InputExample,
      LabelExample,
      LinkExample,
      ListExample,
      MediaGalleryExample,
      MenuExample,
      MessageStripExample,
      MultiComboBoxExample,
      MultiInputExample,
      NotificationListGroupItemExample,
      NotificationListItemExample,
      PageExample,
      PanelExample,
      PopoverExample,
      ProductSwitchExample,
      ProgressIndicatorExample,
      RadioButtonExample,
      RangeSliderExample,
      RatingIndicatorExample,
      ResponsivePopoverExample,
      SegmentedButtonExample,
      SelectExample,
      ShellBarExample,
      SideNavigationExample,
      SliderExample,
      SplitButtonExample,
      StepInputExample,
      SwitchExample,
      TabContainerExample,
      TableExample,
      TextAreaExample,
      TimePickerExample,
      TimelineExample,
      TitleExample,
      ToastExample,
      ToggleButtonExample,
      TreeExample,
      UploadCollectionExample,
      ViewSettingsDialogExample,
      WizardExample
    ).sorted

    val componentName = new URL(dom.document.location.href).pathname.dropWhile(_ == '/')

    render(
      dom.document.getElementById("root"),
      if componentName == "" then
        div(
          h1("Please chose one of the following component below:"),
          ul(
            componentsDemo.map(_.name).map(componentName => li(Link(componentName, _.href := s"/$componentName")))
          )
        )
      else
        div(
          display := "flex",
          div(
            width := "300px",
            SideNavigation(
              _.events.onSelectionChange.map(_.detail.item.dataset.get("componentName")) --> Observer[Option[String]] {
                case Some(componentName) => dom.document.location.href = s"/$componentName"
                case None => throw new IllegalArgumentException(s"This item did not have data 'componentName'.")
              },
              componentsDemo.map(example =>
                SideNavigation.item(
                  _.text := example.name,
                  width := "200px",
                  dataAttr("component-name") := example.name,
                  _.selected := (example.name == componentName)
                )
              ),
              height := "100vh",
              overflowY := "auto"
            )
          ),
          div(
            padding := "10px",
            maxWidth := "calc(100% - 320px)",
            componentsDemo.find(_.name == componentName).map(_.completeComponent).getOrElse(div("Not Found"))
          )
        )
    )
  }
}
