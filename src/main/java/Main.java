import com.mower.Configuration;
import com.mower.Simulator;

public class Main {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration( Main.class.getResourceAsStream("input"));
        Simulator simulator = new Simulator(configuration);
        simulator.simulate();

    }
}
