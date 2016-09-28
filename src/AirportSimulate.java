import java.util.LinkedList;
import java.util.Queue;

public class AirportSimulate {
	private int clock;
	private int runtime;
	private int crashTime;
	private int landingTime;
	private int takeOffTime;
	private int numPlanesCrashed;
	private Queue<Integer> planesDeparting = new LinkedList<Integer>();
	private Queue<Integer> planesArriving = new LinkedList<Integer>();
	private BooleanSource arrivalRate;
	private BooleanSource departureRate;
	private Averager arrivalWaitTimes = new Averager();
	private Averager departureWaitTimes = new Averager();
	private Runway runway;
	
	public AirportSimulate(double arrivalProbability, int landingTime, int crashTime,  double departureProbability, int takeOffTime, int time) {
		runtime = time;
		clock = 0;
		arrivalRate = new BooleanSource(arrivalProbability);
		departureRate = new BooleanSource(departureProbability);
		runway = new Runway(landingTime, takeOffTime);
		numPlanesCrashed = 0;
		this.crashTime = crashTime;
	}
	
	public String runSimulation() {
		String report = "";
		for (clock = 0; clock < runtime; clock++) {
			if (arrivalRate.query()) {
				planesArriving.add(clock);
			}
			if (departureRate.query()) {
				planesDeparting.add(clock);
			}
			// If the runway is open and a plane is waiting to land,
			// allow the plane to land.  Otherwise, if there is a
			// plane waiting to depart, let the plane take off.
			if ((!planesArriving.isEmpty()) && (!runway.isInUse())) {
				
				// If a plane has crashed, remove it without adding it
				// to the total wait time, and increment the number of
				// planes that crashed.
				if (clock - planesArriving.peek() > crashTime) {
					numPlanesCrashed++;
					planesArriving.remove();					
				} else {
					arrivalWaitTimes.addNumber(clock - planesArriving.remove());
					runway.land();
				}
			} else if ((!planesDeparting.isEmpty()) && (!runway.isInUse())) {
				departureWaitTimes.addNumber(clock - planesDeparting.remove());
				runway.takeOff();
			}
			
			if (runway.isInUse()) {
				runway.decrementTime();
			}
		}
		report = "Average arrival wait time: " + arrivalWaitTimes.average() +
				"\nAverage departure wait time: " + departureWaitTimes.average() + 
				"\nNumber of planes crashed: " + numPlanesCrashed + 
				"\nNumber of Arrivals: " + arrivalWaitTimes.howManyNumbers() +
				"\nNumber of Takeoffs: " + departureWaitTimes.howManyNumbers();
		return report;
	}
	
	
}
