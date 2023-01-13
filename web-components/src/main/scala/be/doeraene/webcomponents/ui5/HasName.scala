package be.doeraene.webcomponents.ui5

import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.api.L.*
import com.raquo.laminar.keys.HtmlAttr

trait HasName {
  lazy val name: HtmlAttr[String] = htmlAttr("name", StringAsIsCodec)
}
