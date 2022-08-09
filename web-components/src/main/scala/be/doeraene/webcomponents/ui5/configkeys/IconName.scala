package be.doeraene.webcomponents.ui5.configkeys

import com.raquo.domtypes.generic.codecs.Codec

sealed trait IconName

//noinspection NoTargetNameAnnotationForOperatorLikeDefinition
object IconName extends EnumerationString[IconName] {
  case object accelerated extends IconName
  case object accept extends IconName
  case object `accidental-leave` extends IconName
  case object account extends IconName
  case object `accounting-document-verification` extends IconName
  case object action extends IconName
  case object `action-settings` extends IconName
  case object activate extends IconName
  case object activities extends IconName
  case object `activity-2` extends IconName
  case object `activity-assigned-to-goal` extends IconName
  case object `activity-individual` extends IconName
  case object `activity-items` extends IconName
  case object add extends IconName
  case object `add-activity` extends IconName
  case object `add-activity-2` extends IconName
  case object `add-contact` extends IconName
  case object `add-coursebook` extends IconName
  case object `add-document` extends IconName
  case object `add-employee` extends IconName
  case object `add-equipment` extends IconName
  case object `add-favorite` extends IconName
  case object `add-filter` extends IconName
  case object `add-folder` extends IconName
  case object `add-photo` extends IconName
  case object `add-process` extends IconName
  case object `add-product` extends IconName
  case object `address-book` extends IconName
  case object addresses extends IconName
  case object alert extends IconName
  case object `along-stacked-chart` extends IconName
  case object `alphabetical-order` extends IconName
  case object `appear-offline` extends IconName
  case object appointment extends IconName
  case object `appointment-2` extends IconName
  case object approvals extends IconName
  case object `area-chart` extends IconName
  case object arobase extends IconName
  case object `arrow-bottom` extends IconName
  case object `arrow-down` extends IconName
  case object `arrow-left` extends IconName
  case object `arrow-right` extends IconName
  case object `arrow-top` extends IconName
  case object attachment extends IconName
  case object `attachment-audio` extends IconName
  case object `attachment-e-pub` extends IconName
  case object `attachment-html` extends IconName
  case object `attachment-photo` extends IconName
  case object `attachment-text-file` extends IconName
  case object `attachment-video` extends IconName
  case object `attachment-zip-file` extends IconName
  case object away extends IconName
  case object `back-to-top` extends IconName
  case object background extends IconName
  case object badge extends IconName
  case object `bar-chart` extends IconName
  case object `bar-code` extends IconName
  case object basket extends IconName
  case object `batch-payments` extends IconName
  case object `bbyd-active-sales` extends IconName
  case object `bbyd-dashboard` extends IconName
  case object bed extends IconName
  case object begin extends IconName
  case object bell extends IconName
  case object binary extends IconName
  case object `blank-tag` extends IconName
  case object `blank-tag-2` extends IconName
  case object blur extends IconName
  case object `bo-strategy-management` extends IconName
  case object `bold-text` extends IconName
  case object bookmark extends IconName
  case object `bookmark-2` extends IconName
  case object border extends IconName
  case object `broken-link` extends IconName
  case object `browse-folder` extends IconName
  case object `bubble-chart` extends IconName
  case object building extends IconName
  case object `bullet-text` extends IconName
  case object burglary extends IconName
  case object `bus-public-transport` extends IconName
  case object `business-by-design` extends IconName
  case object `business-card` extends IconName
  case object `business-objects-experience` extends IconName
  case object `business-objects-explorer` extends IconName
  case object `business-objects-mobile` extends IconName
  case object `business-one` extends IconName
  case object busy extends IconName
  case object calendar extends IconName
  case object call extends IconName
  case object camera extends IconName
  case object cancel extends IconName
  case object `cancel-maintenance` extends IconName
  case object `cancel-share` extends IconName
  case object `capital-projects` extends IconName
  case object `car-rental` extends IconName
  case object card extends IconName
  case object `cargo-train` extends IconName
  case object cart extends IconName
  case object `cart-2` extends IconName
  case object `cart-3` extends IconName
  case object `cart-4` extends IconName
  case object `cart-5` extends IconName
  case object `cart-approval` extends IconName
  case object `cart-full` extends IconName
  case object cause extends IconName
  case object `chain-link` extends IconName
  case object chalkboard extends IconName
  case object `chart-axis` extends IconName
  case object `chart-table-view` extends IconName
  case object `Chart-Tree-Map` extends IconName
  case object `check-availability` extends IconName
  case object checklist extends IconName
  case object `checklist-2` extends IconName
  case object `checklist-item` extends IconName
  case object `checklist-item-2` extends IconName
  case object `chevron-phase` extends IconName
  case object `chevron-phase-2` extends IconName
  case object `choropleth-chart` extends IconName
  case object `circle-task` extends IconName
  case object `circle-task-2` extends IconName
  case object `citizen-connect` extends IconName
  case object `clear-all` extends IconName
  case object `clear-filter` extends IconName
  case object `clinical-order` extends IconName
  case object `clinical-tast-tracker` extends IconName
  case object `close-command-field` extends IconName
  case object cloud extends IconName
  case object co extends IconName
  case object collaborate extends IconName
  case object collapse extends IconName
  case object `collapse-all` extends IconName
  case object `collapse-group` extends IconName
  case object `collections-insight` extends IconName
  case object `collections-management` extends IconName
  case object collision extends IconName
  case object `color-fill` extends IconName
  case object `column-chart-dual-axis` extends IconName
  case object combine extends IconName
  case object `command-line-interfaces` extends IconName
  case object comment extends IconName
  case object `commission-check` extends IconName
  case object `company-view` extends IconName
  case object compare extends IconName
  case object `compare-2` extends IconName
  case object competitor extends IconName
  case object complete extends IconName
  case object connected extends IconName
  case object contacts extends IconName
  case object copy extends IconName
  case object `course-book` extends IconName
  case object `course-program` extends IconName
  case object create extends IconName
  case object `create-entry-time` extends IconName
  case object `create-form` extends IconName
  case object `create-leave-request` extends IconName
  case object `create-session` extends IconName
  case object `credit-card` extends IconName
  case object `crm-sales` extends IconName
  case object `crm-service-manager` extends IconName
  case object crop extends IconName
  case object `crossed-line-chart` extends IconName
  case object curriculum extends IconName
  case object `cursor-arrow` extends IconName
  case object customer extends IconName
  case object `customer-and-contacts` extends IconName
  case object `customer-and-supplier` extends IconName
  case object `customer-briefing` extends IconName
  case object `customer-financial-fact-sheet` extends IconName
  case object `customer-history` extends IconName
  case object `customer-order-entry` extends IconName
  case object `customer-view` extends IconName
  case object customize extends IconName
  case object database extends IconName
  case object `date-time` extends IconName
  case object decision extends IconName
  case object decline extends IconName
  case object `decrease-line-height` extends IconName
  case object delete extends IconName
  case object `desktop-mobile` extends IconName
  case object `detail-less` extends IconName
  case object `detail-more` extends IconName
  case object `detail-view` extends IconName
  case object `developer-settings` extends IconName
  case object dimension extends IconName
  case object `direction-arrows` extends IconName
  case object disconnected extends IconName
  case object discussion extends IconName
  case object `discussion-2` extends IconName
  case object dishwasher extends IconName
  case object display extends IconName
  case object `display-more` extends IconName
  case object `doc-attachment` extends IconName
  case object doctor extends IconName
  case object document extends IconName
  case object `document-text` extends IconName
  case object documents extends IconName
  case object `donut-chart` extends IconName
  case object down extends IconName
  case object download extends IconName
  case object `download-from-cloud` extends IconName
  case object `draw-rectangle` extends IconName
  case object `drill-down` extends IconName
  case object `drill-up` extends IconName
  case object `drop-down-list` extends IconName
  case object dropdown extends IconName
  case object duplicate extends IconName
  case object `e-care` extends IconName
  case object `e-learning` extends IconName
  case object `eam-work-order` extends IconName
  case object edit extends IconName
  case object `edit-outside` extends IconName
  case object education extends IconName
  case object electrocardiogram extends IconName
  case object `electronic-medical-record` extends IconName
  case object email extends IconName
  case object `email-read` extends IconName
  case object employee extends IconName
  case object `employee-approvals` extends IconName
  case object `employee-lookup` extends IconName
  case object `employee-pane` extends IconName
  case object `employee-rejections` extends IconName
  case object `end-user-experience-monitoring` extends IconName
  case object endoscopy extends IconName
  case object `energy-saving-lightbulb` extends IconName
  case object `enter-more` extends IconName
  case object eraser extends IconName
  case object error extends IconName
  case object example extends IconName
  case object `excel-attachment` extends IconName
  case object `exit-full-screen` extends IconName
  case object expand extends IconName
  case object `expand-all` extends IconName
  case object `expand-group` extends IconName
  case object `expense-report` extends IconName
  case object explorer extends IconName
  case object factory extends IconName
  case object fallback extends IconName
  case object `family-care` extends IconName
  case object `family-protection` extends IconName
  case object favorite extends IconName
  case object `favorite-list` extends IconName
  case object `fax-machine` extends IconName
  case object feed extends IconName
  case object feedback extends IconName
  case object `feeder-arrow` extends IconName
  case object female extends IconName
  case object filter extends IconName
  case object `filter-analytics` extends IconName
  case object `filter-facets` extends IconName
  case object `filter-fields` extends IconName
  case object flag extends IconName
  case object `flag-2` extends IconName
  case object flight extends IconName
  case object `fob-watch` extends IconName
  case object folder extends IconName
  case object `folder-2` extends IconName
  case object `folder-blank` extends IconName
  case object `folder-full` extends IconName
  case object form extends IconName
  case object forward extends IconName
  case object fridge extends IconName
  case object `full-screen` extends IconName
  case object `full-stacked-chart` extends IconName
  case object `full-stacked-column-chart` extends IconName
  case object `functional-location` extends IconName
  case object future extends IconName
  case object fx extends IconName
  case object `gantt-bars` extends IconName
  case object `gender-male-and-female` extends IconName
  case object `general-leave-request` extends IconName
  case object `generate-shortcut` extends IconName
  case object `geographic-bubble-chart` extends IconName
  case object globe extends IconName
  case object goal extends IconName
  case object goalseek extends IconName
  case object grid extends IconName
  case object group extends IconName
  case object `group-2` extends IconName
  case object header extends IconName
  case object heading1 extends IconName
  case object heading2 extends IconName
  case object heading3 extends IconName
  case object headset extends IconName
  case object heart extends IconName
  case object `heart-2` extends IconName
  case object `heating-cooling` extends IconName
  case object `heatmap-chart` extends IconName
  case object `hello-world` extends IconName
  case object hide extends IconName
  case object hint extends IconName
  case object history extends IconName
  case object home extends IconName
  case object `home-share` extends IconName
  case object `horizontal-bar-chart` extends IconName
  case object `horizontal-bar-chart-2` extends IconName
  case object `horizontal-bullet-chart` extends IconName
  case object `horizontal-combination-chart` extends IconName
  case object `horizontal-grip` extends IconName
  case object `horizontal-stacked-chart` extends IconName
  case object `horizontal-waterfall-chart` extends IconName
  case object `hr-approval` extends IconName
  case object `idea-wall` extends IconName
  case object `image-viewer` extends IconName
  case object `in-progress` extends IconName
  case object inbox extends IconName
  case object incident extends IconName
  case object `incoming-call` extends IconName
  case object `increase-line-height` extends IconName
  case object indent extends IconName
  case object information extends IconName
  case object initiative extends IconName
  case object inspect extends IconName
  case object `inspect-down` extends IconName
  case object inspection extends IconName
  case object instance extends IconName
  case object `insurance-car` extends IconName
  case object `insurance-house` extends IconName
  case object `insurance-life` extends IconName
  case object `internet-browser` extends IconName
  case object inventory extends IconName
  case object ipad extends IconName
  case object `ipad-2` extends IconName
  case object iphone extends IconName
  case object `iphone-2` extends IconName
  case object `it-host` extends IconName
  case object `it-instance` extends IconName
  case object `it-system` extends IconName
  case object `italic-text` extends IconName
  case object jam extends IconName
  case object `journey-arrive` extends IconName
  case object `journey-change` extends IconName
  case object `journey-depart` extends IconName
  case object key extends IconName
  case object `key-user-settings` extends IconName
  case object `keyboard-and-mouse` extends IconName
  case object `kpi-corporate-performance` extends IconName
  case object `kpi-managing-my-area` extends IconName
  case object lab extends IconName
  case object laptop extends IconName
  case object lateness extends IconName
  case object lead extends IconName
  case object `lead-outdated` extends IconName
  case object leads extends IconName
  case object `learning-assistant` extends IconName
  case object legend extends IconName
  case object less extends IconName
  case object letter extends IconName
  case object lightbulb extends IconName
  case object `line-chart` extends IconName
  case object `line-chart-dual-axis` extends IconName
  case object `line-chart-time-axis` extends IconName
  case object `line-charts` extends IconName
  case object list extends IconName
  case object loan extends IconName
  case object `locate-me` extends IconName
  case object locked extends IconName
  case object log extends IconName
  case object machine extends IconName
  case object male extends IconName
  case object manager extends IconName
  case object `manager-insight` extends IconName
  case object map extends IconName
  case object `map-2` extends IconName
  case object `map-3` extends IconName
  case object `marketing-campaign` extends IconName
  case object `master-task-triangle` extends IconName
  case object `master-task-triangle-2` extends IconName
  case object meal extends IconName
  case object measure extends IconName
  case object `measurement-document` extends IconName
  case object `measuring-point` extends IconName
  case object `media-forward` extends IconName
  case object `media-pause` extends IconName
  case object `media-play` extends IconName
  case object `media-reverse` extends IconName
  case object `media-rewind` extends IconName
  case object `meeting-room` extends IconName
  case object megamenu extends IconName
  case object menu extends IconName
  case object menu2 extends IconName
  case object `message-error` extends IconName
  case object `message-information` extends IconName
  case object `message-popup` extends IconName
  case object `message-success` extends IconName
  case object `message-warning` extends IconName
  case object microphone extends IconName
  case object mileage extends IconName
  case object minimize extends IconName
  case object `mirrored-task-circle` extends IconName
  case object `mirrored-task-circle-2` extends IconName
  case object `money-bills` extends IconName
  case object `monitor-payments` extends IconName
  case object move extends IconName
  case object `mri-scan` extends IconName
  case object `multi-select` extends IconName
  case object `multiple-bar-chart` extends IconName
  case object `multiple-line-chart` extends IconName
  case object `multiple-pie-chart` extends IconName
  case object `multiple-radar-chart` extends IconName
  case object `multiselect-all` extends IconName
  case object `multiselect-none` extends IconName
  case object `my-sales-order` extends IconName
  case object `my-view` extends IconName
  case object `nav-back` extends IconName
  case object `navigation-down-arrow` extends IconName
  case object `navigation-left-arrow` extends IconName
  case object `navigation-right-arrow` extends IconName
  case object `navigation-up-arrow` extends IconName
  case object negative extends IconName
  case object `Netweaver-business-client` extends IconName
  case object newspaper extends IconName
  case object `not-editable` extends IconName
  case object notes extends IconName
  case object notification extends IconName
  case object `notification-2` extends IconName
  case object `number-sign` extends IconName
  case object `numbered-text` extends IconName
  case object nurse extends IconName
  case object `nutrition-activity` extends IconName
  case object `official-service` extends IconName
  case object `offsite-work` extends IconName
  case object `open-command-field` extends IconName
  case object `open-folder` extends IconName
  case object opportunities extends IconName
  case object opportunity extends IconName
  case object `order-status` extends IconName
  case object `org-chart` extends IconName
  case object outbox extends IconName
  case object outdent extends IconName
  case object `outgoing-call` extends IconName
  case object overflow extends IconName
  case object overlay extends IconName
  case object `overview-chart` extends IconName
  case object paging extends IconName
  case object `paid-leave` extends IconName
  case object `paint-bucket` extends IconName
  case object palette extends IconName
  case object `paper-plane` extends IconName
  case object `passenger-train` extends IconName
  case object past extends IconName
  case object paste extends IconName
  case object pause extends IconName
  case object `payment-approval` extends IconName
  case object `pdf-attachment` extends IconName
  case object `pdf-reader` extends IconName
  case object pending extends IconName
  case object `per-diem` extends IconName
  case object performance extends IconName
  case object permission extends IconName
  case object `person-placeholder` extends IconName
  case object `personnel-view` extends IconName
  case object pharmacy extends IconName
  case object phone extends IconName
  case object `photo-voltaic` extends IconName
  case object `physical-activity` extends IconName
  case object picture extends IconName
  case object `pie-chart` extends IconName
  case object `pipeline-analysis` extends IconName
  case object pixelate extends IconName
  case object play extends IconName
  case object pool extends IconName
  case object `popup-window` extends IconName
  case object positive extends IconName
  case object post extends IconName
  case object `ppt-attachment` extends IconName
  case object present extends IconName
  case object `primary-key` extends IconName
  case object print extends IconName
  case object `private` extends IconName
  case object process extends IconName
  case object product extends IconName
  case object `program-triangles` extends IconName
  case object `program-triangles-2` extends IconName
  case object `project-definition-triangle` extends IconName
  case object `project-definition-triangle-2` extends IconName
  case object projector extends IconName
  case object provision extends IconName
  case object `pull-down` extends IconName
  case object `pushpin-off` extends IconName
  case object `pushpin-on` extends IconName
  case object puzzle extends IconName
  case object `qr-code` extends IconName
  case object `quality-issue` extends IconName
  case object `question-mark` extends IconName
  case object `radar-chart` extends IconName
  case object receipt extends IconName
  case object record extends IconName
  case object redo extends IconName
  case object refresh extends IconName
  case object repost extends IconName
  case object request extends IconName
  case object reset extends IconName
  case object resize extends IconName
  case object `resize-corner` extends IconName
  case object `resize-horizontal` extends IconName
  case object `resize-vertical` extends IconName
  case object response extends IconName
  case object responsive extends IconName
  case object restart extends IconName
  case object `retail-store` extends IconName
  case object `retail-store-manager` extends IconName
  case object `rhombus-milestone` extends IconName
  case object `rhombus-milestone-2` extends IconName
  case object role extends IconName
  case object rotate extends IconName
  case object s4hana extends IconName
  case object `sales-document` extends IconName
  case object `sales-notification` extends IconName
  case object `sales-order` extends IconName
  case object `sales-order-item` extends IconName
  case object `sales-quote` extends IconName
  case object `sap-box` extends IconName
  case object `sap-logo-shape` extends IconName
  case object `sap-ui5` extends IconName
  case object save extends IconName
  case object `scatter-chart` extends IconName
  case object scissors extends IconName
  case object `screen-split-one` extends IconName
  case object `screen-split-three` extends IconName
  case object `screen-split-two` extends IconName
  case object search extends IconName
  case object settings extends IconName
  case object share extends IconName
  case object `share-2` extends IconName
  case object shelf extends IconName
  case object shield extends IconName
  case object `shipping-status` extends IconName
  case object shortcut extends IconName
  case object show extends IconName
  case object `show-edit` extends IconName
  case object signature extends IconName
  case object `simple-payment` extends IconName
  case object simulate extends IconName
  case object `slim-arrow-down` extends IconName
  case object `slim-arrow-left` extends IconName
  case object `slim-arrow-right` extends IconName
  case object `slim-arrow-up` extends IconName
  case object soccer extends IconName
  case object soccor extends IconName
  case object sonography extends IconName
  case object sort extends IconName
  case object `sort-ascending` extends IconName
  case object `sort-descending` extends IconName
  case object `sorting-ranking` extends IconName
  case object sound extends IconName
  case object `sound-loud` extends IconName
  case object `sound-off` extends IconName
  case object `source-code` extends IconName
  case object `space-navigation` extends IconName
  case object split extends IconName
  case object `status-completed` extends IconName
  case object `status-critical` extends IconName
  case object `status-error` extends IconName
  case object `status-in-process` extends IconName
  case object `status-inactive` extends IconName
  case object `status-negative` extends IconName
  case object `status-positive` extends IconName
  case object step extends IconName
  case object stethoscope extends IconName
  case object stop extends IconName
  case object strikethrough extends IconName
  case object `study-leave` extends IconName
  case object `subway-train` extends IconName
  case object suitcase extends IconName
  case object sum extends IconName
  case object supplier extends IconName
  case object survey extends IconName
  case object `switch-classes` extends IconName
  case object `switch-views` extends IconName
  case object synchronize extends IconName
  case object syntax extends IconName
  case object syringe extends IconName
  case object `sys-add` extends IconName
  case object `sys-back` extends IconName
  case object `sys-back-2` extends IconName
  case object `sys-cancel` extends IconName
  case object `sys-cancel-2` extends IconName
  case object `sys-enter` extends IconName
  case object `sys-enter-2` extends IconName
  case object `sys-find` extends IconName
  case object `sys-find-next` extends IconName
  case object `sys-first-page` extends IconName
  case object `sys-help` extends IconName
  case object `sys-help-2` extends IconName
  case object `sys-last-page` extends IconName
  case object `sys-minus` extends IconName
  case object `sys-monitor` extends IconName
  case object `sys-next-page` extends IconName
  case object `sys-prev-page` extends IconName
  case object `system-exit` extends IconName
  case object `system-exit-2` extends IconName
  case object `table-chart` extends IconName
  case object `table-column` extends IconName
  case object `table-row` extends IconName
  case object `table-view` extends IconName
  case object tag extends IconName
  case object `tag-cloud-chart` extends IconName
  case object tags extends IconName
  case object `target-group` extends IconName
  case object task extends IconName
  case object taxi extends IconName
  case object `technical-object` extends IconName
  case object temperature extends IconName
  case object text extends IconName
  case object `text-align-center` extends IconName
  case object `text-align-justified` extends IconName
  case object `text-align-left` extends IconName
  case object `text-align-right` extends IconName
  case object `text-color` extends IconName
  case object `text-formatting` extends IconName
  case object theater extends IconName
  case object `thing-type` extends IconName
  case object `thumb-down` extends IconName
  case object `thumb-up` extends IconName
  case object `time-account` extends IconName
  case object `time-entry-request` extends IconName
  case object `time-overtime` extends IconName
  case object timesheet extends IconName
  case object `to-be-reviewed` extends IconName
  case object `toaster-down` extends IconName
  case object `toaster-top` extends IconName
  case object `toaster-up` extends IconName
  case object `tools-opportunity` extends IconName
  case object touch extends IconName
  case object translate extends IconName
  case object `travel-expense` extends IconName
  case object `travel-expense-report` extends IconName
  case object `travel-itinerary` extends IconName
  case object `travel-request` extends IconName
  case object tree extends IconName
  case object `trend-down` extends IconName
  case object `trend-up` extends IconName
  case object `tri-state` extends IconName
  case object `trip-report` extends IconName
  case object `two-keys` extends IconName
  case object `ui-notifications` extends IconName
  case object umbrella extends IconName
  case object `underline-text` extends IconName
  case object undo extends IconName
  case object unfavorite extends IconName
  case object unlocked extends IconName
  case object `unpaid-leave` extends IconName
  case object unwired extends IconName
  case object up extends IconName
  case object upload extends IconName
  case object `upload-to-cloud` extends IconName
  case object `upstacked-chart` extends IconName
  case object `user-edit` extends IconName
  case object `user-settings` extends IconName
  case object validate extends IconName
  case object `value-help` extends IconName
  case object `vds-file` extends IconName
  case object `vehicle-repair` extends IconName
  case object `vertical-bar-chart` extends IconName
  case object `vertical-bar-chart-2` extends IconName
  case object `vertical-bullet-chart` extends IconName
  case object `vertical-grip` extends IconName
  case object `vertical-stacked-chart` extends IconName
  case object `vertical-waterfall-chart` extends IconName
  case object video extends IconName
  case object visits extends IconName
  case object waiver extends IconName
  case object wallet extends IconName
  case object warning extends IconName
  case object warning2 extends IconName
  case object `washing-machine` extends IconName
  case object `weather-proofing` extends IconName
  case object `web-cam` extends IconName
  case object widgets extends IconName
  case object `windows-doors` extends IconName
  case object `work-history` extends IconName
  case object `workflow-tasks` extends IconName
  case object world extends IconName
  case object `wounds-doc` extends IconName
  case object wrench extends IconName
  case object `write-new` extends IconName
  case object `write-new-document` extends IconName
  case object `x-ray` extends IconName
  case object `zoom-in` extends IconName
  case object `zoom-out` extends IconName

