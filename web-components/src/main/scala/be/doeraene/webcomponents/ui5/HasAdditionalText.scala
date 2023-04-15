package be.doeraene.webcomponents.ui5

import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.api.L.*
import com.raquo.laminar.keys.HtmlAttr

trait HasAdditionalText {
  def additionalText: HtmlAttr[String] = htmlAttr("additional-text", StringAsIsCodec)
}
