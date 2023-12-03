import java.util.ArrayList;

public class Planet {
    private double x;
    private double y; 
    private double x_vel;
    private double y_vel;
    private double x_accel;
    private double y_accel;
    private int radius;
    private int mass;

    public Planet(double x, double y, double x_vel, double y_vel, int mass, int radius) {
        this.x = x;
        this.y = y; 
        this.x_vel = x_vel;
        this.y_vel = y_vel;
        this.x_accel = 0;
        this.y_accel = 0;
        this.mass = mass;
        this.radius = radius;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    private void gravCalc(Planet body) {
        double distance = Math.sqrt((this.x - body.x) * (this.x - body.x) + (this.y - body.y) * (this.y - body.y));
        double cos = Math.abs(this.x - body.x) / distance;
        double sin = Math.abs(this.y - body.y) / distance;
        double total_accel;

        if (distance > this.radius + body.radius) {
            total_accel = this.mass * body.mass / (distance*distance);
        } else {
            total_accel = this.mass * body.mass / ((this.radius + body.radius)*(this.radius + body.radius));
        }

        if (this.x < body.x) {
            this.x_accel = total_accel / cos;
        } else if (this.x > body.x) {
            this.x_accel = -total_accel / cos;
        }

        if (this.y < body.y) {
            this.y_accel = total_accel / sin;
        } else if (this.y > body.y) {
            this.y_accel = -total_accel / sin;
        }
    }

    public static void tickAcceleration(ArrayList<Planet> bodies) {
        for (Planet planet1 : bodies) {
            for (Planet planet2 : bodies) {
                planet1.gravCalc(planet2);
            }
        }
    }

    public static void tickVelocity(ArrayList<Planet> bodies) {
        for (Planet planet : bodies) {
            planet.x_vel += planet.x_accel;
            planet.y_vel += planet.y_accel;
        }
    }

    public static void tickPosition(ArrayList<Planet> bodies, long deltaTime) {
        for (Planet planet : bodies) {
            planet.x += planet.x_vel * (double) deltaTime / 1000.0;
            planet.y += planet.y_vel * (double) deltaTime / 1000.0;
        }
    }

    public String toString() {
        return String.format("position: (%6.3f, %6.3f) || velocity: (%6.3f, %6.3f) || acceleration: (%6.3f, %6.3f)", x, y, x_vel, y_vel, x_accel, y_accel);
    }
}
