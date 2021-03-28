import org.openrndr.math.mod
import kotlin.math.sin

// I put lots of comments in case you are new to Kotlin, mostly referring
// typical JavaScript idioms.
// Note that Kotlin does not require semicolons - no ; anymore at the end of line

/**
 * Returns sine wave always normalized to 0..1 range.
 *
 * @param phase the phase of the wave, usually used with something related
 *              time or space.
 */
fun wave(phase: Double) = (sin(phase) + 1.0) / 2.0

// In Kotlin you can define new functions like the wave above, without return
// statement. It's very convenient for functional programming style. The
// documentation is optional, however if kept, it will be automatically
// translated to documentation of your project, which might be important
// if it's a library you are going to reuse or offer to others.

// Note that we have to provide the type of phase parameter. Double is a default
// Kotlin type for expressing floating point number and it follows the parameter
// name instead of preceding it, like it is in Java and C derived syntax.

// If you are familiar with JS arrow functions, the wave function above
// would be an equivalent here, except the actual lambda expression in Kotlin would
// be written like this:

val wave2 = { phase: Double -> (sin(phase) + 1.0) / 2.0 }

// Not necessary here. It has more use when lambda is supplied as an argument
// which happens quite often in Kotlin. The idiom you might know from JS:
//
// collection.forEach(element => { doSomething(element) })
//
// In Kotlin would become:
//
// collection.forEach({ element -> doSomething(element) })
//
// But there is more syntactic sugar. If it is the last parameter of the
// function, then you can skip brackets:
//
// collection.forEach { element -> doSomething(element) }
//
// Or even go for more Kotlin idiomatic way:
//
// collection.forEach { doSomething(it) }
//
// "it" resolves to the current lambda argument


/**
 * Sinusoidal oscillation depending on current time.
 *
 * @param tempo the tempo of oscillation.
 * @param maxValue the maximal value to oscillate to.
 */
fun oscillate(tempo: Double, maxValue: Double) = wave(time * tempo) * maxValue

// it's possible to overload the function like this:
fun oscillate(tempo: Double, maxValue: Int) = oscillate(tempo, maxValue.toDouble())

// here is a function enclosing the code which will be run once per animation frame
/**
 * Draws on the screen.
 * Note: this function is being called from the [Painter] defined in Engine.kt file.
 */
fun draw() {
    // Here we are using the pure JS canvas API:
    // https://developer.mozilla.org/en-US/docs/Web/API/Canvas_API
    // normally in JS you would call canvas.something(), with Kotlin's "with"
    // the "canvas." can be skipped completely
    with (canvas) {
        beginPath()
        moveTo(oscillate(1.0, width), oscillate(1.1, height))
        lineWidth = 2.0
        // see comments in Colors.kt to check in details what's happening here
        strokeStyle = spectralZucconi6(mod(wave(time), 1.0)).toRgbaColor(.2)
        lineTo(oscillate(1.2, width), oscillate(1.3, height))
        stroke()
        closePath()
    }
}
