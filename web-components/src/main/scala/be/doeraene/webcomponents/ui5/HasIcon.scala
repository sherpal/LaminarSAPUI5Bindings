package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.IconName
import com.raquo.laminar.keys.ReactiveHtmlAttr
import com.raquo.laminar.api.L.*

trait HasIcon {
  lazy val icon: ReactiveHtmlAttr[IconName] = customHtmlAttr("icon", IconName.AsStringCodec)
}
