
public class SynchronizedBuffer implements Ibuffer{
	
	private State[] currentState = new State[5];
	 
	public SynchronizedBuffer(){
		init();
	}

	void init(){
		for (State element : currentState ){
			element  = State.thinking;
		}
	}

	public synchronized void set(State value, int i) throws InterruptedException {
		switch (value){
			case thinking:
				setThink(i);
			break;
			case hungry:
				setHungry(i);
			break;
			case eating:
				setEating(i);
			break;
			default:
				setThink(i);
			break;
		}
	}

	@Override
	public synchronized State get( int i ) throws InterruptedException {
		return currentState[i];
	}
	
	private void setThink( int i ){
		currentState[i] = State.thinking;
		System.out.printf("\n%s%d %s\n", "Philosopher", i + 1, "is thinking.");
	}
	
	private synchronized void setEating( int i ) throws InterruptedException {
		int r = (i + 4) % 5;
		int l = (i + 1) % 5;
		
		while ( currentState[r] == State.eating || currentState [l] == State.eating || 
				currentState[i] == State.eating){
			System.out.printf("\n%s%d %s%d %s%d%s\n", "Philosopher", i + 1 ,
					"is waiting on Philosopher", l + 1, "and Philosopher", 
					r + 1, "'s chopsticks");
			wait();
		}
		
		if (currentState[i] == State.hungry){
			currentState[i] = State.eating;
			System.out.printf("\n%s%d %s\n", "Philosopher", i + 1, "is eating.");
		}
		notifyAll();
	}
	
	private void setHungry( int i ){
		currentState[i] = State.hungry;
		System.out.printf("\n%s%d %s\n", "Philosopher", i + 1, "is hungry.");
	}
}
