package be.doeraene.webcomponents.ui5

import com.raquo.domtypes.generic.codecs.StringAsIsCodec
import com.raquo.laminar.api.L.*
import com.raquo.laminar.keys.ReactiveHtmlAttr

trait HasText {
  val text: ReactiveHtmlAttr[String] = customHtmlAttr("text", StringAsIsCodec)
}
