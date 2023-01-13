package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.IconName
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.api.L.*

trait HasIcon {
  lazy val icon: HtmlAttr[IconName] = customHtmlAttr("icon", IconName.AsStringCodec)

  lazy val iconString: HtmlAttr[String] = customHtmlAttr("icon", StringAsIsCodec)
}
