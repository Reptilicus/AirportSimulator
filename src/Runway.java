
public class Runway {
	private int timeRemaining = 0;
	private int runwayTime;

	
	public Runway(int runwayTime) {
		this.runwayTime = runwayTime;
	}
	
	public void decrementTime() {
		timeRemaining--;
	}
	
	public boolean isBusy() {
		return (timeRemaining != 0);
	}
	
	public void useRunway() {
		timeRemaining = runwayTime;
	}
}
