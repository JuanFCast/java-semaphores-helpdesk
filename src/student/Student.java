package student;

import monitor.Monitor;

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
                System.out.println("Estudiante " + studentId
                        + " está programando en la sala de cómputo...");
                Thread.sleep((int) (Math.random() * 5000) + 2000);

                // Intenta conseguir una silla
                Monitor.accessSeats.acquire();
                boolean satDown = Monitor.getWaitingQueue().tryToSit(studentId);
                Monitor.accessSeats.release();

                if (satDown) {
                    System.out.println("Estudiante " + studentId
                            + " se sienta en el corredor y avisa al monitor de que está esperando.");
                    Monitor.studentReady.release();

                    // Espera la señal de "Monitor listo"
                    Monitor.monitorReady.acquire();
                } else {
                    // No consiguió silla, se va y vuelve más tarde
                    System.out.println("No hay sillas en el corredor. "
                            + "Estudiante " + studentId + " regresa a programar y volverá más tarde.");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
