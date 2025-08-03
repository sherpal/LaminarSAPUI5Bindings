package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.LinkTarget
import com.raquo.laminar.api.L.*
import demo.TagExample
import demo.helpers.Example
import demo.helpers.ThemeSelector
import org.scalajs.dom
import org.scalajs.dom.URL
import org.scalajs.dom.window
object EntryPoint {
  def main(args: Array[String]): Unit = {
    val demoElement = {
      val componentsDemo: List[Example] = List(
        AvatarExample,
        AvatarGroupExample,
        TagExample,
        BarExample,
        BarcodeScannerDialogExample,
        BreadcrumbsExample,
        BusyIndicatorExample,
        ButtonExample,
        CalendarExample,
        CalendarLegendExample,
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
        DynamicPageExample,
        DynamicSideContentExample,
        FileUploaderExample,
        FlexibleColumnLayoutExample,
        IconExample,
        IllustratedMessageExample,
        InputExample,
        LabelExample,
        LinkExample,
        ListExample,
        MakeError,
        MediaGalleryExample,
        MenuExample,
        MessageStripExample,
        MultiComboBoxExample,
        MultiInputExample,
        NavigationLayoutExample,
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
        TextExample,
        TextAreaExample,
        TimePickerExample,
        TimelineExample,
        TitleExample,
        ToastExample,
        ToggleButtonExample,
        ToolbarExample,
        TreeExample,
        UploadCollectionExample,
        ViewSettingsDialogExample,
        WizardExample
      ).sorted

      val componentListVar = Var(componentsDemo)

      val componentNameVar = Var(componentNameFromSearchParam)
      window.onpopstate = _ => componentNameVar.set(componentNameFromSearchParam)

      def updateHistory(componentName: String): Unit = {
        val url = new URL(window.location.toString)
        url.searchParams.set("componentName", componentName)
        window.history.pushState(null, "", url.toString)
      }

      val noComponentSelectedElement =
        div(
          padding := "1em",
          h1("Demo of SAP UI5 bindings for Laminar"),
          p(
            "This is the demo page for the ",
            Link(_.target := LinkTarget._blank, _.href := "https://sap.github.io/ui5-webcomponents/", "SAP UI5"),
            " bindings for ",
            Link(_.target := LinkTarget._blank, _.href := "https://laminar.dev/", "Laminar"),
            "."
          ),
          p(
            "The library repo is available ",
            Link(
              _.target := LinkTarget._blank,
              _.href   := "https://github.com/sherpal/LaminarSAPUI5Bindings",
              "here"
            ),
            "."
          ),
          h2("Choose one of the components.")
        )

      NavigationLayout(
        _.slots.sideContent := div(
          paddingRight("2rem"),
          Title(
            div(
              display       := "flex",
              flexDirection := "column",
              flexWrap      := "wrap",
              span(
                "Components",
                cursor := "pointer",
                onClick --> (_ => {
                  updateHistory("")
                  componentNameVar.set(None)
                })
              ),
              Input(
                _.placeholder   := "Search",
                _.showClearIcon := true,
                onInput.mapToValue.compose(_.throttle(1000)) --> { v =>
                  componentListVar.set(componentsDemo.filter(_.name.toLowerCase.contains(v)))
                }
              ),
              ThemeSelector()
            ),
            padding("0.5rem")
          ),
          SideNavigation(
            _.events.onSelectionChange
              .map(_.detail.item.dataset.get("componentName")) --> Observer[Option[String]] {
              case v @ Some(componentName) =>
                updateHistory(componentName)
                componentNameVar.set(v)
              case None => throw new IllegalArgumentException(s"This item did not have data 'componentName'.")
            },
            children <-- componentListVar.signal
              .split(_.name)((_, example, _) =>
                SideNavigation.item(
                  _.text                     := example.name,
                  width                      := "200px",
                  dataAttr("component-name") := example.name,
                  _.selected                <-- componentNameVar.signal.map(_.exists(_ == example.name))
                )
              ),
            height    := "90vh",
            overflowY := "auto"
          )
        ),
        div(
          height    := "100vh",
          overflowY := "auto",
          display   := "flex",
          flexGrow  := 1,
          div(
            padding  := "10px",
            minWidth := "40%",
            width    := "100%",
            child <-- componentNameVar.signal
              .map(
                _.flatMap(cn => componentsDemo.find(_.name == cn).map(_.completeComponent))
                  .getOrElse(noComponentSelectedElement)
              )
          )
        )
      )
    }

    render(dom.document.getElementById("root"), demoElement)
  }

  private def componentNameFromSearchParam =
    Option(new URL(dom.document.location.href).searchParams.get("componentName"))
}
