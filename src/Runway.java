
public class Runway {
	private int timeRemaining = 0;
	private int landingTime;
	private int takeOffTime;

	
	public Runway(int landingTime, int takeOffTime) {
		this.landingTime = landingTime;
		this.takeOffTime = takeOffTime;
	}
	
	public void decrementTime() {
		timeRemaining--;
	}
	
	public boolean isInUse() {
		return (timeRemaining != 0);
	}
	
	public void land() {
		timeRemaining = landingTime;
	}
	
	public void takeOff() {
		timeRemaining = takeOffTime;
	}
}
