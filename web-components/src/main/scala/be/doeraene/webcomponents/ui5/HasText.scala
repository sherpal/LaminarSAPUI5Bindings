package be.doeraene.webcomponents.ui5

import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.api.L.*
import com.raquo.laminar.keys.HtmlAttr

trait HasText {
  lazy val text: HtmlAttr[String] = htmlAttr("text", StringAsIsCodec)
}
