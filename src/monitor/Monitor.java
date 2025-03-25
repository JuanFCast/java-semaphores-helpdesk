// Proyecto MonitorDormilon - Clase Monitor
// Sistemas Operativos - Integrantes: Juan Felipe Castillo Gomez, Juan Camilo Ramirez Tabares
//
// Esta clase implementa al monitor como un hilo que:
//  - Duerme cuando no hay estudiantes en el corredor (cola).
//  - Despierta cuando llega un estudiante y lo notifica (semaforización).
//  - Atiende a los estudiantes en orden FIFO, uno por vez.


package monitor;

import util.WaitingQueue;
import java.util.concurrent.Semaphore;

public class Monitor extends Thread {
    // Semáforos globales
    public static final Semaphore monitorReady = new Semaphore(0);
    public static final Semaphore studentReady = new Semaphore(0);
    public static final Semaphore accessSeats = new Semaphore(1);

    private static final WaitingQueue waitingQueue = new WaitingQueue();

    @Override
    public void run() {
        while (true) {
            try {
                // Muestra que el monitor duerme siempre antes de "acquire()"
                System.out.println("El monitor no ve estudiantes en el corredor y se dispone a dormir...");

                // Se bloquea hasta que un estudiante lo despierte con 'studentReady.release()'
                studentReady.acquire();

                // Se despierta y revisa la cola
                System.out.println("El monitor se despierta y revisa el corredor para atender al siguiente estudiante...");

                // Accede a las sillas (cola) de forma exclusiva
                accessSeats.acquire();
                Integer studentId = waitingQueue.nextStudent();
                accessSeats.release();

                if (studentId != null) {
                    System.out.println("El monitor atiende al estudiante " + studentId);

                    // Permiso al estudiante para que sepa que comenzó la atención
                    monitorReady.release();

                    // Simula el tiempo de ayuda
                    Thread.sleep((int) (Math.random() * 3000) + 1000);

                    System.out.println("El monitor terminó de ayudar al estudiante " + studentId);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static WaitingQueue getWaitingQueue() {
        return waitingQueue;
    }
}