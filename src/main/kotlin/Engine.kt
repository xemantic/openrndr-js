import kotlinx.browser.document
import kotlinx.browser.window
import org.openrndr.math.Vector2
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.math.floor
import kotlin.math.min

// The code below is pretty standard, so I guess in the future it will
// become something like a common openrndr-js framework. But this the whole
// OPENRNDR community has to think through. The "Engine" here is just
// abstracting canvas initialization and drawing, as if it is happening in p5.js, etc.


val FLIP_Y = Vector2(1.0, -1.0)

val resolution: Vector2 get() = Vector2(width.toDouble(), height.toDouble())

val minDimension: Double get() = min(width.toDouble(), height.toDouble())

fun toScreenCoord(coord: Vector2) = (coord * FLIP_Y * minDimension + resolution) / 2.0

fun CanvasRenderingContext2D.moveTo(coord: Vector2) = this.moveTo(coord.x, coord.y)
fun CanvasRenderingContext2D.lineTo(coord: Vector2) = this.lineTo(coord.x, coord.y)
fun CanvasRenderingContext2D.clearScreen(fillStyle: String) {
  this.fillStyle = fillStyle
  this.fillRect(0.0, 0.0, width.toDouble(), height.toDouble())
}

fun main() {
  window.onload = {
    painter = Painter { draw() }
    true
  }
}

lateinit var painter: Painter
val canvas: CanvasRenderingContext2D get() = painter.canvas2d

val time: Double get() = painter.time
val width: Int get() = painter.width
val height: Int get() = painter.height


class Painter(
  val backgroundFillStyle: String = "black",
  private val drawFunction: () -> Unit
) {

  @Suppress("MemberVisibilityCanBePrivate")
  val canvas: HTMLCanvasElement = document.body?.appendChild(
    document.createElement("canvas")
  ) as HTMLCanvasElement

  val canvas2d = canvas.getContext("2d") as CanvasRenderingContext2D

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
    val pixelRatio = window.devicePixelRatio
    cssWidth = canvas.clientWidth
    cssHeight = canvas.clientHeight
    width = floor(cssWidth * pixelRatio).toInt()
    height = floor(cssHeight * pixelRatio).toInt()
    canvas.width = width
    canvas.height = height
    canvas2d.fillStyle = backgroundFillStyle
    canvas2d.fillRect(0.0, 0.0, width.toDouble(), height.toDouble())
  }

}
