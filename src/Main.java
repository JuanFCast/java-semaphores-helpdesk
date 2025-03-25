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