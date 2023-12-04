import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        final long deltaTime = 1000;

        Planet Earth = new Planet(0, 0, 0, 0, 5.9722 * Math.pow(10, 24), 6.378137 * Math.pow(10, 6));
        Planet Moon = new Planet(0, 3.844 * Math.pow(10, 8), 1.023 * Math.pow(10, 3), 0, 7.346 * Math.pow(10, 22), 1.7381 * Math.pow(10, 3));

        FileWriter earth_out = new FileWriter("output_Earth.txt");
        FileWriter moon_out = new FileWriter("output_Moon.txt");

        ArrayList<Planet> bodies = new ArrayList<Planet>();
        bodies.add(Earth);
        bodies.add(Moon);

        for (int n = 1; n <= 2360592; ++n) {
            Planet.tickAcceleration(bodies);
            Planet.tickVelocity(bodies, deltaTime);
            Planet.tickPosition(bodies, deltaTime);
            
            earth_out.write(String.format("Tick %d: %s %n", n, Earth.toString()));
            moon_out.write(String.format("Tick %d: %s %n", n, Moon.toString()));
        }

        earth_out.close();
        moon_out.close();
    }
}
