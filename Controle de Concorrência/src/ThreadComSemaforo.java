import java.util.Random;
import java.util.concurrent.Semaphore;

// Controle de concorrencia
// Autor: Franco Stefano

// Thread com semaforo.
public class ThreadComSemaforo implements Runnable {
	
	// Atributos:
	
	private int id;
	private Semaphore semaforo;

	// Construtor:
	
	public ThreadComSemaforo(int id, Semaphore semaforo) {
		
		this.id = id;
		this.semaforo = semaforo;
	}
	
	// Metodos:
	
	// Metodo que simula processamento colocando a thread para dormir durante um determinado tempo
	// (que ela vai passar na regiao critica):	
	private void processar() {
		
		try {
			System.out.println("Thread " + this.id + " processando...");
			int valor = new Random().nextInt(10000);
			Thread.sleep((long) valor); // simulacao de processamento...
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Metodo que significa entrada na regiao critica:
	private void entrarRegiaoCritica() {
		
		System.out.println("Thread " + this.id + " entrou na regiao critica");
		processar();
		System.out.println("Thread " + this.id + " saiu da regiao critica");
	}
	
	// Metodo que significa estadia na regiao nao critica:
	private void entrarRegiaoNaoCritica() {
		System.out.println("Thread " + this.id + " na regiao nao critica");
	}
	
	// Metodo run:
	@Override
	public void run() {
		
		entrarRegiaoNaoCritica(); // toda thread deve comecar na regiao nao critica
		
		try {
			semaforo.acquire(); // a thread pede ao semaforo o recurso (P)
			entrarRegiaoCritica(); // entra na regiao critica (semaforo disse ok)
			// feito num try catch para o caso do pedido ser negado
		} catch (InterruptedException e) {
			e.printStackTrace(); // pedido negado
		} finally {
			semaforo.release(); // processamento realizado, libera recurso (V)
		}
	}
	
	// Start (chama run()):
	public void start() {
		run();
	}
}