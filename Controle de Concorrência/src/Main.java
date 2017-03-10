import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		
		int quantPermissoes = 1;
		int quantThreads = 5;
		
		Semaphore semaforo = new Semaphore(quantPermissoes); 
		
		ThreadComSemaforo[] threads = new ThreadComSemaforo[quantThreads];
		for(int i = 0; i < 5; i++) {
			
			threads[i] = new ThreadComSemaforo(i, semaforo);
			threads[i].start();
		}
	}
}