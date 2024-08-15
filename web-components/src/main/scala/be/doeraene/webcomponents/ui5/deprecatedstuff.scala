package be.doeraene.webcomponents.ui5

@deprecated("Badge has been renamed to Tag", since = "2.0.0")
def Badge = Tag

@deprecated("Table is deprecated while waiting for the Table v2 implementation. Use compat.Table instead", since = "2.0.0")
def Table = TableCompat

@deprecated("Table is deprecated while waiting for the Table v2 implementation. Use compat.Table instead", since = "2.0.0")
def TableRow = TableRowCompat

@deprecated("Table is deprecated while waiting for the Table v2 implementation. Use compat.Table instead", since = "2.0.0")
def TableColumn = TableColumnCompat

@deprecated("Table is deprecated while waiting for the Table v2 implementation. Use compat.Table instead", since = "2.0.0")
def TableCell = TableCellCompat

@scala.annotation.compileTimeOnly(
  "UListGroupHeader has been replaced by UListGroup, and the structure of your list items now needs to be nested."
)
def UListGroupHeader = ???

object compat {
  def Table = TableCompat
  def TableRow = TableRowCompat
  def TableColumn = TableColumnCompat
  def TableCell = TableCellCompat
}
