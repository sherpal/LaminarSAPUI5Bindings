package be.doeraene.webcomponents.ui5.configkeys

import scala.scalajs.js
import com.raquo.domtypes.generic.codecs.{Codec, StringAsIsCodec}

//noinspection ScalaUnusedSymbol
object IllustrationMessageType {

  opaque type IllustrationMessageType <: String = String

  def BeforeSearch: IllustrationMessageType = "BeforeSearch"
  def NoActivities: IllustrationMessageType = "NoActivities"
  def NoData: IllustrationMessageType = "NoData"
  def NoMail: IllustrationMessageType = "NoMail"
  def NoMail_v1: IllustrationMessageType = "NoMail_v1"
  def NoEntries: IllustrationMessageType = "NoEntries"
  def NoNotifications: IllustrationMessageType = "NoNotifications"
  def NoSavedItems: IllustrationMessageType = "NoSavedItems"
  def NoSavedItems_v1: IllustrationMessageType = "NoSavedItems_v1"
  def NoSearchResults: IllustrationMessageType = "NoSearchResults"
  def NoTasks: IllustrationMessageType = "NoTasks"
  def NoTasks_v1: IllustrationMessageType = "NoTasks_v1"
  def UnableToLoad: IllustrationMessageType = "UnableToLoad"
  def UnableToLoadImage: IllustrationMessageType = "UnableToLoadImage"
  def UnableToUpload: IllustrationMessageType = "UnableToUpload"
  def AddColumn: IllustrationMessageType = "AddColumn"
  def AddPeople: IllustrationMessageType = "AddPeople"
  def BalloonSky: IllustrationMessageType = "BalloonSky"
  def Connection: IllustrationMessageType = "Connection"
  def EmptyCalendar: IllustrationMessageType = "EmptyCalendar"
  def EmptyList: IllustrationMessageType = "EmptyList"
  def EmptyPlanningCalendar: IllustrationMessageType = "EmptyPlanningCalendar"
  def ErrorScreen: IllustrationMessageType = "ErrorScreen"
  def FilterTable: IllustrationMessageType = "FilterTable"
  def GroupTable: IllustrationMessageType = "GroupTable"
  def NoFilterResults: IllustrationMessageType = "NoFilterResults"
  def PageNotFound: IllustrationMessageType = "PageNotFound"
  def ReloadScreen: IllustrationMessageType = "ReloadScreen"
  def ResizeColumn: IllustrationMessageType = "ResizeColumn"
  def SearchEarth: IllustrationMessageType = "SearchEarth"
  def SearchFolder: IllustrationMessageType = "SearchFolder"
  def SimpleBalloon: IllustrationMessageType = "SimpleBalloon"
  def SimpleBell: IllustrationMessageType = "SimpleBell"
  def SimpleCalendar: IllustrationMessageType = "SimpleCalendar"
  def SimpleCheckMark: IllustrationMessageType = "SimpleCheckMark"
  def SimpleConnection: IllustrationMessageType = "SimpleConnection"
  def SimpleEmptyDoc: IllustrationMessageType = "SimpleEmptyDoc"
  def SimpleEmptyList: IllustrationMessageType = "SimpleEmptyList"
  def SimpleError: IllustrationMessageType = "SimpleError"
  def SimpleMagnifier: IllustrationMessageType = "SimpleMagnifier"
  def SimpleMail: IllustrationMessageType = "SimpleMail"
  def SimpleNoSavedItems: IllustrationMessageType = "SimpleNoSavedItems"
  def SimpleNotFoundMagnifier: IllustrationMessageType = "SimpleNotFoundMagnifier"
  def SimpleReload: IllustrationMessageType = "SimpleReload"
  def SimpleTask: IllustrationMessageType = "SimpleTask"
  def SleepingBell: IllustrationMessageType = "SleepingBell"
  def SortColumn: IllustrationMessageType = "SortColumn"
  def SuccessBalloon: IllustrationMessageType = "SuccessBalloon"
  def SuccessCheckMark: IllustrationMessageType = "SuccessCheckMark"
  def SuccessHighFive: IllustrationMessageType = "SuccessHighFive"
  def SuccessScreen: IllustrationMessageType = "SuccessScreen"
  def Tent: IllustrationMessageType = "Tent"
  def UploadCollection: IllustrationMessageType = "UploadCollection"
  def TntCompany: IllustrationMessageType = "TntCompany"
  def TntExternalLink: IllustrationMessageType = "TntExternalLink"
  def TntFaceID: IllustrationMessageType = "TntFaceID"
  def TntFingerprint: IllustrationMessageType = "TntFingerprint"
  def TntLock: IllustrationMessageType = "TntLock"
  def TntMission: IllustrationMessageType = "TntMission"
  def TntNoApplications: IllustrationMessageType = "TntNoApplications"
  def TntNoFlows: IllustrationMessageType = "TntNoFlows"
  def TntNoUsers: IllustrationMessageType = "TntNoUsers"
  def TntRadar: IllustrationMessageType = "TntRadar"
  def TntServices: IllustrationMessageType = "TntServices"
  def TntSessionExpired: IllustrationMessageType = "TntSessionExpired"
  def TntSessionExpiring: IllustrationMessageType = "TntSessionExpiring"
  def TntSuccess: IllustrationMessageType = "TntSuccess"
  def TntSuccessfulAuth: IllustrationMessageType = "TntSuccessfulAuth"
  def TntUnlock: IllustrationMessageType = "TntUnlock"
  def TntUnsuccessfulAuth: IllustrationMessageType = "TntUnsuccessfulAuth"

  def AsStringCodec: Codec[IllustrationMessageType, String] = StringAsIsCodec

  /**
    * If the IllustrationMessageType you are looking is not there for some reason, you can fallback to this usage.
    */
  def _raw(value: String): IllustrationMessageType = value

}

type IllustrationMessageType = IllustrationMessageType.IllustrationMessageType