  val allValues: List[IconName] =
    List(
      accelerated,
      accept,
      `accidental-leave`,
      account,
      `accounting-document-verification`,
      action,
      `action-settings`,
      activate,
      activities,
      `activity-2`,
      `activity-assigned-to-goal`,
      `activity-individual`,
      `activity-items`,
      add,
      `add-activity`,
      `add-activity-2`,
      `add-contact`,
      `add-coursebook`,
      `add-document`,
      `add-employee`,
      `add-equipment`,
      `add-favorite`,
      `add-filter`,
      `add-folder`,
      `add-photo`,
      `add-process`,
      `add-product`,
      `address-book`,
      addresses,
      alert,
      `along-stacked-chart`,
      `alphabetical-order`,
      `appear-offline`,
      appointment,
      `appointment-2`,
      approvals,
      `area-chart`,
      arobase,
      `arrow-bottom`,
      `arrow-down`,
      `arrow-left`,
      `arrow-right`,
      `arrow-top`,
      attachment,
      `attachment-audio`,
      `attachment-e-pub`,
      `attachment-html`,
      `attachment-photo`,
      `attachment-text-file`,
      `attachment-video`,
      `attachment-zip-file`,
      away,
      `back-to-top`,
      background,
      badge,
      `bar-chart`,
      `bar-code`,
      basket,
      `batch-payments`,
      `bbyd-active-sales`,
      `bbyd-dashboard`,
      bed,
      begin,
      bell,
      binary,
      `blank-tag`,
      `blank-tag-2`,
      blur,
      `bo-strategy-management`,
      `bold-text`,
      bookmark,
      `bookmark-2`,
      border,
      `broken-link`,
      `browse-folder`,
      `bubble-chart`,
      building,
      `bullet-text`,
      burglary,
      `bus-public-transport`,
      `business-by-design`,
      `business-card`,
      `business-objects-experience`,
      `business-objects-explorer`,
      `business-objects-mobile`,
      `business-one`,
      busy,
      calendar,
      call,
      camera,
      cancel,
      `cancel-maintenance`,
      `cancel-share`,
      `capital-projects`,
      `car-rental`,
      card,
      `cargo-train`,
      cart,
      `cart-2`,
      `cart-3`,
      `cart-4`,
      `cart-5`,
      `cart-approval`,
      `cart-full`,
      cause,
      `chain-link`,
      chalkboard,
      `chart-axis`,
      `chart-table-view`,
      `Chart-Tree-Map`,
      `check-availability`,
      checklist,
      `checklist-2`,
      `checklist-item`,
      `checklist-item-2`,
      `chevron-phase`,
      `chevron-phase-2`,
      `choropleth-chart`,
      `circle-task`,
      `circle-task-2`,
      `citizen-connect`,
      `clear-all`,
      `clear-filter`,
      `clinical-order`,
      `clinical-tast-tracker`,
      `close-command-field`,
      cloud,
      co,
      collaborate,
      collapse,
      `collapse-all`,
      `collapse-group`,
      `collections-insight`,
      `collections-management`,
      collision,
      `color-fill`,
      `column-chart-dual-axis`,
      combine,
      `command-line-interfaces`,
      comment,
      `commission-check`,
      `company-view`,
      compare,
      `compare-2`,
      competitor,
      complete,
      connected,
      contacts,
      copy,
      `course-book`,
      `course-program`,
      create,
      `create-entry-time`,
      `create-form`,
      `create-leave-request`,
      `create-session`,
      `credit-card`,
      `crm-sales`,
      `crm-service-manager`,
      crop,
      `crossed-line-chart`,
      curriculum,
      `cursor-arrow`,
      customer,
      `customer-and-contacts`,
      `customer-and-supplier`,
      `customer-briefing`,
      `customer-financial-fact-sheet`,
      `customer-history`,
      `customer-order-entry`,
      `customer-view`,
      customize,
      database,
      `date-time`,
      decision,
      decline,
      `decrease-line-height`,
      delete,
      `desktop-mobile`,
      `detail-less`,
      `detail-more`,
      `detail-view`,
      `developer-settings`,
      dimension,
      `direction-arrows`,
      disconnected,
      discussion,
      `discussion-2`,
      dishwasher,
      display,
      `display-more`,
      `doc-attachment`,
      doctor,
      document,
      `document-text`,
      documents,
      `donut-chart`,
      down,
      download,
      `download-from-cloud`,
      `draw-rectangle`,
      `drill-down`,
      `drill-up`,
      `drop-down-list`,
      dropdown,
      duplicate,
      `e-care`,
      `e-learning`,
      `eam-work-order`,
      edit,
      `edit-outside`,
      education,
      electrocardiogram,
      `electronic-medical-record`,
      email,
      `email-read`,
      employee,
      `employee-approvals`,
      `employee-lookup`,
      `employee-pane`,
      `employee-rejections`,
      `end-user-experience-monitoring`,
      endoscopy,
      `energy-saving-lightbulb`,
      `enter-more`,
      eraser,
      error,
      example,
      `excel-attachment`,
      `exit-full-screen`,
      expand,
      `expand-all`,
      `expand-group`,
      `expense-report`,
      explorer,
      factory,
      fallback,
      `family-care`,
      `family-protection`,
      favorite,
      `favorite-list`,
      `fax-machine`,
      feed,
      feedback,
      `feeder-arrow`,
      female,
      filter,
      `filter-analytics`,
      `filter-facets`,
      `filter-fields`,
      flag,
      `flag-2`,
      flight,
      `fob-watch`,
      folder,
      `folder-2`,
      `folder-blank`,
      `folder-full`,
      form,
      forward,
      fridge,
      `full-screen`,
      `full-stacked-chart`,
      `full-stacked-column-chart`,
      `functional-location`,
      future,
      fx,
      `gantt-bars`,
      `gender-male-and-female`,
      `general-leave-request`,
      `generate-shortcut`,
      `geographic-bubble-chart`,
      globe,
      goal,
      goalseek,
      grid,
      group,
      `group-2`,
      header,
      heading1,
      heading2,
      heading3,
      headset,
      heart,
      `heart-2`,
      `heating-cooling`,
      `heatmap-chart`,
      `hello-world`,
      hide,
      hint,
      history,
      home,
      `home-share`,
      `horizontal-bar-chart`,
      `horizontal-bar-chart-2`,
      `horizontal-bullet-chart`,
      `horizontal-combination-chart`,
      `horizontal-grip`,
      `horizontal-stacked-chart`,
      `horizontal-waterfall-chart`,
      `hr-approval`,
      `idea-wall`,
      `image-viewer`,
      `in-progress`,
      inbox,
      incident,
      `incoming-call`,
      `increase-line-height`,
      indent,
      information,
      initiative,
      inspect,
      `inspect-down`,
      inspection,
      instance,
      `insurance-car`,
      `insurance-house`,
      `insurance-life`,
      `internet-browser`,
      inventory,
      ipad,
      `ipad-2`,
      iphone,
      `iphone-2`,
      `it-host`,
      `it-instance`,
      `it-system`,
      `italic-text`,
      jam,
      `journey-arrive`,
      `journey-change`,
      `journey-depart`,
      key,
      `key-user-settings`,
      `keyboard-and-mouse`,
      `kpi-corporate-performance`,
      `kpi-managing-my-area`,
      lab,
      laptop,
      lateness,
      lead,
      `lead-outdated`,
      leads,
      `learning-assistant`,
      legend,
      less,
      letter,
      lightbulb,
      `line-chart`,
      `line-chart-dual-axis`,
      `line-chart-time-axis`,
      `line-charts`,
      list,
      loan,
      `locate-me`,
      locked,
      log,
      machine,
      male,
      manager,
      `manager-insight`,
      map,
      `map-2`,
      `map-3`,
      `marketing-campaign`,
      `master-task-triangle`,
      `master-task-triangle-2`,
      meal,
      measure,
      `measurement-document`,
      `measuring-point`,
      `media-forward`,
      `media-pause`,
      `media-play`,
      `media-reverse`,
      `media-rewind`,
      `meeting-room`,
      megamenu,
      menu,
      menu2,
      `message-error`,
      `message-information`,
      `message-popup`,
      `message-success`,
      `message-warning`,
      microphone,
      mileage,
      minimize,
      `mirrored-task-circle`,
      `mirrored-task-circle-2`,
      `money-bills`,
      `monitor-payments`,
      move,
      `mri-scan`,
      `multi-select`,
      `multiple-bar-chart`,
      `multiple-line-chart`,
      `multiple-pie-chart`,
      `multiple-radar-chart`,
      `multiselect-all`,
      `multiselect-none`,
      `my-sales-order`,
      `my-view`,
      `nav-back`,
      `navigation-down-arrow`,
      `navigation-left-arrow`,
      `navigation-right-arrow`,
      `navigation-up-arrow`,
      negative,
      `Netweaver-business-client`,
      newspaper,
      `not-editable`,
      notes,
      notification,
      `notification-2`,
      `number-sign`,
      `numbered-text`,
      nurse,
      `nutrition-activity`,
      `official-service`,
      `offsite-work`,
      `open-command-field`,
      `open-folder`,
      opportunities,
      opportunity,
      `order-status`,
      `org-chart`,
      outbox,
      outdent,
      `outgoing-call`,
      overflow,
      overlay,
      `overview-chart`,
      paging,
      `paid-leave`,
      `paint-bucket`,
      palette,
      `paper-plane`,
      `passenger-train`,
      past,
      paste,
      pause,
      `payment-approval`,
      `pdf-attachment`,
      `pdf-reader`,
      pending,
      `per-diem`,
      performance,
      permission,
      `person-placeholder`,
      `personnel-view`,
      pharmacy,
      phone,
      `photo-voltaic`,
      `physical-activity`,
      picture,
      `pie-chart`,
      `pipeline-analysis`,
      pixelate,
      play,
      pool,
      `popup-window`,
      positive,
      post,
      `ppt-attachment`,
      present,
      `primary-key`,
      print,
      `private`,
      process,
      product,
      `program-triangles`,
      `program-triangles-2`,
      `project-definition-triangle`,
      `project-definition-triangle-2`,
      projector,
      provision,
      `pull-down`,
      `pushpin-off`,
      `pushpin-on`,
      puzzle,
      `qr-code`,
      `quality-issue`,
      `question-mark`,
      `radar-chart`,
      receipt,
      record,
      redo,
      refresh,
      repost,
      request,
      reset,
      resize,
      `resize-corner`,
      `resize-horizontal`,
      `resize-vertical`,
      response,
      responsive,
      restart,
      `retail-store`,
      `retail-store-manager`,
      `rhombus-milestone`,
      `rhombus-milestone-2`,
      role,
      rotate,
      s4hana,
      `sales-document`,
      `sales-notification`,
      `sales-order`,
      `sales-order-item`,
      `sales-quote`,
      `sap-box`,
      `sap-logo-shape`,
      `sap-ui5`,
      save,
      `scatter-chart`,
      scissors,
      `screen-split-one`,
      `screen-split-three`,
      `screen-split-two`,
      search,
      settings,
      share,
      `share-2`,
      shelf,
      shield,
      `shipping-status`,
      shortcut,
      show,
      `show-edit`,
      signature,
      `simple-payment`,
      simulate,
      `slim-arrow-down`,
      `slim-arrow-left`,
      `slim-arrow-right`,
      `slim-arrow-up`,
      soccer,
      soccor,
      sonography,
      sort,
      `sort-ascending`,
      `sort-descending`,
      `sorting-ranking`,
      sound,
      `sound-loud`,
      `sound-off`,
      `source-code`,
      `space-navigation`,
      split,
      `status-completed`,
      `status-critical`,
      `status-error`,
      `status-in-process`,
      `status-inactive`,
      `status-negative`,
      `status-positive`,
      step,
      stethoscope,
      stop,
      strikethrough,
      `study-leave`,
      `subway-train`,
      suitcase,
      sum,
      supplier,
      survey,
      `switch-classes`,
      `switch-views`,
      synchronize,
      syntax,
      syringe,
      `sys-add`,
      `sys-back`,
      `sys-back-2`,
      `sys-cancel`,
      `sys-cancel-2`,
      `sys-enter`,
      `sys-enter-2`,
      `sys-find`,
      `sys-find-next`,
      `sys-first-page`,
      `sys-help`,
      `sys-help-2`,
      `sys-last-page`,
      `sys-minus`,
      `sys-monitor`,
      `sys-next-page`,
      `sys-prev-page`,
      `system-exit`,
      `system-exit-2`,
      `table-chart`,
      `table-column`,
      `table-row`,
      `table-view`,
      tag,
      `tag-cloud-chart`,
      tags,
      `target-group`,
      task,
      taxi,
      `technical-object`,
      temperature,
      text,
      `text-align-center`,
      `text-align-justified`,
      `text-align-left`,
      `text-align-right`,
      `text-color`,
      `text-formatting`,
      theater,
      `thing-type`,
      `thumb-down`,
      `thumb-up`,
      `time-account`,
      `time-entry-request`,
      `time-overtime`,
      timesheet,
      `to-be-reviewed`,
      `toaster-down`,
      `toaster-top`,
      `toaster-up`,
      `tools-opportunity`,
      touch,
      translate,
      `travel-expense`,
      `travel-expense-report`,
      `travel-itinerary`,
      `travel-request`,
      tree,
      `trend-down`,
      `trend-up`,
      `tri-state`,
      `trip-report`,
      `two-keys`,
      `ui-notifications`,
      umbrella,
      `underline-text`,
      undo,
      unfavorite,
      unlocked,
      `unpaid-leave`,
      unwired,
      up,
      upload,
      `upload-to-cloud`,
      `upstacked-chart`,
      `user-edit`,
      `user-settings`,
      validate,
      `value-help`,
      `vds-file`,
      `vehicle-repair`,
      `vertical-bar-chart`,
      `vertical-bar-chart-2`,
      `vertical-bullet-chart`,
      `vertical-grip`,
      `vertical-stacked-chart`,
      `vertical-waterfall-chart`,
      video,
      visits,
      waiver,
      wallet,
      warning,
      warning2,
      `washing-machine`,
      `weather-proofing`,
      `web-cam`,
      widgets,
      `windows-doors`,
      `work-history`,
      `workflow-tasks`,
      world,
      `wounds-doc`,
      wrench,
      `write-new`,
      `write-new-document`,
      `x-ray`,
      `zoom-in`,
      `zoom-out`
    )

  def valueOf(value: IconName): String = value.toString

}
