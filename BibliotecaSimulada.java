import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class Libro {
    String titulo;
    boolean disponible = true;
    LocalDate fechaDevolucion = null;

    Libro(String titulo) {
        this.titulo = titulo;
    }
}

class Usuario {
    String nombre;
    ArrayList<Libro> librosPrestados = new ArrayList<>();

    Usuario(String nombre) {
        this.nombre = nombre;
    }
}

class Biblioteca {
    ArrayList<Libro> libros = new ArrayList<>();
    ArrayList<Usuario> usuarios = new ArrayList<>();

    void agregarLibro(String titulo) {
        libros.add(new Libro(titulo));
    }

    void registrarUsuario(String nombre) {
        if (buscarUsuario(nombre) == null) {
            usuarios.add(new Usuario(nombre));
            System.out.println("Usuario registrado correctamente.");
        } else {
            System.out.println("El usuario ya existe.");
        }
    }

    Libro buscarLibro(String titulo) {
        for (Libro l : libros) {
            if (l.titulo.equalsIgnoreCase(titulo)) return l;
        }
        return null;
    }

    Usuario buscarUsuario(String nombre) {
        for (Usuario u : usuarios) {
            if (u.nombre.equalsIgnoreCase(nombre)) return u;
        }
        return null;
    }

    boolean prestarLibro(String nombreUsuario, String tituloLibro) {
        Usuario usuario = buscarUsuario(nombreUsuario);
        Libro libro = buscarLibro(tituloLibro);
        if (usuario == null || libro == null) return false;
        if (!libro.disponible) return false;
        libro.disponible = false;
        libro.fechaDevolucion = LocalDate.now().plusDays(7);
        usuario.librosPrestados.add(libro);
        return true;
    }

    boolean devolverLibro(String nombreUsuario, String tituloLibro) {
        Usuario usuario = buscarUsuario(nombreUsuario);
        Libro libro = buscarLibro(tituloLibro);
        if (usuario == null || libro == null) return false;
        if (!usuario.librosPrestados.contains(libro)) return false;
        usuario.librosPrestados.remove(libro);
        libro.disponible = true;
        libro.fechaDevolucion = null;
        return true;
    }

    void mostrarLibros() {
        System.out.println("Libros en la biblioteca:");
        for (Libro l : libros) {
            System.out.println("- " + l.titulo + (l.disponible ? " (Disponible)" : " (Prestado, devuelve: " + l.fechaDevolucion + ")"));
        }
    }

    void mostrarUsuarios() {
        System.out.println("Usuarios registrados:");
        for (Usuario u : usuarios) {
            System.out.print("- " + u.nombre);
            if (!u.librosPrestados.isEmpty()) {
                System.out.print(" | Libros prestados: ");
                for (Libro l : u.librosPrestados) {
                    System.out.print(l.titulo + " (devuelve: " + l.fechaDevolucion + "), ");
                }
            }
            System.out.println();
        }
    }
}

public class BibliotecaSimulada {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        // Ejemplo: agregar libros y usuarios
        biblioteca.agregarLibro("El Quijote");
        biblioteca.agregarLibro("Cien años de soledad");
        biblioteca.agregarLibro("1984");
        biblioteca.registrarUsuario("Ana");
        biblioteca.registrarUsuario("Luis");

        int opcion = 0;
        do {
            System.out.println("\n--- Biblioteca ---");
            System.out.println("1. Ver libros");
            System.out.println("2. Prestar libro");
            System.out.println("3. Devolver libro");
            System.out.println("4. Registrar usuario");
            System.out.println("5. Ver usuarios");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.nextLine();
                continue;
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    biblioteca.mostrarLibros();
                    break;
                case 2:
                    System.out.print("Nombre de usuario: ");
                    String usuario = scanner.nextLine();
                    System.out.print("Título del libro: ");
                    String libro = scanner.nextLine();
                    if (biblioteca.prestarLibro(usuario, libro)) {
                        System.out.println("Libro prestado correctamente.");
                    } else {
                        System.out.println("No se pudo prestar el libro (no existe, no disponible o usuario no registrado).");
                    }
                    break;
                case 3:
                    System.out.print("Nombre de usuario: ");
                    usuario = scanner.nextLine();
                    System.out.print("Título del libro: ");
                    libro = scanner.nextLine();
                    if (biblioteca.devolverLibro(usuario, libro)) {
                        System.out.println("Libro devuelto correctamente.");
                    } else {
                        System.out.println("No se pudo devolver el libro (no existe, no prestado o usuario no registrado).");
                    }
                    break;
                case 4:
                    System.out.print("Nombre del nuevo usuario: ");
                    String nuevoUsuario = scanner.nextLine();
                    biblioteca.registrarUsuario(nuevoUsuario);
                    break;
                case 5:
                    biblioteca.mostrarUsuarios();
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
        scanner.close();
    }
}