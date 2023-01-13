package be.doeraene.webcomponents.ui5

import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.api.L.*
import com.raquo.laminar.keys.HtmlAttr

trait HasAccessibleName {
  lazy val accessibleName: HtmlAttr[String]    = customHtmlAttr("accessible-name", StringAsIsCodec)
  lazy val accessibleNameRef: HtmlAttr[String] = customHtmlAttr("accessible-name-ref", StringAsIsCodec)
}
