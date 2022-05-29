package be.doeraene.webcomponents.ui5.configkeys

import scala.scalajs.js

sealed trait IllustrationMessageType {
  final def value: String = toString

  /** Returns the import necessary to register this illustration. */
  final def importStatement: String = {
    val endOfPath = if value.startsWith("Tnt") then s"/tnt/${value.drop(3)}" else s"/${value}"
    s"@ui5/webcomponents-fiori/dist/illustrations$endOfPath.js"
  }
}

object IllustrationMessageType extends EnumerationString[IllustrationMessageType] {
  case object BeforeSearch extends IllustrationMessageType
  case object NoActivities extends IllustrationMessageType
  case object NoData extends IllustrationMessageType
  case object NoMail extends IllustrationMessageType
  case object NoMail_v1 extends IllustrationMessageType
  case object NoEntries extends IllustrationMessageType
  case object NoNotifications extends IllustrationMessageType
  case object NoSavedItems extends IllustrationMessageType
  case object NoSavedItems_v1 extends IllustrationMessageType
  case object NoSearchResults extends IllustrationMessageType
  case object NoTasks extends IllustrationMessageType
  case object NoTasks_v1 extends IllustrationMessageType
  case object UnableToLoad extends IllustrationMessageType
  case object UnableToLoadImage extends IllustrationMessageType
  case object UnableToUpload extends IllustrationMessageType
  case object AddColumn extends IllustrationMessageType
  case object AddPeople extends IllustrationMessageType
  case object BalloonSky extends IllustrationMessageType
  case object Connection extends IllustrationMessageType
  case object EmptyCalendar extends IllustrationMessageType
  case object EmptyList extends IllustrationMessageType
  case object EmptyPlanningCalendar extends IllustrationMessageType
  case object ErrorScreen extends IllustrationMessageType
  case object FilterTable extends IllustrationMessageType
  case object GroupTable extends IllustrationMessageType
  case object NoFilterResults extends IllustrationMessageType
  case object PageNotFound extends IllustrationMessageType
  case object ReloadScreen extends IllustrationMessageType
  case object ResizeColumn extends IllustrationMessageType
  case object SearchEarth extends IllustrationMessageType
  case object SearchFolder extends IllustrationMessageType
  case object SimpleBalloon extends IllustrationMessageType
  case object SimpleBell extends IllustrationMessageType
  case object SimpleCalendar extends IllustrationMessageType
  case object SimpleCheckMark extends IllustrationMessageType
  case object SimpleConnection extends IllustrationMessageType
  case object SimpleEmptyDoc extends IllustrationMessageType
  case object SimpleEmptyList extends IllustrationMessageType
  case object SimpleError extends IllustrationMessageType
  case object SimpleMagnifier extends IllustrationMessageType
  case object SimpleMail extends IllustrationMessageType
  case object SimpleNoSavedItems extends IllustrationMessageType
  case object SimpleNotFoundMagnifier extends IllustrationMessageType
  case object SimpleReload extends IllustrationMessageType
  case object SimpleTask extends IllustrationMessageType
  case object SleepingBell extends IllustrationMessageType
  case object SortColumn extends IllustrationMessageType
  case object SuccessBalloon extends IllustrationMessageType
  case object SuccessCheckMark extends IllustrationMessageType
  case object SuccessHighFive extends IllustrationMessageType
  case object SuccessScreen extends IllustrationMessageType
  case object Tent extends IllustrationMessageType
  case object UploadCollection extends IllustrationMessageType
  case object TntCompany extends IllustrationMessageType
  case object TntExternalLink extends IllustrationMessageType
  case object TntFaceID extends IllustrationMessageType
  case object TntFingerprint extends IllustrationMessageType
  case object TntLock extends IllustrationMessageType
  case object TntMission extends IllustrationMessageType
  case object TntNoApplications extends IllustrationMessageType
  case object TntNoFlows extends IllustrationMessageType
  case object TntNoUsers extends IllustrationMessageType
  case object TntRadar extends IllustrationMessageType
  case object TntServices extends IllustrationMessageType
  case object TntSessionExpired extends IllustrationMessageType
  case object TntSessionExpiring extends IllustrationMessageType
  case object TntSuccess extends IllustrationMessageType
  case object TntSuccessfulAuth extends IllustrationMessageType
  case object TntUnlock extends IllustrationMessageType
  case object TntUnsuccessfulAuth extends IllustrationMessageType

  val allValues: List[IllustrationMessageType] = List(
    BeforeSearch,
    NoActivities,
    NoData,
    NoMail,
    NoMail_v1,
    NoEntries,
    NoNotifications,
    NoSavedItems,
    NoSavedItems_v1,
    NoSearchResults,
    NoTasks,
    NoTasks_v1,
    UnableToLoad,
    UnableToLoadImage,
    UnableToUpload,
    AddColumn,
    AddPeople,
    BalloonSky,
    Connection,
    EmptyCalendar,
    EmptyList,
    EmptyPlanningCalendar,
    ErrorScreen,
    FilterTable,
    GroupTable,
    NoFilterResults,
    PageNotFound,
    ReloadScreen,
    ResizeColumn,
    SearchEarth,
    SearchFolder,
    SimpleBalloon,
    SimpleBell,
    SimpleCalendar,
    SimpleCheckMark,
    SimpleConnection,
    SimpleEmptyDoc,
    SimpleEmptyList,
    SimpleError,
    SimpleMagnifier,
    SimpleMail,
    SimpleNoSavedItems,
    SimpleNotFoundMagnifier,
    SimpleReload,
    SimpleTask,
    SleepingBell,
    SortColumn,
    SuccessBalloon,
    SuccessCheckMark,
    SuccessHighFive,
    SuccessScreen,
    Tent,
    UploadCollection,
    TntCompany,
    TntExternalLink,
    TntFaceID,
    TntFingerprint,
    TntLock,
    TntMission,
    TntNoApplications,
    TntNoFlows,
    TntNoUsers,
    TntRadar,
    TntServices,
    TntSessionExpired,
    TntSessionExpiring,
    TntSuccess,
    TntSuccessfulAuth,
    TntUnlock,
    TntUnsuccessfulAuth
  )

  def valueOf(value: IllustrationMessageType): String = value.value
}
