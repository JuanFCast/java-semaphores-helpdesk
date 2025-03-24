package student;

import monitor.Monitor;

import java.util.concurrent.Semaphore;

public class Student extends Thread {
    private final int studentId;

    public Student(int id) {
        this.studentId = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Simula que el estudiante está programando
                System.out.println("Student " + studentId + " is programming...");
                Thread.sleep((int) (Math.random() * 5000) + 2000);

                // Intenta conseguir una silla
                Monitor.accessSeats.acquire();
                boolean satDown = Monitor.getWaitingQueue().tryToSit(studentId);
                Monitor.accessSeats.release();

                if (satDown) {
                    // Notifica que hay un estudiante esperando
                    Monitor.studentReady.release();

                    // Espera a que el monitor esté listo para ayudar
                    Monitor.monitorReady.acquire();
                } else {
                    // No consiguió silla, se va y vuelve más tarde
                    System.out.println("Student " + studentId + " goes back to program and will return later.");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
