import java.util.Random;

public class Philosopher implements Runnable{
	
	private final static Random generator = new Random();
	private final Ibuffer sharedLocation;
	private final int STARVEINDEX = 5;
	private int philosopherIndex;
	
	public Philosopher( Ibuffer shared, int pIndex ){
		sharedLocation = shared;
		philosopherIndex = pIndex;
	}
	
	@Override
	public void run() {
		int sum = 0;
		for (int count = 0; count < STARVEINDEX; count++){
			try{
				Thread.sleep(generator.nextInt( 3000 ));
				sharedLocation.set(Ibuffer.State.hungry, philosopherIndex);
			}catch(InterruptedException exception){
				exception.printStackTrace();
			}
			
			try{
				Thread.sleep(generator.nextInt( 3000 ));
				sharedLocation.set(Ibuffer.State.eating, philosopherIndex);
				if( sharedLocation.get(philosopherIndex) == Ibuffer.State.eating)
					sum++;
			}catch(InterruptedException exception){
				exception.printStackTrace();
			}
			
			try{
				Thread.sleep(generator.nextInt( 3000 ));
				sharedLocation.set(Ibuffer.State.thinking, philosopherIndex);
			}catch(InterruptedException exception){
				exception.printStackTrace();
			}
			
		}
		
		System.out.printf("\n%s%d %s %d %s\n", "Philosopher", philosopherIndex + 1, 
				"has eaten a total of", sum, "times");
	}
	
	public int getIndex(){
		return philosopherIndex;
	}

}
