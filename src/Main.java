// Proyecto MonitorDormilon - Clase Main
// Sistemas Operativos - Integrantes: Juan Felipe Castillo Gomez, Juan Camilo Ramirez Tabares
//
// Esta clase contiene el método main, que inicia la ejecución del programa,
// creando y lanzando un hilo para el monitor y varios hilos para los estudiantes.


public class Main {
    public static void main(String[] args) {
        int numberOfStudents = 5; // Se puede cambiar este número de estudiantes

        // Crear y arrancar el hilo del monitor
        monitor.Monitor monitor = new monitor.Monitor();
        monitor.start();

        // Crear y arrancar los hilos de estudiantes
        for (int i = 1; i <= numberOfStudents; i++) {
            new student.Student(i).start();
        }
    }
}