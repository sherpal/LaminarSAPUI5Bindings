package be.doeraene.webcomponents.ui5.datatypes

import scala.scalajs.js

/** Validation of CSS sizes, as UI5 used to do it.
  *
  * This used to be a facade for `@ui5/webcomponents-base/dist/types/CSSSize.js`, but that module was removed in UI5 2.0
  * (importing it breaks the bundling). The regular expression below is the one from that module.
  */
object JSCSSSize {

  private val regex = new js.RegExp(
    """^(auto|inherit|[-+]?(0*|([0-9]+|[0-9]*\.[0-9]+)([rR][eE][mM]|[eE][mM]|[eE][xX]|[pP][xX]|[cC][mM]|[mM][mM]|[iI][nN]|[pP][tT]|[pP][cC]|%))|calc\(\s*(\(\s*)*[-+]?(([0-9]+|[0-9]*\.[0-9]+)([rR][eE][mM]|[eE][mM]|[eE][xX]|[pP][xX]|[cC][mM]|[mM][mM]|[iI][nN]|[pP][tT]|[pP][cC]|%)?)(\s*(\)\s*)*(\s[-+]\s|[*\/])\s*(\(\s*)*([-+]?(([0-9]+|[0-9]*\.[0-9]+)([rR][eE][mM]|[eE][mM]|[eE][xX]|[pP][xX]|[cC][mM]|[mM][mM]|[iI][nN]|[pP][tT]|[pP][cC]|%)?)))*\s*(\)\s*)*\))$"""
  )

  def isValid(value: String): Boolean = regex.test(value)
}
