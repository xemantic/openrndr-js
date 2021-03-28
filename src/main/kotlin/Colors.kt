import org.openrndr.math.Vector3
import org.openrndr.math.Vector4
import org.openrndr.math.max
import org.openrndr.math.min

// Note: also take a look what's possible with `openrndr-color`
// https://guide.openrndr.org/#/04_Drawing_basics/C03_Color

// We are going to represent color as Vector3 from openrndr-math, but we need to translate it
// to HTML/CSS world

// First let's define how to convert values of each RGB component. They are stored as values
// in range 0..1 inside Vector3.
fun toRgbInt(component: Double) = (component * 255.0).toInt()
fun toHex(component: Double) = toRgbInt(component).toString(16).padStart(2, '0')

// It's so nice to code in this more functional style, where function can be composed
// out of other functions, while not bothering with curly brackets and return statements.
// Here is another Kotlin syntactic sugar - something called extension property. It will
// provide virtual property to our vector representing it as hex string needed in HTML / CSS
val Vector3.hexColor: String
    get() = "#${toHex(this.x)}${toHex(this.y)}${toHex(this.z)}"

// Sometimes we will need CSS color like "rgba(0, 128, 255, .5)" here is so called extension
// function which will do it. It takes optional parameter for the alpha channel. If not specified
// it defaults to 1.
fun Vector3.toRgbaColor(alpha: Double = 1.0) =
    "rgba(${toRgbInt(this.x)},${toRgbInt(this.y)},${toRgbInt(this.z)},$alpha)"

// So for convenience we can do it again as a property
val Vector3.rgbaColor: String
    get() = this.toRgbaColor()

// But Vector4 can represent also the alpha channel in the lest component, so let's do it as well
val Vector4.rgbaColor: String
    get() = "rgba(${toRgbInt(this.x)},${toRgbInt(this.y)},${toRgbInt(this.z)},${this.z})"

// Note: you will find unit tests for all the code above in ColorsTest file (src/test/kotlin)


// Below is my favorite coloring function of spectral colors created by Alan Zucconi
// Website: www.alanzucconi.com
// Usually I use it directly in my GLSL shaders, but here I quickly rewrote it in OPENRNDR
// vector arithmetics.

fun spectralZucconi6(x: Double) =
    bump3y(c1 * (Vector3(x) - x1), y1) + bump3y(c2 * (Vector3(x) - x2), y2)

fun saturate(x: Vector3) = min(Vector3.ONE, max(Vector3.ZERO, x))

fun bump3y(x: Vector3, yoffset: Vector3) = (Vector3.ONE - x * x).let { saturate(it - yoffset) }

private val c1 = Vector3(3.54585104, 2.93225262, 2.41593945)
private val x1 = Vector3(0.69549072, 0.49228336, 0.27699880)
private val y1 = Vector3(0.02312639, 0.15225084, 0.52607955)

private val c2 = Vector3(3.90307140, 3.21182957, 3.96587128)
private val x2 = Vector3(0.11748627, 0.86755042, 0.66077860)
private val y2 = Vector3(0.84897130, 0.88445281, 0.73949448)


/*

// https://www.shadertoy.com/view/ls2Bz1

vec3 saturate (vec3 x)
{
    return min(vec3(1.,1.,1.), max(vec3(0.,0.,0.),x));
}

vec3 bump3y (vec3 x, vec3 yoffset)
{
	vec3 y = vec3(1.,1.,1.) - x * x;
	y = saturate(y-yoffset);
	return y;
}

vec3 spectral_zucconi6 (float w)
{
	// w: [400, 700]
	// x: [0,   1]
	float x = saturate((w - 400.0)/ 300.0);

	const vec3 c1 = vec3(3.54585104, 2.93225262, 2.41593945);
	const vec3 x1 = vec3(0.69549072, 0.49228336, 0.27699880);
	const vec3 y1 = vec3(0.02312639, 0.15225084, 0.52607955);

	const vec3 c2 = vec3(3.90307140, 3.21182957, 3.96587128);
	const vec3 x2 = vec3(0.11748627, 0.86755042, 0.66077860);
	const vec3 y2 = vec3(0.84897130, 0.88445281, 0.73949448);

	return
		bump3y(c1 * (x - x1), y1) +
		bump3y(c2 * (x - x2), y2) ;
}
 */
