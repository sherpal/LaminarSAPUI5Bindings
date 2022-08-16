package be.doeraene.webcomponents.ui5

import com.raquo.domtypes.generic.codecs.StringAsIsCodec
import com.raquo.laminar.api.L.*
import com.raquo.laminar.keys.ReactiveHtmlAttr

trait HasAccessibleName {
  lazy val accessibleName: ReactiveHtmlAttr[String]    = customHtmlAttr("accessible-name", StringAsIsCodec)
  lazy val accessibleNameRef: ReactiveHtmlAttr[String] = customHtmlAttr("accessible-name-ref", StringAsIsCodec)
}
