package demo.helpers

object MTG {

  case class Card(name: String, tpe: String, cost: String, comment: String)

  val cards = List(
    Card("Black Lotus", "Artifact", "0", "Power Nine"),
    Card("Ancestral Recall", "Instant", "U", "Power Nine"),
    Card("Time Walk", "Sorcery", "1U", "Power Nine"),
    Card("Timetwister", "Sorcery", "2U", "Power Nine"),
    Card("Mox Sapphire", "Artifact", "0", "Power Nine"),
    Card("Mox Ruby", "Artifact", "0", "Power Nine"),
    Card("Mox Jet", "Artifact", "0", "Power Nine"),
    Card("Mox Pearl", "Artifact", "0", "Power Nine"),
    Card("Mox Emerald", "Artifact", "0", "Power Nine"),
    Card(
      "Urza's tower",
      "Land",
      "",
      "This land is part of what is called the 'Tron', which contains the Urza's Tower, Urza's Mine and Urza's Power Plant."
    )
  )

  val manaSymbolsShortNames = List("B", "U", "W", "G", "R", "C")
  val manaSymbolsNames = List(
    "Black",
    "Blue",
    "White",
    "Green",
    "Red",
    "Colourless"
  )

  def manaSymbolsRefs(shortName: String) = s"images/mtg/$shortName.png"

  val cardImages = Map(
    "Black Lotus"      -> "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=382866&type=card",
    "Ancestral Recall" -> "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=382841&type=card",
    "Time Walk"        -> "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=383131&type=card",
    "Timetwister"      -> "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=383132&type=card",
    "Mox Sapphire"     -> "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=383023&type=card",
    "Mox Ruby"         -> "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=383022&type=card",
    "Mox Jet"          -> "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=383020&type=card",
    "Mox Pearl"        -> "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=383021&type=card",
    "Mox Emerald"      -> "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=383019&type=card"
  )

}
