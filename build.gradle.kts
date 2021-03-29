plugins {
    kotlin("js") version "1.4.31" // it's good to always put the latest Kotlin version here
}

group = "org.openrndr.demo"                // you can change it to reflect your organization and project
version = "1.0-SNAPSHOT"

val openrndrVersion = "0.3.47-rc.5"     // It's locally built SNAPSHOT for now, see the README.md, will be replaced with the official version soon

repositories {                             // where all the software libs are coming from - "NPM of Java/Kotlin world"
    mavenLocal()                           // needed for now to add locally built openrndr-math, will be gone soon
    mavenCentral()                         // mavenCentral - the only one which will work in the future
    jcenter()                              // as jcenter will is phased out this year
    maven(url = "https://dl.bintray.com/openrndr/openrndr") // for now we might need some snapshot/RC versions from here
    // uncomment the next one if you want to use kotlinx.html, see https://github.com/Kotlin/kotlinx.html
    //maven { url = uri("https://dl.bintray.com/kotlin/kotlinx") }
}

/*
  Add your dependencies here. Either Kotlin libs which are already prepared for Kotlin MPP/JS
  (the number of them is growing), or NPM dependencies.

  See full documentation the documentation here:

  https://kotlinlang.org/docs/using-packages-from-npm.html

 */
dependencies {
    implementation("org.openrndr:openrndr-math-js:$openrndrVersion") // It's locally built SNAPSHOT for now, see README, will be official version soon
    implementation("org.openrndr:openrndr-color-js:$openrndrVersion")

    // uncomment the next one if you want to use kotlinx.html, see https://github.com/Kotlin/kotlinx.html
    //implementation("org.jetbrains.kotlinx:kotlinx-html:0.7.2")

    testImplementation(kotlin("test-js"))
    testImplementation("io.kotest:kotest-assertions-core:4.4.3")
}

kotlin {
    js {
        browser {
            binaries.executable()

            /*
             the distribution section is here because this project is also set up to serve produced
             HTML using gh-pages:

             https://docs.github.com/en/github/working-with-github-pages/creating-a-github-pages-site

             You might also want it on your GitHub project. gh-pages is limited to docs/ dir
             or project root. If you don't need gh-pages, you can delete the whole distribution
             section and the result of the compilation will go into built/distributions/ folder.
             */
            @Suppress("EXPERIMENTAL_API_USAGE")
            distribution {
                directory = file("$projectDir/docs/")
            }

            // uncomment this if you need CSS support
            /*
            webpackTask {
                cssSupport.enabled = true
            }
            runTask {
                cssSupport.enabled = true
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
            */
        }
    }
}
