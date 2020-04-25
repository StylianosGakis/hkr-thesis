package se.stylianosgakis.benchmarkapplication.benchmark.nbody

import kotlin.math.sqrt

class NBodyKotlin {
    fun benchmark(): Double {
        return benchmark(500000)
    }

    fun benchmark(n: Int): Double {
        val bodySystem = NBodySystemKotlin()

        for (i in 0 until n) {
            bodySystem.advance(0.01)
        }
        return bodySystem.energy()
    }
}

class NBodySystemKotlin {
    private val bodies: Array<BodyKotlin> = arrayOf(
        sun,
        jupiter,
        saturn,
        uranus,
        neptune
    )

    init {
        var px = 0.0
        var py = 0.0
        var pz = 0.0
        for (body in bodies) {
            px += body.vx * body.mass
            py += body.vy * body.mass
            pz += body.vz * body.mass
        }
        bodies.first().offsetMomentum(px, py, pz)
    }

    fun advance(dt: Double) {
        for (i in bodies.indices) {
            val currentBody = bodies[i]
            for (j in (i.plus(1)) until bodies.size) {
                val innerBody = bodies[j]
                val dx = currentBody.x - innerBody.x
                val dy = currentBody.y - innerBody.y
                val dz = currentBody.z - innerBody.z
                val dSquared = (dx * dx) + (dy * dy) + (dz * dz)
                val distance = sqrt(dSquared)
                val magnitude = dt / (dSquared * distance)

                currentBody.vx -= dx * innerBody.mass * magnitude
                currentBody.vy -= dy * innerBody.mass * magnitude
                currentBody.vz -= dz * innerBody.mass * magnitude

                innerBody.vx += dx * currentBody.mass * magnitude
                innerBody.vy += dy * currentBody.mass * magnitude
                innerBody.vz += dz * currentBody.mass * magnitude
            }
        }

        for (body in bodies) {
            body.x += dt * body.vx
            body.y += dt * body.vy
            body.z += dt * body.vz
        }
    }

    fun energy(): Double {
        var e = 0.0
        var dx: Double
        var dy: Double
        var dz: Double
        var distance: Double

        for (i in bodies.indices) {
            val currentBody = bodies[i]
            e += 0.5 * currentBody.mass * (currentBody.vx * currentBody.vx +
                    currentBody.vy * currentBody.vy +
                    currentBody.vz * currentBody.vz)

            for (j in (i.plus(1)) until bodies.size) {
                val innerBody = bodies[j]
                dx = currentBody.x - innerBody.x
                dy = currentBody.y - innerBody.y
                dz = currentBody.z - innerBody.z

                distance = sqrt((dx * dx) + (dy * dy) + (dz * dz))
                e -= (currentBody.mass * innerBody.mass) / distance
            }
        }
        return e
    }
}

const val PI: Double = 3.141592653589793
const val SOLAR_MASS: Double = 4 * PI * PI
const val DAYS_PER_YEAR: Double = 365.24

data class BodyKotlin(
    var x: Double = 0.0,
    var y: Double = 0.0,
    var z: Double = 0.0,
    var vx: Double = 0.0,
    var vy: Double = 0.0,
    var vz: Double = 0.0,
    var mass: Double
)

val jupiter = BodyKotlin(
    x = 4.84143144246472090e+00,
    y = -1.16032004402742839e+00,
    z = -1.03622044471123109e-01,
    vx = 1.66007664274403694e-03 * DAYS_PER_YEAR,
    vy = 7.69901118419740425e-03 * DAYS_PER_YEAR,
    vz = -6.90460016972063023e-05 * DAYS_PER_YEAR,
    mass = 9.54791938424326609e-04 * SOLAR_MASS
)
val saturn = BodyKotlin(
    x = 8.34336671824457987e+00,
    y = 4.12479856412430479e+00,
    z = -4.03523417114321381e-01,
    vx = -2.76742510726862411e-03 * DAYS_PER_YEAR,
    vy = 4.99852801234917238e-03 * DAYS_PER_YEAR,
    vz = 2.30417297573763929e-05 * DAYS_PER_YEAR,
    mass = 2.85885980666130812e-04 * SOLAR_MASS
)
val uranus = BodyKotlin(
    x = 1.28943695621391310e+01,
    y = -1.51111514016986312e+01,
    z = -2.23307578892655734e-01,
    vx = 2.96460137564761618e-03 * DAYS_PER_YEAR,
    vy = 2.37847173959480950e-03 * DAYS_PER_YEAR,
    vz = -2.96589568540237556e-05 * DAYS_PER_YEAR,
    mass = 4.36624404335156298e-05 * SOLAR_MASS
)
val neptune = BodyKotlin(
    x = 1.53796971148509165e+01,
    y = -2.59193146099879641e+01,
    z = 1.79258772950371181e-01,
    vx = 2.68067772490389322e-03 * DAYS_PER_YEAR,
    vy = 1.62824170038242295e-03 * DAYS_PER_YEAR,
    vz = -9.51592254519715870e-05 * DAYS_PER_YEAR,
    mass = 5.15138902046611451e-05 * SOLAR_MASS
)
val sun = BodyKotlin(
    mass = SOLAR_MASS
)

fun BodyKotlin.offsetMomentum(px: Double, py: Double, pz: Double) {
    vx = -px / SOLAR_MASS
    vy = -py / SOLAR_MASS
    vz = -pz / SOLAR_MASS
}
