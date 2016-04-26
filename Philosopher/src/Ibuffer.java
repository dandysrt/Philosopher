
public interface Ibuffer {
	enum State {thinking, hungry, eating };
	
	public void set(State value, int i) throws InterruptedException;

	public State get( int i ) throws InterruptedException;
}
