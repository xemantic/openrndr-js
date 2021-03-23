plugins {
    kotlin("js") version "1.4.31"
}

group = "org.openrndr"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal() // needed for now to load locally build openrndr-math
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-js"))
    testImplementation("io.kotest:kotest-assertions-core:4.4.3")
    // TODO I still need to figure out why -js suffix is needed here, it shouldn't I guess some metadata is not propagated
    implementation("org.openrndr:openrndr-math-js:0.3.33-dev.664+openrndrMathAsKotlinMultiplatformLib.0ee960e")
}

kotlin {
    js(IR) {
        browser {
            binaries.executable()
            webpackTask {
             //   cssSupport.enabled = true
            }
            runTask {
              //  cssSupport.enabled = true
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                //    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
}
