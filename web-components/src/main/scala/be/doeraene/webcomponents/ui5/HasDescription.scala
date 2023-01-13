package be.doeraene.webcomponents.ui5

import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.api.L.*
import com.raquo.laminar.keys.HtmlAttr

trait HasDescription {
  lazy val description: HtmlAttr[String] = customHtmlAttr("description", StringAsIsCodec)
}
