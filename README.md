# Laminar bindings for SAP ui5 web-components library

This repository contains [Laminar](https://laminar.dev/) bindings for [SAP ui5 web-components](https://sap.github.io/ui5-webcomponents/).

Web components are a powerful technologies to create near-native html elements. Laminar, for its part, is a Scala-js framework/library to manipulate the dom.

## Disclaimer

The contents of this repository are (currently) a clone of the contents of bindings that I'm using in a private repository. I'm filling them as I go, so they are not complete, but they will eventually be. At which point I might decide to make it a proper library.

## How to use

These bindings are barely "facades" types for the official ui5 library. They *won't* work if you don't handle that npm dependency on your own project. You will need the following imports in your `package.json` (or equivalent tool such as scala-js-bundler):

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

## How to read the source code

Every component is implemented as an object with the following:

- a `RawElement` (js-native) trait that defines methods that the underlying DOM element will have
- a `RawElement` object only there to actually import the library
- a few reactive html attributes specific to that component
- a `slots` object that contains descriptions of the slots that the component can have. They allow syntax such as `Input(_.slots.valueStateMessage := span("Hello"))`.
- an `events` object containing specific events that the component can emit.
- an `apply` method to build the component (following the [example from the Laminar website](https://laminar.dev/examples/web-components), they take functions from the object to mods)
- some components will have links to other tightly coupled components (for example, the `TabContainer` has a `tab` link to the `Tab` component). They allow syntax such as `TabContainer(_.tab := ???)`.

Every object is named after the corresponding ui5 web-component, with the notable exception of `List` which is called `UList` to not interfere with the built-in Scala `List` type.
