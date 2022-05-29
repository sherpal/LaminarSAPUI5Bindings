package be.doeraene.webcomponents.ui5.configkeys

sealed trait ToastPlacement {
  def value: String = toString
}

object ToastPlacement extends EnumerationString[ToastPlacement] {

  case object TopStart extends ToastPlacement
  case object TopCenter extends ToastPlacement
  case object TopEnd extends ToastPlacement
  case object MiddleStart extends ToastPlacement
  case object MiddleCenter extends ToastPlacement
  case object MiddleEnd extends ToastPlacement
  case object BottomStart extends ToastPlacement
  case object BottomCenter extends ToastPlacement
  case object BottomEnd extends ToastPlacement

  def valueOf(value: ToastPlacement): String = value.value

  val allValues: List[ToastPlacement] = List(
    TopStart,
    TopCenter,
    TopEnd,
    MiddleStart,
    MiddleCenter,
    MiddleEnd,
    BottomStart,
    BottomCenter,
    BottomEnd
  )

}
