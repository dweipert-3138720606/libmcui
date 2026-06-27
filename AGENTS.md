# Project Description

A kotlin library for UIs in minecraft mods.

This library holds classes for UI elements that can be constructed and out together manually,
or arranged in a template XML file with variable substitution.


# Verification

Before committing any changes, run:

```bash
gradle build
```

after that verify the code style with:

```bash
gradle ktlintCheck
```

and fix any violations.

Then run

```bash
gradle publishToMavenLocal
```

so the test mod can use the current version.
