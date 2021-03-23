import io.kotest.matchers.shouldBe
import org.openrndr.math.Vector2
import kotlin.test.Ignore
import kotlin.test.Test

/**
 * You can test pieces of logic here. They will run as JS in ChromeHeadless.
 */
class OpenrndrTest {

    @Test
    fun twoPlusTwoEqualsFour() {
        (Vector2(2.0) + Vector2(2.0)) shouldBe Vector2(4.0)
    }

    @Test
    @Ignore // un-ignore to see how i fails
    fun twoPlusTwoEqualsFive() {
        (Vector2(2.0) + Vector2(2.0)) shouldBe Vector2(5.0)
    }

}
