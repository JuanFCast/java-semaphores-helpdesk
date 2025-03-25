// Proyecto MonitorDormilon - Clase WaitingQueue
// Sistemas Operativos - Integrantes: Juan Felipe Castillo Gomez, Juan Camilo Ramirez Tabares
//
// Esta clase representa la cola de espera (sillas en el corredor).
// Su capacidad es de 3 sillas. Los estudiantes intentan sentarse aquí
// mientras esperan al monitor. Si la cola está llena, se van y regresan más tarde.


package util;

import java.util.LinkedList;
import java.util.Queue;

public class WaitingQueue {
    private final int capacity = 3;
    private final Queue<Integer> queue = new LinkedList<>();

    public synchronized boolean tryToSit(int studentId) {
        if (queue.size() < capacity) {
            queue.add(studentId);
            System.out.println("Estudiante " + studentId
                    + " se sentó en el corredor. Sillas ocupadas: " + queue.size());
            return true;
        } else {
            System.out.println("No hay sillas disponibles. "
                    + "El estudiante " + studentId + " no puede esperar.");
            return false;
        }
    }

    public synchronized Integer nextStudent() {
        return queue.poll(); // Siguiente en la cola (orden de llegada)
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}