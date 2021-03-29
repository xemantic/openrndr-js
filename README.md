# openrndr-js = OPENRNDR + Kotlin running in JavaScript

**Website/Demo:** :fireworks: https://xemantic.github.io/openrndr-js :sparkler:

:information_source: Here is the [code behind the Demo](src/main/kotlin/client.kt) with
lots of comments which might be useful for someone coming from from the JavaScript ecosystem.


## How to use it?

TL;RD this is a
[template GitHub project](https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/creating-a-repository-from-a-template),
so you can just start a new repository from this one.


### Install IntelliJ IDEA (recommended)

Not absolutely necessary, but highly recommended as the IDE is tailored to the whole toolchain.
Open source [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/) should
be good enough.


### Use this project as a template

Just click the green "Use this template" button above and then clone your fresh repository.


### Start the webdev server

```shell
$ cd your-repository-name
$ ./gradlew run --continous
```

It will open your web app's [index.html](src/main/resources/index.html) in the browser and
any changes to the Kotlin code will refresh the page automatically.

:information_source: You can also
[run it from IntelliJ IDEA](https://kotlinlang.org/docs/dev-server-continuous-compilation.html).


### Testing changes

Just do some edits in [src/main/kotlin/client.kt](src/main/kotlin/client.kt) and check
the outcome.


## Customizing openrndr-js to your needs

If you used this repository as a template, you might consider:

 * Changing `rootProject.name` in [settings.gradle.kts](settings.gradle.kts) to the name of your repository.
   * :warning: the change of generated JS script name in [src/main/resources/index.html](src/main/resources/index.html)
     should follow.
 * Changing `group` in [build.gradle.ks](build.gradle.kts) to something reflecting your organization
 * You can uncomment additional futures you want to use in [build.gradle.ks](build.gradle.kts)

Go through the source code. I tried to document it a lot for providing all the necessary
context. For the overview of Kotlin JS check:

https://kotlinlang.org/docs/js-overview.html

If you are not familiar with OPENRNDR thant look at the Guide:

https://guide.openrndr.org/


## Publishing to GitHub pages

Check: https://pages.github.com/

If you used this project as a template for your own GitHub repository, then go to the `Settings`
and enable GitHub pages with `docs` folder as root. Then every time you successfully run:

```shell
$ ./gradlew build
```

It will also populate the `docs` folder with all the compiled web resources.
