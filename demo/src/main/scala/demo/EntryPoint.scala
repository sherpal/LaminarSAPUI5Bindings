package demo

import be.doeraene.webcomponents.ui5.*
import com.raquo.laminar.api.L.*
import org.scalajs.dom
import org.scalajs.dom.URL
import demo.helpers.Example
import be.doeraene.webcomponents.ui5.configkeys.LinkTarget

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

    val componentName = Option(new URL(dom.document.location.href).searchParams.get("componentName")).getOrElse("")


    render(
      dom.document.getElementById("root"),
      if componentName == "" then
        div(
          padding := "1em",
          h1("Demo of SAP UI5 bindings for Laminar"),
          p("This is the demo page for the ", 
            Link(_.target := LinkTarget._blank, _.href := "https://sap.github.io/ui5-webcomponents/", "SAP UI5"), " bindings for ", 
            Link(_.target := LinkTarget._blank, _.href := "https://laminar.dev/", "Laminar"), "."
          ),
          p("The library repo is available ", Link(_.target := LinkTarget._blank, _.href := "https://github.com/sherpal/LaminarSAPUI5Bindings", "here"), "."),
          h2("Choose one of the components below:"),
          ul(
            componentsDemo.map(_.name).map(componentName => li(Link(componentName, _.href := dom.document.location.pathname ++ s"?componentName=$componentName")))
          )
        )
      else
        div(
          display := "flex",
          div(
            width := "300px",
            SideNavigation(
              _.events.onSelectionChange.map(_.detail.item.dataset.get("componentName")) --> Observer[Option[String]] {
                case Some(componentName) => dom.document.location.href = dom.document.location.pathname ++ s"?componentName=$componentName"
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
