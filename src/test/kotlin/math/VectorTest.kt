package math

import org.junit.Test
import kotlin.test.assertEquals

internal class VectorTest {

    @Test
    fun plus() {
        assertEquals(Vector(2.5, 0.0), Vector(1.5, -.5) + Vector(1f, .5f))
    }

    @Test
    fun plusAssign() {
        val v = Vector(-2, 3, 1)
        v += Vector(1, 1, -1)
        assertEquals(Vector(-1, 4, 0), v)
    }

    @Test
    fun minus() {
        assertEquals(Vector(.5, -1.0), Vector(1.5, -.5) - Vector(1f, .5f))
    }

    @Test
    fun minusAssign() {
        val v = Vector(-2, 3, 1)
        v -= Vector(1, 1, -1)
        assertEquals(Vector(-3, 2, 2), v)
    }

    @Test
    fun times() {
        assertEquals(Vector(3, -2), Vector(1.5, -1.0) * 2)
    }

    @Test
    fun timesAssign() {
        val v = Vector(-2, 3, 1)
        v *= 3
        assertEquals(Vector(-6, 9, 3), v)
    }

    @Test
    fun div() {
        assertEquals(Vector(5, -2), Vector(10, -4) / 2)
    }

    @Test
    fun divAssign() {
        val v = Vector(-2, 3, 1)
        v /= 2
        assertEquals(Vector(-1.0, 1.5, .5), v)
    }

    @Test
    fun isNullVector() {
        assert(Vector(0, 0, 0, 0, 0).isNullVector())
        assert(!Vector(0, 0, 0, 1, 0).isNullVector())
        assert(!Vector(0, 3, 0, 1, 2).isNullVector())
    }

    @Test
    fun isUnitVector() {
        assert(!Vector(0, 0, 0, 0, 0).isUnitVector())
        assert(Vector(0, 1).isUnitVector())
        assert(Vector(0, 0, 0, 1, 0).isUnitVector())
        assert(!Vector(0, 3, 0, 1, 2).isUnitVector())
    }

    @Test
    fun setDimension() {
        val v = Vector(1, 0)
        v.setDimension(3)
        assertEquals(3, v.getDimension())
        v.setDimension(1)
        assertEquals(1, v.getDimension())
    }

    @Test
    fun toDiagonalMatrix() {
        val v1 = Vector(3, 1, 2)
        assertEquals(v1.toDiagonalMatrix(), Matrix(3, 3, 0, 0, 0, 1, 0, 0, 0, 2))
        val v2 = Vector(2, 2, 2, 2)
        assertEquals(v2.toDiagonalMatrix(), Matrix(4, 4, 2.0))
    }
}