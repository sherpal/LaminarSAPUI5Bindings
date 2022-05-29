package be.doeraene.webcomponents.ui5

import com.raquo.domtypes.generic.codecs.StringAsIsCodec
import com.raquo.laminar.api.L.*
import com.raquo.laminar.keys.ReactiveHtmlAttr

trait HasAccessibleName {
  val accessibleName: ReactiveHtmlAttr[String]    = customHtmlAttr("accessible-name", StringAsIsCodec)
  val accessibleNameRef: ReactiveHtmlAttr[String] = customHtmlAttr("accessible-name-ref", StringAsIsCodec)
}
