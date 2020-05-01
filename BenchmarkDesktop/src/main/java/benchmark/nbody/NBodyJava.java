package benchmark.nbody;

/* The Computer Language Benchmarks Game
   https://salsa.debian.org/benchmarksgame-team/benchmarksgame/

   contributed by Mark C. Lewis
   modified slightly by Chad Whipkey
   further modified for the purpose of this paper by Stylianos Gakis
*/


public final class NBodyJava {
    public double benchmark() {
        return benchmark(500000);
    }

    public double benchmark(int n) {
        NBodySystemJava bodySystem = new NBodySystemJava();

        for (int i = 0; i < n; ++i) {
            bodySystem.advance(0.01);
        }
        return bodySystem.energy();
    }
}

final class NBodySystemJava {
    private BodyJava[] bodies;

    public NBodySystemJava() {
        bodies = new BodyJava[]{
                BodyJava.sun(),
                BodyJava.jupiter(),
                BodyJava.saturn(),
                BodyJava.uranus(),
                BodyJava.neptune()
        };

        double px = 0.0;
        double py = 0.0;
        double pz = 0.0;
        for (int i = 0; i < bodies.length; ++i) {
            px += bodies[i].vx * bodies[i].mass;
            py += bodies[i].vy * bodies[i].mass;
            pz += bodies[i].vz * bodies[i].mass;
        }
        bodies[0].offsetMomentum(px, py, pz);
    }

    public void advance(double dt) {
        for (int i = 0; i < bodies.length; ++i) {
            BodyJava iBody = bodies[i];
            for (int j = i + 1; j < bodies.length; ++j) {
                double dx = iBody.x - bodies[j].x;
                double dy = iBody.y - bodies[j].y;
                double dz = iBody.z - bodies[j].z;

                double dSquared = dx * dx + dy * dy + dz * dz;
                double distance = Math.sqrt(dSquared);
                double mag = dt / (dSquared * distance);

                iBody.vx -= dx * bodies[j].mass * mag;
                iBody.vy -= dy * bodies[j].mass * mag;
                iBody.vz -= dz * bodies[j].mass * mag;

                bodies[j].vx += dx * iBody.mass * mag;
                bodies[j].vy += dy * iBody.mass * mag;
                bodies[j].vz += dz * iBody.mass * mag;
            }
        }

        for (BodyJava body : bodies) {
            body.x += dt * body.vx;
            body.y += dt * body.vy;
            body.z += dt * body.vz;
        }
    }

    public double energy() {
        double dx, dy, dz, distance;
        double e = 0.0;

        for (int i = 0; i < bodies.length; ++i) {
            BodyJava iBody = bodies[i];
            e += 0.5 * iBody.mass *
                    (iBody.vx * iBody.vx
                            + iBody.vy * iBody.vy
                            + iBody.vz * iBody.vz);

            for (int j = i + 1; j < bodies.length; ++j) {
                BodyJava jBody = bodies[j];
                dx = iBody.x - jBody.x;
                dy = iBody.y - jBody.y;
                dz = iBody.z - jBody.z;

                distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
                e -= (iBody.mass * jBody.mass) / distance;
            }
        }
        return e;
    }
}


final class BodyJava {
    static final double PI = 3.141592653589793;
    static final double SOLAR_MASS = 4 * PI * PI;
    static final double DAYS_PER_YEAR = 365.24;

    public double x, y, z, vx, vy, vz, mass;

    public BodyJava() {
    }

    static BodyJava jupiter() {
        BodyJava p = new BodyJava();
        p.x = 4.84143144246472090e+00;
        p.y = -1.16032004402742839e+00;
        p.z = -1.03622044471123109e-01;
        p.vx = 1.66007664274403694e-03 * DAYS_PER_YEAR;
        p.vy = 7.69901118419740425e-03 * DAYS_PER_YEAR;
        p.vz = -6.90460016972063023e-05 * DAYS_PER_YEAR;
        p.mass = 9.54791938424326609e-04 * SOLAR_MASS;
        return p;
    }

    static BodyJava saturn() {
        BodyJava p = new BodyJava();
        p.x = 8.34336671824457987e+00;
        p.y = 4.12479856412430479e+00;
        p.z = -4.03523417114321381e-01;
        p.vx = -2.76742510726862411e-03 * DAYS_PER_YEAR;
        p.vy = 4.99852801234917238e-03 * DAYS_PER_YEAR;
        p.vz = 2.30417297573763929e-05 * DAYS_PER_YEAR;
        p.mass = 2.85885980666130812e-04 * SOLAR_MASS;
        return p;
    }

    static BodyJava uranus() {
        BodyJava p = new BodyJava();
        p.x = 1.28943695621391310e+01;
        p.y = -1.51111514016986312e+01;
        p.z = -2.23307578892655734e-01;
        p.vx = 2.96460137564761618e-03 * DAYS_PER_YEAR;
        p.vy = 2.37847173959480950e-03 * DAYS_PER_YEAR;
        p.vz = -2.96589568540237556e-05 * DAYS_PER_YEAR;
        p.mass = 4.36624404335156298e-05 * SOLAR_MASS;
        return p;
    }

    static BodyJava neptune() {
        BodyJava p = new BodyJava();
        p.x = 1.53796971148509165e+01;
        p.y = -2.59193146099879641e+01;
        p.z = 1.79258772950371181e-01;
        p.vx = 2.68067772490389322e-03 * DAYS_PER_YEAR;
        p.vy = 1.62824170038242295e-03 * DAYS_PER_YEAR;
        p.vz = -9.51592254519715870e-05 * DAYS_PER_YEAR;
        p.mass = 5.15138902046611451e-05 * SOLAR_MASS;
        return p;
    }

    static BodyJava sun() {
        BodyJava p = new BodyJava();
        p.mass = SOLAR_MASS;
        return p;
    }

    BodyJava offsetMomentum(double px, double py, double pz) {
        vx = -px / SOLAR_MASS;
        vy = -py / SOLAR_MASS;
        vz = -pz / SOLAR_MASS;
        return this;
    }
}
