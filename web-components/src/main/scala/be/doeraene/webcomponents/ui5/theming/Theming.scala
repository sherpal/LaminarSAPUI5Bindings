package be.doeraene.webcomponents.ui5.theming

import scala.scalajs.js.annotation.JSImport

import scala.scalajs.js

//noinspection ScalaUnusedSymbol
object Theming {

  @js.native
  @JSImport("@ui5/webcomponents-base/dist/config/Theme.js", "setTheme")
  def setTheme(theme: String): js.Promise[Unit] = js.native

  @js.native
  @JSImport("@ui5/webcomponents-base/dist/config/Theme.js", "getTheme")
  def getTheme(): String = js.native

  @js.native
  @JSImport("@ui5/webcomponents-base/dist/asset-registries/Themes.js", "registerThemePropertiesLoader")
  def registerThemePropertiesLoader(
      packageName: String,
      themeName: String,
      loader: js.Function0[js.Promise[js.Object]]
  ): Unit =
    js.native

  @js.native
  @JSImport(
    "@ui5/webcomponents/dist/Assets-fetch.js",
    JSImport.Namespace
  )
  object WebComponentsAssets extends js.Object

  @js.native
  @JSImport(
    "@ui5/webcomponents-fiori/dist/Assets.js",
    JSImport.Namespace
  )
  object WebComponentsFioriAssets extends js.Object

  @js.native
  @JSImport(
    "@ui5/webcomponents-compat/dist/Assets.js",
    JSImport.Namespace
  )
  object WebComponentsCompatAssets extends js.Object

}
