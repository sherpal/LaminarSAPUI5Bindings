# Version 2.0 migration

This file contains information about the migration from SAP versions 1.x to SAP version 2.0. We tried our best to not disrupt your Scala source files (only deprecation warning will be emitted in a lot of cases), but some things could not be moved automatically due to a consistent change in the behaviour.

> To actively see the deprecation messages when compiling, you need to add `scalacOptions += "-deprecation"` in your `build.sbt` file.
> 
> If you furthermore wants them to be compilation errors, so that you are forced to tackle them, you can add `scalacOptions += "-Xfatal-warnings"` as well

For most changes actually *requiring* you to make a change, you will have a compile error with a message explaning what you need to do. For comprehensive understanding, though, we refer to [SAP's migration guide](https://sap.github.io/ui5-webcomponents/docs/migration-guides/to-version-2).

Despite that, some changes live outside of these compiler messages, and we highlight them below. **Please make sure to review them!**

## 🚨 Breaking changes requiring your attention

1. Table has changed, and requires a new npm package import. If you were using the `Table` component, you need to add this extra package import in your `package.json`: `"@ui5/webcomponents-compat": "2.0.1"`. Also, since there will be a new `Table`, we moved the current Table implementation inside a `compat` object. This will be notified via deprecation notice, unless you skipped 2.0.0 and went directly higher, then you will end up with the new one "by mistake".
2. The default value of wrapping types has been switch. if you want to have word wrapping, you now need to actively add `_.wrappingType := WrappingType.None`
3. In ComboBox with groups, grouping is now done via nesting of items inside the groups, rather than a flat structure

