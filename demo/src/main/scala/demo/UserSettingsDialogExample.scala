package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object UserSettingsDialogExample extends Example("UserSettingsDialog") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("User Settings Dialog") {
      //-- Begin: User Settings Dialog
      val dialogOpenVar = Var(false)
      val themeVar      = Var("sap_horizon")
      val lastEventVar  = Var("Open the dialog and play with the settings.")

      def notificationItem(text: String, byline: String, initiallyChecked: Boolean) =
        UserSettingsNotificationsViewItem(
          _.text       := text,
          _.bylineText := byline,
          _.checked    := initiallyChecked,
          _.events.onSwitchChange.map(event =>
            s"Notifications for '$text' turned ${if event.detail.checked then "on" else "off"}"
          ) --> lastEventVar.writer
        )

      div(
        Button("Open User Settings", _.events.onClick.mapTo(true) --> dialogOpenVar.writer),
        Label(child.text <-- lastEventVar.signal),
        UserSettingsDialog(
          _.headerText      := "Settings",
          _.showSearchField := true,
          _.open <-- dialogOpenVar.signal,
          _.events.onClose.mapTo(false) --> dialogOpenVar.writer,
          _.events.onSelectionChange.map(event => s"Selected setting: ${event.detail.item.text}") --> lastEventVar,
          // User account
          _.item(
            _.text       := "User Account",
            _.tooltip    := "User Account",
            _.headerText := "User Account",
            _.icon       := IconName.employee,
            UserSettingsAccountView(
              _.text              := "Account",
              _.showManageAccount := true,
              _.slots.account := UserMenuAccount(
                _.avatarSrc    := "/images/avatars/sherpal.png",
                _.titleText    := "Sherpal",
                _.subtitleText := "sherpal@example.com",
                _.description  := "Software developer"
              ),
              _.events.onManageAccountClick.mapTo("Manage account clicked") --> lastEventVar.writer,
              Button(_.design := ButtonDesign.Transparent, _.icon := IconName.reset, "Reset all personalization")
            )
          ),
          // Appearance
          _.item(
            _.text       := "Appearance",
            _.tooltip    := "Appearance",
            _.headerText := "Appearance",
            _.icon       := IconName.palette,
            UserSettingsAppearanceView(
              _.text := "Themes",
              _.events.onSelectionChange.map(_.detail.item.itemKey) --> themeVar.writer,
              UserSettingsAppearanceViewGroup(
                _.headerText := "SAP Horizon",
                UserSettingsAppearanceViewItem(
                  _.itemKey     := "sap_horizon",
                  _.text        := "SAP Morning Horizon",
                  _.icon        := IconName.palette,
                  _.colorScheme := AvatarColorScheme.Accent7,
                  _.selected <-- themeVar.signal.map(_ == "sap_horizon")
                ),
                UserSettingsAppearanceViewItem(
                  _.itemKey     := "sap_horizon_dark",
                  _.text        := "SAP Evening Horizon",
                  _.icon        := IconName.palette,
                  _.colorScheme := AvatarColorScheme.Accent9,
                  _.selected <-- themeVar.signal.map(_ == "sap_horizon_dark")
                )
              ),
              UserSettingsAppearanceViewGroup(
                _.headerText := "SAP Quartz",
                UserSettingsAppearanceViewItem(
                  _.itemKey     := "sap_fiori_3",
                  _.text        := "SAP Quartz Light",
                  _.icon        := IconName.palette,
                  _.colorScheme := AvatarColorScheme.Accent5,
                  _.selected <-- themeVar.signal.map(_ == "sap_fiori_3")
                )
              ),
              _.slots.additionalContent := Label(child.text <-- themeVar.signal.map(theme => s"Selected theme: $theme"))
            )
          ),
          // Notifications
          _.item(
            _.text       := "Notifications",
            _.tooltip    := "Notifications",
            _.headerText := "Notifications",
            _.icon       := IconName.bell,
            UserSettingsNotificationsView(
              _.text := "Notifications",
              _.slots.headerItems := notificationItem("Allow Notifications", "Master switch for all notifications", true),
              _.slots.additionalContent := MessageStrip(
                _.design          := MessageStripDesign.Information,
                _.hideCloseButton := true,
                "Changes are applied immediately."
              ),
              UserSettingsNotificationsViewGroup(
                _.headerText := "Workflow",
                notificationItem("Approvals", "When a request needs your approval", true),
                notificationItem("Comments", "When someone comments on your items", false)
              ),
              UserSettingsNotificationsViewGroup(
                _.headerText := "Delivery",
                UserSettingsNotificationsViewItem(
                  _.text       := "Email digest",
                  _.bylineText := "How often you receive a summary",
                  _.slots.endContent := Select(
                    Select.option(_.value := "daily", "Daily"),
                    Select.option(_.value := "weekly", "Weekly"),
                    Select.option(_.value := "never", "Never")
                  )
                )
              )
            )
          ),
          // Fixed item at the bottom
          _.slots.fixedItems := UserSettingsItem(
            _.text       := "About",
            _.tooltip    := "About",
            _.headerText := "About",
            _.icon       := IconName.hint,
            UserSettingsView(
              _.text := "About",
              Text("This dialog is built with the Laminar bindings for SAP UI5 web components.")
            )
          )
        )
      )
      //-- End
    },
    DemoPanel("User Settings Dialog with Save Mode") {
      //-- Begin: User Settings Dialog with Save Mode
      val dialogOpenVar   = Var(false)
      val savedLanguage   = Var("English")
      val draftLanguage   = Var("English")
      val languageChoices = List("English", "French", "German", "Dutch")

      div(
        Button(
          "Open User Settings (save mode)",
          _.events.onClick --> Observer[Any] { _ =>
            draftLanguage.set(savedLanguage.now())
            dialogOpenVar.set(true)
          }
        ),
        Label(child.text <-- savedLanguage.signal.map(language => s"Saved language: $language")),
        UserSettingsDialog(
          _.headerText := "Settings",
          _.saveMode   := true,
          _.open <-- dialogOpenVar.signal,
          _.events.onClose.mapTo(false) --> dialogOpenVar.writer,
          _.events.onSave --> Observer[Any] { _ =>
            savedLanguage.set(draftLanguage.now())
            dialogOpenVar.set(false)
          },
          _.events.onCancel.mapTo(false) --> dialogOpenVar.writer,
          _.item(
            _.text       := "Language & Region",
            _.tooltip    := "Language & Region",
            _.headerText := "Language & Region",
            _.icon       := IconName.globe,
            UserSettingsView(
              _.text := "Language",
              Label("Language"),
              Select(
                _.value <-- draftLanguage.signal,
                languageChoices.map(language => Select.option(_.value := language, language)),
                _.events.onChange.map(_.detail.selectedOption.maybeValue.getOrElse("English")) --> draftLanguage.writer
              )
            )
          )
        )
      )
      //-- End
    }
  )

}
