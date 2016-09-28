import java.util.Queue;

public class AirportSimulate {
	private int clock;
	private int totalTime;
	private Queue<Integer> planesDeparting;
	private Queue<Integer> planesArriving;
	private BooleanSource arrival;
	private BooleanSource departure;
	private Averager arrivalWaitTimes;
	private Averager departureWaitTimes;
	
	public AirportSimulate(int time, double arrivalProbability, double departureProbability, int runwayTime) {
		totalTime = time;
		clock = 0;
		arrival = new BooleanSource(arrivalProbability);
		departure = new BooleanSource(departureProbability);
	}
	
	public void runSimulation() {
		while (clock < totalTime) {
			
		}
	}
}
