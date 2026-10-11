package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object UserMenuExample extends Example("UserMenu") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("User Menu in a ShellBar") {
      //-- Begin: User Menu in a ShellBar
      val profileId    = "user-menu-shellbar-profile"
      val menuOpenVar  = Var(false)
      val lastEventVar = Var("Click on the avatar to open the user menu.")

      div(
        ShellBar(
          _.primaryTitle  := "Corporate Portal",
          _.slots.logo    := img(src := "/images/avatars/scala-logo.png"),
          _.slots.profile := Avatar(idAttr := profileId, img(src := "/images/avatars/sherpal.png")),
          _.events.onProfileClick.mapTo(true) --> menuOpenVar.writer
        ),
        UserMenu(
          _.openerId := profileId,
          _.open <-- menuOpenVar.signal,
          _.showManageAccount := true,
          _.showOtherAccounts := true,
          _.showEditAccounts  := true,
          _.showEditButton    := true,
          _.slots.accounts := UserMenu.account(
            _.avatarSrc      := "/images/avatars/sherpal.png",
            _.titleText      := "Sherpal",
            _.subtitleText   := "sherpal@example.com",
            _.description    := "Software developer",
            _.additionalInfo := "Brussels office",
            _.selected       := true
          ),
          _.slots.accounts := UserMenu.account(
            _.avatarInitials    := "JD",
            _.avatarColorScheme := AvatarColorScheme.Accent3,
            _.titleText         := "John Doe",
            _.subtitleText      := "john.doe@example.com",
            _.description       := "Second account"
          ),
          _.item(_.icon := IconName.`action-settings`, _.text := "Settings"),
          _.item(
            _.icon := IconName.`sys-help`,
            _.text := "Help",
            _.item(_.icon := IconName.document, _.text   := "Documentation"),
            _.item(_.icon := IconName.discussion, _.text := "Contact support")
          ),
          _.item(_.icon := IconName.`official-service`, _.text := "Legal Information"),
          _.events.onItemClick.map(event => s"Item clicked: ${event.detail.item.text.getOrElse("")}") --> lastEventVar.writer,
          _.events.onChangeAccount.map(event =>
            s"Account changed from ${event.detail.prevSelectedAccount.titleText} to ${event.detail.selectedAccount.titleText}"
          ) --> lastEventVar.writer,
          _.events.onManageAccountClick.mapTo("Manage account clicked") --> lastEventVar.writer,
          _.events.onEditAccountsClick.mapTo("Edit accounts clicked") --> lastEventVar.writer,
          _.events.onSignOutClick.mapTo("Sign out clicked") --> lastEventVar.writer,
          _.events.onClose.mapTo(false) --> menuOpenVar.writer
        ),
        Label(child.text <-- lastEventVar.signal)
      )
      //-- End
    },
    DemoPanel("User Menu with Groups and Custom Footer") {
      //-- Begin: User Menu with Groups and Custom Footer
      val openerId    = "user-menu-button-opener"
      val menuOpenVar = Var(false)
      val densityVar  = Var("Cozy")

      div(
        Button(idAttr := openerId, "Open User Menu", _.events.onClick.mapTo(true) --> menuOpenVar.writer),
        Label(child.text <-- densityVar.signal.map(density => s"Selected density: $density")),
        UserMenu(
          _.openerId := openerId,
          _.open <-- menuOpenVar.signal,
          _.slots.accounts := UserMenu.account(
            _.avatarInitials    := "AD",
            _.avatarColorScheme := AvatarColorScheme.Accent6,
            _.titleText         := "Alex Doe",
            _.subtitleText      := "alex.doe@example.com"
          ),
          _.slots.infoArea := MessageStrip(
            _.design          := MessageStripDesign.Information,
            _.hideCloseButton := true,
            "Trial account"
          ),
          _.group(
            _.checkMode := MenuItemGroupCheckMode.Single,
            List("Cozy", "Compact").map(density =>
              UserMenu.item(
                _.text := density,
                _.checked <-- densityVar.signal.map(_ == density),
                _.events.onCheck.mapTo(density) --> densityVar.writer
              )
            )
          ),
          _.item(_.icon := IconName.`action-settings`, _.text := "Settings"),
          _.slots.footer := Bar(
            _.design          := BarDesign.Footer,
            _.slots.endContent := Button(
              _.design := ButtonDesign.Transparent,
              "Close",
              _.events.onClick.mapTo(false) --> menuOpenVar.writer
            )
          ),
          _.events.onClose.mapTo(false) --> menuOpenVar.writer
        )
      )
      //-- End
    }
  )

}
