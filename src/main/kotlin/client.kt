import kotlinx.browser.document
import kotlinx.browser.window
import org.openrndr.math.mod
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.math.floor
import kotlin.math.sin

fun wave(phase: Double) = (sin(phase) + 1.0) / 2.0

fun oscillate(offset: Double, range: Int) = wave(time * offset) * range

fun draw() {
    // drawer is an instance of CanvasRenderingContext2D
    with (drawer) {
        beginPath()
        moveTo(oscillate(1.0, width), oscillate(1.1, height))
        lineWidth = 2.0
        strokeStyle = spectralZucconi6(mod(wave(time), 1.0)).toRgbaColor(.2)
        lineTo(oscillate(1.2, width), oscillate(1.3, height))
        stroke()
        closePath()
    }
}


// The code below is pretty standard, so I guess in the future it will be a common openrndr-js lib
// here it is just abstracting canvas initialization, as if it is also happening in p5.js



fun main() {
    window.onload = {
        painter = Painter { draw() }
        true
    }
}


lateinit var painter: Painter
val drawer: CanvasRenderingContext2D get() = painter.drawer

val time: Double get() = painter.time
val width: Int get() = painter.width
val height: Int get() = painter.height


class Painter(val backgroundFillStyle: String = "black", private val drawFunction: () -> Unit) {

    val canvas: HTMLCanvasElement = document.body?.appendChild(
        document.createElement("canvas")
    ) as HTMLCanvasElement

    val drawer = canvas.getContext("2d") as CanvasRenderingContext2D

    init {
        with(canvas.style) {
            position = "fixed"
            width = "100vw"
            height = "100vh"
            top = "0"
            left = "0"
            zIndex = "-10"
        }
        window.requestAnimationFrame { render() }
    }

    var time = 0.0
        private set
    var width: Int = 0
        private set
    var height: Int = 0
        private set

    private var cssWidth: Int = 0
    private var cssHeight: Int = 0

    private fun render() {
        window.requestAnimationFrame { render() }
        if (maybeResize()) {
            resize()
        }
        time = window.performance.now() / 1000
        drawFunction()
    }

    private fun maybeResize() =
        (cssWidth != canvas.clientWidth) || (cssHeight != canvas.clientHeight)

    private fun resize() {
        println("resize")
        val pixelRatio = window.devicePixelRatio
        cssWidth   = canvas.clientWidth
        cssHeight  = canvas.clientHeight
        width      = floor(cssWidth  * pixelRatio).toInt()
        height     = floor(cssHeight * pixelRatio).toInt()
        canvas.width = width
        canvas.height = height
        drawer.fillStyle = backgroundFillStyle
        drawer.fillRect(0.0, 0.0, width.toDouble(), height.toDouble())
    }

}
