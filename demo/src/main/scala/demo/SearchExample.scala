package demo

import be.doeraene.webcomponents.ui5.*
import be.doeraene.webcomponents.ui5.configkeys.*
import com.raquo.laminar.api.L.*
import demo.helpers.{DemoPanel, Example, FetchDemoPanelFromGithub}

object SearchExample extends Example("Search") {

  def component(using
      demoPanelInfoMap: FetchDemoPanelFromGithub.CompleteDemoPanelInfo
  ): HtmlElement = div(
    DemoPanel("Basic Search") {
      //-- Begin: Basic Search
      val allSuggestions = List("Apple", "Apricot", "Banana", "Blueberry", "Cherry", "Grape", "Orange", "Pineapple")
      val valueVar       = Var("")
      val lastSearchVar  = Var("No search yet.")

      div(
        Search(
          _.placeholder   := "Search for a fruit",
          _.showClearIcon := true,
          _.value <-- valueVar.signal,
          _.events.onInput.map(_.target.value) --> valueVar.writer,
          _.events.onSearch.map { event =>
            val fromItem = event.detail.item.toOption.flatMap(_.text.toOption)
            s"Searched for '${fromItem.getOrElse(event.target.value)}'"
          } --> lastSearchVar.writer,
          children <-- valueVar.signal.map { value =>
            allSuggestions
              .filter(_.toLowerCase.contains(value.toLowerCase))
              .map(fruit => Search.item(_.text := fruit, _.icon := IconName.history))
          }
        ),
        Label(child.text <-- lastSearchVar.signal)
      )
      //-- End
    },
    DemoPanel("Search with Scopes and Groups") {
      //-- Begin: Search with Scopes and Groups
      case class Entry(text: String, description: String, scope: String)
      val entries = List(
        Entry("Order 4711", "Created yesterday", "orders"),
        Entry("Order 4712", "Waiting for approval", "orders"),
        Entry("Order 4713", "Shipped", "orders"),
        Entry("Order 4714", "Delivered", "orders"),
        Entry("Order 4715", "Cancelled", "orders"),
        Entry("John Doe", "Customer since 2019", "customers"),
        Entry("Jane Doe", "Customer since 2021", "customers")
      )
      val scopeVar     = Var("all")
      val shownCount   = Var(2)
      val recentSearch = Var(List("orders shipped last week", "unpaid invoices"))

      Search(
        _.placeholder   := "Search orders and customers",
        _.showClearIcon := true,
        _.scopeValue <-- scopeVar.signal,
        _.slots.scopes := Search.scope(_.text := "All", _.value       := "all"),
        _.slots.scopes := Search.scope(_.text := "Orders", _.value    := "orders"),
        _.slots.scopes := Search.scope(_.text := "Customers", _.value := "customers"),
        _.events.onScopeChange.map(_.detail.scope.toOption.flatMap(_.value.toOption).getOrElse("all")) --> scopeVar,
        _.slots.messageArea := Search.messageArea(
          _.text        := "Tip",
          _.description := "Pick a scope on the left to narrow down the suggestions."
        ),
        Search.group(
          _.headerText := "Recent searches",
          children <-- recentSearch.signal.map(_.map { recent =>
            Search.item(
              _.text      := recent,
              _.icon      := IconName.history,
              _.deletable := true,
              _.events.onDelete.mapTo(recent) --> recentSearch.updater[String]((list, toRemove) =>
                list.filterNot(_ == toRemove)
              )
            )
          })
        ),
        children <-- scopeVar.signal.combineWith(shownCount.signal).map { (scope, count) =>
          val inScope = entries.filter(entry => scope == "all" || entry.scope == scope)
          inScope.groupBy(_.scope).toList.sortBy(_._1).map { (groupScope, groupEntries) =>
            val shown = if groupScope == "orders" then groupEntries.take(count) else groupEntries
            Search.group(
              _.headerText := groupScope.capitalize,
              shown.map(entry =>
                Search.item(
                  _.text        := entry.text,
                  _.description := entry.description,
                  _.scopeName   := groupScope.capitalize,
                  _.icon        := (if groupScope == "orders" then IconName.cart else IconName.customer),
                  _.slots.actions := Button(
                    _.icon    := IconName.`action`,
                    _.design  := ButtonDesign.Transparent,
                    _.tooltip := "Open"
                  )
                )
              ),
              Option.when(shown.length < groupEntries.length)(
                Search.showMoreItem(
                  _.itemsToShowCount := groupEntries.length - shown.length,
                  _.events.onClick.mapTo(groupEntries.length) --> shownCount.writer
                )
              )
            )
          }
        }
      )
      //-- End
    },
    DemoPanel("Search with Loading State") {
      //-- Begin: Search with Loading State
      val fieldLoadingVar = Var(false)

      div(
        Switch(
          _.textOn  := "Loading",
          _.textOff := "Idle",
          _.events.onCheckedChange --> fieldLoadingVar.writer
        ),
        Search(
          _.placeholder := "Type something",
          _.fieldLoading <-- fieldLoadingVar.signal,
          _.slots.illustration := IllustratedMessage(
            _.name         := IllustratedMessageType.NoSearchResults,
            _.titleText    := "No results",
            _.subtitleText := "There are no suggestions here."
          )
        )
      )
      //-- End
    }
  )

}
