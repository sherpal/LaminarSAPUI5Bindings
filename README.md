# Laminar bindings for SAP ui5 web-components library

This repository contains [Laminar](https://laminar.dev/) bindings for [SAP ui5 web-components](https://sap.github.io/ui5-webcomponents/).

Web components are a powerful technology to create near-native html elements. Laminar, for its part, is a Scala-js framework/library to manipulate the dom.

## Installation

In order to use these bindings within your Scala.js project, you need to add the following to your `build.sbt`:

```scala
resolvers += "jitpack" at "https://jitpack.io"

// if using scala 2.13
scalacOptions ++= List("-Ytasty-reader")

libraryDependencies ++= List(
  "com.github.sherpal" % "LaminarSAPUI5Bindings" % "<release-tag>",
  "com.raquo" %%% "laminar" % "0.14.2"
)
```

where `<release-tag>` must be replaced with any of the release tags from [here](https://github.com/sherpal/LaminarSAPUI5Bindings/releases). Note that it is correctly defined with single `%`. This thing using Jitpack which I'm not sure I understand, but it works like that.

The relase tag is composed of the version of the project, followed by the (beginning of the) commit hash that issued the release.

The version of the project corresponds to the version of the SAP UI5 version of the libraries these bindings are made for. Using these bindings with an earlier version will probably show stuff that did not exist at the time. Similarly, using these bindings with an older version will imply that you will not find certain bindings. **Other than that, it is perfectly safe to mix mismatch versions.**

### Note on the Laminar version

This library is built against a certain version of Laminar, but it is set as "provided". That means that you have to depend on Laminar yourself, but (for a large deal) you are not bound to the specific version used here.

## What if I find a missing bindings?

First of all, don't panic. You can do the following things:

1. Open an issue in this repository, referencing the missing component from [the official docs](https://sap.github.io/ui5-webcomponents/playground/components)
2. In order to not be stuck in your project right away, you can implement the bindings you need yourself. See the section below to see how to do that
3. If you feel like it, create a PR to this repository with your new implementation.

## How to use

These bindings are barely "facades" types for the official ui5 library. They _won't_ work if you don't handle that npm dependency on your own project. You will need the following imports in your `package.json` (or equivalent tool such as scala-js-bundler):

```
"@ui5/webcomponents": "1.3.0",
"@ui5/webcomponents-fiori": "1.3.0",
"@ui5/webcomponents-icons": "1.3.0"
```

(and thus `npm install` it). Then, you can use any of the components as defined in the `be.doeraene.webcomponents.ui5` package.

For example, you can add an `<ui5-input>` in your Laminar code like this:

```scala
div(
  Input(
    _.required := true,
    _.valueState := ValueState.Information,
    _.placeholder := "Enter your name",
    _ => onChange.mapToValue --> Observer(println)
  )
)
```

This will be rendered as an input as documented [here](https://sap.github.io/ui5-webcomponents/playground/components/Input/).

### How to use slots?

In web-components, certain components can have special dom children called "slots". In the dom, these "special" children are only singular in the fact that they have a property `slot` specifying which slot they fill in.

For example, the `Dialog` component as a `footer` slot. In raw html, this would look like

```html
<ui5-dialog>
  <div slot="footer">I'm a footer</div>
</ui5-dialog>
```

Thanks to the `slots` object in the `Dialog` component, this is written in Scala as

```scala
Dialog(
  _.footer := div("I'm a fotter")
)
```

See the implementation of the `Slot` class to understand what it does.

## How to read the source code

Every component is implemented as an object with the following:

- a `RawElement` (js-native) trait that defines methods that the underlying DOM element will have
- a `RawImport` object only there to actually import the library
- a few reactive html attributes specific to that component
- a `slots` object that contains descriptions of the slots that the component can have. They allow syntax such as `Input(_.slots.valueStateMessage := span("Hello"))`.
- an `events` object containing specific events that the component can emit.
- an `apply` method to build the component (following the [example from the Laminar website](https://laminar.dev/examples/web-components), they take functions from the object to mods)
- some components will have links to other tightly coupled components (for example, the `TabContainer` has a `tab` link to the `Tab` component). They allow syntax such as `TabContainer(_.tab := ???)`.

Every object is named after the corresponding ui5 web-component, with the notable exception of `List` which is called `UList` to not interfere with the built-in Scala `List` type.

### Config Keys package

The ui5 library contains a whole lot of "enums" that are used as properties of elements. In TypeScript, this would typically be represented as

```typescript
type Stuff = "foo" | "bar" | "baz"
```

In Scala 3, this approach could work in theory, but not I'm not really fond of. Instead, I prefer to define those as sealed traits like this

```scala
sealed trait Stuff
object Stuff {
  case object Foo extends Stuff
  case object Bar extends Stuff
  case object Baz extends Stuff
}
```

In order to facilitate such usage with Laminar, these objects will typically extends `EnumerationString`, which helps by providing the codec required to represent this as a string.

## Contributing: Implement bindings for a new component

If you found a missing component that you would like to see integrated in the repo, please find below small guidelines to help you with. There are quite a lot of steps but don't worry, in pratice you can do most of these by simply copy-pasting another existing component!

#### Before you start

Be sure to check the section about How to read the source code, above.

Also, verify that the component you are about to add is available in the current matching version defined in the `build.sbt` as

```scala
ThisBuild / version := "<current supported sap version>"
```

If not, please open an issue to upgrade to the new version. This does not prevent you to already do the following steps.

#### Create the scala file

In the `be.doeraene.webcomponents.ui5` package, create a new `object` called the same way as the component, as visible in the docs page (for example, for the [busy indicator](https://sap.github.io/ui5-webcomponents/playground/components/BusyIndicator/), it will be `BusyIndicator`)

#### Make the docstring

Fill in the docstring for that object with the contents of the "Overview" section from the docs, and a `@see` referencing the official docs webpage

#### Write the boilerplate

In the `object`, add the following things:

- create a trait `RawElement` extending `js.Object` and annotated with `@js.native`
- add an object `RawImport` extending `js.Object` and annotated with both `@js.native` and `@JSImport`, specifying the correct import (available in the official docs), setting `JSImport.Default` as second argument
- call `used(RawImport)` the line after (this is done to be sure that scala-js actually import the JS dependency)
- define an alias `type Ref` as `dom.html.Element & RawElement`
- define an alias `type ModFunction` as `YourObject.type => Mod[ReactiveHtmlElement[Ref]]`
- define a private `tag` variable of type `HtmlTag[Ref]` specifying the ui5 tag name from the doc (for example, for the Button component, it's `private val tag: HtmlTag[Ref] = customHtmlTag("ui5-button")`). ⚠️: when copy-pasting from an existing component, this is usually the one we forget! When that happens, you will observe a component doing basically nothing. It's a sign you put the wrong import.
- create an empty object `slots`
- create an empty object `events`
- define an apply method as `def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(YourObject)): _*)`
- in the case where your component is linked to other components (for example a `TableCell` is always contained in `TableRow`, so the `TableRow` object will have a reference to the `TableCell` object)

#### Filling the reactive attributes

The official docs always have a "Properties/Attributes" section. All these properties should be converted into `ReactiveHtmlAttr`. For example, the `disabled` attribute of Button is defined as

```scala
val disabled: ReactiveHtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)
```

Note that while it's not mandatory that the name of the variable matches the name of the attribute, it's customary to use the same (camelCase) naming.

For "primitive" types (`Boolean`, `String`, `Int`), you can use the codecs provided by Laminar. For "enums", see below (essentially you will need to define an `EnumerationString` type as described in the next section).

For durations, _expressed in millis_, you can use the `FiniteDurationCodec` available for you in the package object.

You can decide to create more involved types a bit fancy and create custom codecs, but you need to decide whether it's worth it. Such example could be if you have a type representing numbers between 0 and 10 because that are the only values that are valid by ui5.

Some common attribute are "packaged" in traits that your component object can inherit from (e.g., `HasIcon`).

Note: in theory all these properties could (should?) also exist on the `RawElement` trait. However, it is not really idiomatic to Laminar to use these. If, for some reason, you still think some property should be there, it should be "readonly" (that is, defined as a `def`).

#### Reactive attributes for enums

A lot of attributes/properties of UI5 elements are enumerations. Usually, the official SAP docs itself gives a name to the "type" of the enum. For example, `ValueState` or `BreadcrumbsSeparatorStyle`.

Although you could define these properties as `String`, this would be poorly typed and lack semantics. For that reason, we create a `sealed trait`, with plenty of `case object`s to represent those.

They are all defined in the `be.doeraene.webcomponents.ui5.configkeys` package.

To implement a new enum, follow these steps:

1. create a sealed trait named the same way as in the sap docs
2. give this sealed trait a companion object, and fill it with one `case object` for each possible value
3. make the companion object extend `EnumerationString` (available in the same package)
4. Implement the `valueOf` and `allValues` abstract members
5. use the `AsStringCodec` in your reactive values

#### Events

Some UI5 components have custom events specific to them, usually with special type of emitted events.

All these events are represented as values of type `EventProp`. It takes a type parameter, which is the type of the values emitted by this event. This type has to be a subtype of `dom.Event`. Events from UI5 components are usually augmented with other properties. They are roughly documented, but most of the time you will have to trigger them manually and `dom.console.log` the emitted values to understand what they really are.

Some common patterns are:

- values have a `detail` field of a certain type. The library has a helper `HasDetail` trait, and in that case the complete type will look like `dom.Event & HasDetail[SomeOtherType]`. An example taken from the `Table` component is `val onSelectionChange = new EventProp[dom.Event & HasDetail[TableSelectionChangeDetail]]("selection-change")`.
- events with a more precise target type (when you need the precise type instead of just `HtmlElement`). This type is `EventWithPreciseTarget` and already extends `dom.Event`. An example from the `CheckBox` component is `val onChange: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("change")`
- a combination of the above (combined with `&`)

#### Slots

In the `slots` object, define a value for each of the slot this component has, except for the (always present) `default` one.

All the slots for a component are documented in the `Slots` section in the docs.

For example, the footer slot of the `Dialog` is defined as

```scala
val footer: Slot = new Slot("footer")
```
