import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        final long deltaTime = 100;

        Planet Earth = new Planet(0, 0, 1, 0, 10, 20);
        Planet Moon = new Planet(0, 1000, -2, 0, 2, 5);

        FileWriter earth_out = new FileWriter("output_Earth.txt");
        FileWriter moon_out = new FileWriter("output_Moon.txt");

        ArrayList<Planet> bodies = new ArrayList<Planet>();
        bodies.add(Earth);
        bodies.add(Moon);

        for (int n = 1; n <= 100; ++n) {
            Planet.tickAcceleration(bodies);
            Planet.tickVelocity(bodies);
            Planet.tickPosition(bodies, deltaTime);
            
            earth_out.write(String.format("Tick %d: %s %n", n, Earth.toString()));
            moon_out.write(String.format("Tick %d: %s %n", n, Moon.toString()));

            Thread.sleep(deltaTime);
        }

        earth_out.close();
        moon_out.close();
    }
}