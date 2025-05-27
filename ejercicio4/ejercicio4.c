#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define BAR_TO_PSI 14.5 // Factor de conversión de bar a psi

// Función para simular la lectura de temperatura del refrigerante
float obtenerTemperatura() {
    return 70.0 + (rand() % 50); // Simula una temperatura entre 70°C y 120°C
}

// Función para simular la lectura de presión del sistema en bar
float obtenerPresionEnBar() {
    return 0.8 + ((rand() % 40) / 10.0); // Simula una presión entre 0.8 y 4.8 bar
}

// Función para convertir la presión de bar a psi
float convertirBarAPsi(float presionBar) {
    return presionBar * BAR_TO_PSI;
}

// Función para simular el estado del ventilador
int estadoVentilador(float temperatura) {
    return temperatura > 95.0 ? 1 : 0; // Enciende si la temperatura supera los 95°C
}

int main() {
    srand(time(NULL)); // Inicializa la semilla para números aleatorios

    int opcion;
    do {
        printf("\n--- Sistema de Refrigeración del Vehículo ---\n");
        printf("1. Medir temperatura del refrigerante\n");
        printf("2. Medir presión del sistema (psi)\n");
        printf("3. Verificar estado del ventilador\n");
        printf("4. Salir\n");
        printf("Seleccione una opción: ");
        scanf("%d", &opcion);

        switch (opcion) {
            case 1:
                printf("Temperatura del refrigerante: %.2f°C\n", obtenerTemperatura());
                break;
            case 2: {
                float presionBar = obtenerPresionEnBar();
                float presionPsi = convertirBarAPsi(presionBar);
                printf("Presión del sistema: %.2f psi (%.2f bar)\n", presionPsi, presionBar);
                break;
            }
            case 3: {
                float temp = obtenerTemperatura();
                printf("Temperatura actual: %.2f°C\n", temp);
                printf("Estado del ventilador: %s\n", estadoVentilador(temp) ? "ENCENDIDO" : "APAGADO");
                break;
            }
            case 4:
                printf("Saliendo del sistema...\n");
                break;
            default:
                printf("Opción inválida. Intente nuevamente.\n");
        }
    } while (opcion != 4);

    return 0;
}