package ejercicio2.lenguaje2;
// Lista enlazada con nodos en java
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class lenguaje2 {

    static class Nodo {
        String partida;
        ArrayList<Integer> cuerpo;
        String firma;
        Nodo siguiente;

        public Nodo(String partida, int k) {
            this.partida = partida;
            this.cuerpo = generarCuerpo(k);
            this.firma = generarFirma(partida, cuerpo);
            this.siguiente = null;
        }

        private ArrayList<Integer> generarCuerpo(int k) {
            Random rand = new Random();
            ArrayList<Integer> cuerpo = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                cuerpo.add(rand.nextInt(100000) + 1);
            }
            return cuerpo;
        }

        private String generarFirma(String partida, ArrayList<Integer> cuerpo) {
            StringBuilder contenido = new StringBuilder(partida);
            for (int valor : cuerpo) {
                contenido.append(" ").append(valor);
            }
            return sha256(contenido.toString());
        }

        private String sha256(String texto) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(texto.getBytes());
                try (Formatter formatter = new Formatter()) {
                    for (byte b : hash) {
                        formatter.format("%02x", b);
                    }
                    return formatter.toString();
                }
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String toString() {
            return partida + "\n" + cuerpo + "\n" + firma + "\n";
        }
    }

    public static Nodo crearLista(int n, int k) {
        String partidaInicial = sha256FechaHora();
        Nodo cabeza = new Nodo(partidaInicial, k);
        Nodo actual = cabeza;

        for (int i = 1; i < n; i++) {
            Nodo nuevo = new Nodo(actual.firma, k);
            actual.siguiente = nuevo;
            actual = nuevo;
        }

        return cabeza;
    }

    private static String sha256FechaHora() {
        String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(fechaHora.getBytes());
            try (Formatter formatter = new Formatter()) {
                for (byte b : hash) {
                    formatter.format("%02x", b);
                }
                return formatter.toString();
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void imprimirLista(Nodo cabeza) {
        Nodo actual = cabeza;
        int contador = 1;
        while (actual != null) {
            System.out.println("Nodo #" + contador++);
            System.out.println(actual);
            actual = actual.siguiente;
        }
    }

    public static void ejecutarEscenario(int n, int k) {
        long inicio = System.nanoTime();
        Nodo lista = crearLista(n, k);
        long fin = System.nanoTime();

        imprimirLista(lista);
        System.out.printf("La lista fue generada en: %.3f ms\n", (fin - inicio) / 1_000_000.0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Para ejecutar el escenario 1 oprima 1");
            System.out.println("Para ejecutar el escenario 2 oprima 2");
            System.out.println("Para ejecutar el escenario 3 oprima 3");
            System.out.println("Para salir oprima cualquier otra tecla");


            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    ejecutarEscenario(3, 4);
                    break;
                case "2":
                    ejecutarEscenario(10, 200);
                    break;
                case "3":
                    ejecutarEscenario(200, 10);
                    break;
                default:
                    scanner.close();
                    System.out.println("Saliendo del programa.");
                    return;
            }
        }
    }
}
