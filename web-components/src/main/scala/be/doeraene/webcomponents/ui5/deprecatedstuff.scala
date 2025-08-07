package be.doeraene.webcomponents.ui5

@deprecated("Badge has been renamed to Tag", since = "2.0.0")
def Badge = Tag

@scala.annotation.compileTimeOnly(
  "UListGroupHeader has been replaced by UListGroup, and the structure of your list items now needs to be nested."
)
def UListGroupHeader = ???

object compat {
  @deprecated("Compat Tables are now fully deprecated, urgently switch to the new Table", since = "2.12.0")
  def Table = TableCompat
  @deprecated("Compat Tables are now fully deprecated, urgently switch to the new Table", since = "2.12.0")
  def TableRow = TableRowCompat
  @deprecated("Compat Tables are now fully deprecated, urgently switch to the new Table", since = "2.12.0")
  def TableColumn = TableColumnCompat
  @deprecated("Compat Tables are now fully deprecated, urgently switch to the new Table", since = "2.12.0")
  def TableCell = TableCellCompat
}
