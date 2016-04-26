import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PhilosopherTest {

	public static void main(String[] args){
		ExecutorService application = Executors.newCachedThreadPool();
		Ibuffer sharedLocation = new SynchronizedBuffer();
		
		for(int i = 0; i < 5; i++){
			application.execute( new Philosopher( sharedLocation, i ));
		}
		
		application.shutdown();
	}
}
