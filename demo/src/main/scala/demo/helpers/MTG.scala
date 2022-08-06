package demo.helpers

object MTG {

  val manaSymbolsShortNames = List("B", "U", "W", "G", "R", "C")
  val manaSymbolsNames = List(
    "Black",
    "Blue",
    "White",
    "Green",
    "Red",
    "Colourless"
  )

  def manaSymbolsRefs(shortName: String) = s"/images/mtg/$shortName.png"

}
