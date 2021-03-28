# openrndr-js = OPENRNDR + Kotlin running in JavaScript

**Website/Demo:** :fireworks: https://xemantic.github.io/openrndr-js :sparkler:


## How to use it?

TL;RD this is a
[template GitHub project](https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/creating-a-repository-from-a-template),
so you can just start a new repository from this one.


### Install IntelliJ IDEA (recommended)

Not absolutely necessary, but highly recommended as the IDE is tailored to the whole toolchain.
Open source [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/) should
be good enough.


### Build modified OPENRNDR (won't be needed soon)

:information_source: All these changes will be incorporated into official OPENRNDR releases soon.
For now you have to do:

```shell
$ git clone git@github.com:morisil/openrndr.git
$ cd openrndr
$ git checkout openrndrColorAsKotlinMultiplatform
$ ./gradlew publishToMavenLocal -Prelease.version=0.4.0-SNAPSHOT
```

It will build the latest OPENRNDR version with Kotlin JS support for
`openrndr-math` and `openrndr-color` modules.


### Start the webdev server

```shell
$ ./gradlew run --continous
```

It will open your web app's [index.html](src/main/resources/index.html) in the browser and
any changes to the Kotlin code will refresh the page automatically. You can also
[run it from IntelliJ IDEA](https://kotlinlang.org/docs/dev-server-continuous-compilation.html).


## Publishing to GitHub pages

Check: https://pages.github.com/

If you used this project as a template for your own GitHub repository, then go to the `Settings`
and enable GitHub pages with `docs` folder as root. Then every time you successfully run:

```shell
$ ./gradlew build
```

It will also populate the `docs` folder with all the compiled web resources.
