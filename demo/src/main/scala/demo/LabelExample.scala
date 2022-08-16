package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object LabelExample extends Example("Label") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Label")(
      //-- Begin: Basic label
      Label(_ => "Simple Label")
      //-- End
    ),
    DemoPanel("Required Label")(
      //-- Begin: Required Label
      Label(_ => "Required Label", _.required := true)
      //-- End
    ),
    DemoPanel("Required Label With Colon")(
      //-- Begin: Required Label With Colon
      Label(_ => "Required Label", _.required := true, _.showColon := true)
      //-- End
    ),
    DemoPanel("Truncated Label")(
      //-- Begin: Truncated Label
      Label(_ => width := "200px", _ => "Long labels are truncated by default.")
      //-- End
    ),
    DemoPanel("Label 'for'")(
      //-- Begin: Label 'for'
      div(
        className := loginFormClass,
        styleTagForLoginFormClass,
        div(
          Label(_.id := "myLabel", _.forId := "myInput", _.required := true, _.showColon := true, _ => "First name"),
          Input(
            _.id := "myInput",
            _ => aria.required := true,
            _.accessibleNameRef := "myLabel",
            _.placeholder := "Enter your name"
          )
        ),
        div(
          Label(_.id := "myLabel2", _.forId := "myDP", _.required := true, _.showColon := true, _ => "Date of birth"),
          DatePicker(_.id := "myDP", _.accessibleNameRef := "myLabel2", _ => aria.required := true)
        ),
        div(
          Label(_.id := "myLabel3", _.forId := "mySelect", _.required := true, _.showColon := true, _ => "Job"),
          Select(
            _.id := "mySelect",
            _ => aria.required := true,
            _.accessibleNameRef := "myLabel3",
            _.option(_ => "Manager"),
            _.option(_ => "Sales"),
            _.option(_.selected := true, _ => "Developer")
          )
        ),
        div(
          Label(
            _.id := "myLabel4",
            _.forId := "myTextArea",
            _.required := true,
            _.showColon := true,
            _ => "Description label test"
          ),
          TextArea(
            _.id := "myTextArea",
            _.accessibleNameRef := "myLabel4",
            _ => aria.required := true,
            _.placeholder := "Type as much text as you wish."
          )
        ),
        div(
          Label(_.forId := "myRB", _.required := true, _.showColon := true, _ => "Gender"),
          RadioButton(_.id := "myRB", _.name := "a", _.text := "Choice 1"),
          RadioButton(_.id := "myRB2", _.name := "a", _.checked := true, _.text := "Choice 2")
        ),
        div(
          Label(_.forId := "myCB", _.required := true, _.showColon := true, _ => "Accept terms of use"),
          CheckBox(_.id := "myCB")
        )
      )
      //-- End
    )
  )

}
