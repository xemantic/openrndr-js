import io.kotest.matchers.shouldBe
import org.openrndr.math.Vector3
import org.openrndr.math.Vector4
import kotlin.test.Test

/**
 * You can test pieces of logic here. They will run as JS in ChromeHeadless.
 */
class ColorsTest {

    @Test
    fun toRgbInt_Double_shouldConvertVectorComponentToInt() {
        toRgbInt(0.0) shouldBe 0
        toRgbInt(1.0) shouldBe 255
        toRgbInt(.5) shouldBe 127
    }

    @Test
    fun toHex_Double_shouldConvertVectorComponentToInt() {
        toHex(0.0) shouldBe "00"
        toHex(1.0) shouldBe "ff"
        toHex(.5) shouldBe "7f"
    }

    @Test
    fun hexColor_Vector3Property_shouldBeHexColor() {
        Vector3(0.0, 0.5, 1.0).hexColor shouldBe "#007fff"
    }

    @Test
    fun toRgbaColor_Vector3Extension_shouldBeRgbaColor() {
        Vector3(0.0, 0.5, 1.0).toRgbaColor() shouldBe "rgba(0,127,255,1)"
        Vector3.ONE.toRgbaColor(.42) shouldBe "rgba(255,255,255,0.42)"
    }

    @Test
    fun rgbaColor_Vector3Extension_shouldBeRgbaColor() {
        Vector3(0.0, 0.5, 1.0).rgbaColor shouldBe "rgba(0,127,255,1)"
        Vector3.ONE.rgbaColor shouldBe "rgba(255,255,255,1)"
        Vector3.ZERO.rgbaColor shouldBe "rgba(0,0,0,1)"
    }

    @Test
    fun rgbaColor_Vector4Extension_shouldBeRgbaColor() {
        Vector4(0.0, 0.5, 1.0, 1.0).rgbaColor shouldBe "rgba(0,127,255,1)"
        Vector4.ONE.rgbaColor shouldBe "rgba(255,255,255,1)"
        Vector4.ZERO.rgbaColor shouldBe "rgba(0,0,0,0)"
    }

}
