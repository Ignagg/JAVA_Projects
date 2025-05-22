import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    public static void main(String[] args) {
        ArrayList<String> tareas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- To-Do List ---");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Mostrar tareas");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.nextLine(); // Limpiar entrada inválida
                continue;
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Escribe la nueva tarea: ");
                    String tarea = scanner.nextLine();
                    tareas.add(tarea);
                    System.out.println("Tarea agregada.");
                    break;
                case 2:
                    System.out.println("\nTus tareas:");
                    for (int i = 0; i < tareas.size(); i++) {
                        System.out.println((i + 1) + ". " + tareas.get(i));
                    }
                    break;
                case 3:
                    System.out.print("Número de tarea a eliminar: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Por favor, ingresa un número válido.");
                        scanner.nextLine(); // Limpiar entrada inválida
                        break;
                    }
                    int num = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    if (num > 0 && num <= tareas.size()) {
                        tareas.remove(num - 1);
                        System.out.println("Tarea eliminada.");
                    } else {
                        System.out.println("Número inválido.");
                    }
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } 
        while (opcion != 4);
        scanner.close();
    }
}