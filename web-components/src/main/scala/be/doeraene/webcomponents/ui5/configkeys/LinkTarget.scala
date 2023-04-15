package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.laminar.codecs.Codec

sealed trait LinkTarget {
  def value: String
}

object LinkTarget extends EnumerationString[LinkTarget] {
  case object _self extends LinkTarget {
    override def value: String = "_self"
  }

  case object _blank extends LinkTarget {
    override def value: String = "_blank"
  }

  case object _parent extends LinkTarget {
    override def value: String = "_parent"
  }

  case object _top extends LinkTarget {
    override def value: String = "_top"
  }

  case object _search extends LinkTarget {
    override def value: String = "_search"
  }

  val allValues: List[LinkTarget] = List(_self, _blank, _parent, _top, _search)

  def valueOf(value: LinkTarget): String = value.value

}
