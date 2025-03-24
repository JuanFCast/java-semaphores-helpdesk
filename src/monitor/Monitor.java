package monitor;

import util.WaitingQueue;

import java.util.concurrent.Semaphore;

public class Monitor extends Thread {
    public static final Semaphore monitorReady = new Semaphore(0);
    public static final Semaphore studentReady = new Semaphore(0);
    public static final Semaphore accessSeats = new Semaphore(1);

    private static final WaitingQueue waitingQueue = new WaitingQueue();

    @Override
    public void run() {
        while (true) {
            try {
                // Espera a que un estudiante lo despierte
                studentReady.acquire();

                accessSeats.acquire();
                Integer studentId = waitingQueue.nextStudent();
                accessSeats.release();

                if (studentId != null) {
                    System.out.println("Monitor is helping student " + studentId);
                    monitorReady.release(); // Listo para ayudar

                    // Simula el tiempo de ayuda
                    Thread.sleep((int) (Math.random() * 3000) + 1000);

                    System.out.println("Monitor finished helping student " + studentId);
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
