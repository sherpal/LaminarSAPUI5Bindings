package be.doeraene.webcomponents.ui5.scaladsl.colour

import org.scalajs.dom

import scala.collection.mutable

/** The purpose of a [[FromStringColourCache]] is to circumvent the fact that actually pulling a [[Colour]] from a
  * [[String]] is a quite expensive operation. Indeed, one has to create an actual canvas, paint to it, and retrieve the
  * colour data back from it.
  *
  * In order to seemlessly use the fromString method in the companion object of [[Colour]], there is a default cache
  * implementation (given -- pun intended -- below) that simply caches the last 100 different seen values.
  *
  * If you want, you can provide a custom implementation and use that one.
  *
  * Note that the mutability aspect, as implemented here, is not a liability since we run this thing on JS, where only
  * one thread exists.
  */
trait FromStringColourCache {
  def fromString(colourString: String): Colour
}

object FromStringColourCache {

  val noCache: FromStringColourCache = new FromStringColourCache {
    val canvas = new dom.OffscreenCanvas(1, 1)
    val ctx    = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

    def fromString(colourString: String): Colour = {
      ctx.clearRect(0, 0, 1, 1) // reset the single pixel to transparent black
      ctx.fillStyle = colourString
      ctx.fillRect(0, 0, 1, 1)
      val data  = ctx.getImageData(0, 0, 1, 1).data
      val red   = data(0)
      val green = data(1)
      val blue  = data(2)
      val alpha = data(3) / 255.0

      Colour(red, green, blue, alpha)
    }
  }

  def lastNCache(cacheSize: Int): FromStringColourCache = new FromStringColourCache {
    var oldestCacheValue: String                  = "white"
    var cachedValues: mutable.Map[String, Colour] = mutable.Map("white" -> Colour.white)
    var numberOfCachedValues: Int                 = 0

    def fromString(colourString: String): Colour =
      cachedValues.get(colourString) match {
        case Some(colour) => colour
        case None =>
          val colour = noCache.fromString(colourString)
          if numberOfCachedValues == cacheSize then
            cachedValues -= oldestCacheValue
            oldestCacheValue = colourString
          else numberOfCachedValues += 1

          cachedValues += (colourString -> colour)

          colour
      }
  }

  given FromStringColourCache = lastNCache(100)

}
