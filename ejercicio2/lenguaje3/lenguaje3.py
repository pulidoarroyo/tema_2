#Lista enlazada con nodos en python
import hashlib
import random
import time
from datetime import datetime

class Nodo:
    def __init__(self, partida, cuerpo):
        self.partida = partida
        self.cuerpo = cuerpo
        self.firma = self.calcular_firma()
        self.siguiente = None

    def calcular_firma(self):
        contenido = self.partida + ' ' + ' '.join(map(str, self.cuerpo))
        return hashlib.sha256(contenido.encode()).hexdigest()

    def imprimir(self):
        print(self.partida)
        print(self.cuerpo)
        print(self.firma)
        print()

def crear_lista(n, k):
    ahora = datetime.now().strftime("%d/%m/%Y %H:%M")
    partida_inicial = hashlib.sha256(ahora.encode()).hexdigest()

    cabeza = Nodo(partida_inicial, [random.randint(1, 100000) for _ in range(k)])
    actual = cabeza

    for _ in range(1, n):
        nueva_partida = actual.firma
        nuevo_cuerpo = [random.randint(1, 100000) for _ in range(k)]
        nuevo_nodo = Nodo(nueva_partida, nuevo_cuerpo)
        actual.siguiente = nuevo_nodo
        actual = nuevo_nodo

    return cabeza

def imprimir_lista(cabeza):
    actual = cabeza
    contador = 1
    while actual:
        print(f"Nodo {contador}:")
        actual.imprimir()
        actual = actual.siguiente
        contador += 1

def ejecutar_escenario(n, k):
    print(f"Escenario n={n}, k={k}")
    inicio = time.time()

    lista = crear_lista(n, k)
    imprimir_lista(lista)

    fin = time.time()
    print(f"Tiempo de ejecución: {fin - inicio:.6f} segundos")
    print("-" * 40)

def main():
    
    print("Para ejecutar el escenario 1 oprima 1")
    print("Para ejecutar el escenario 2 oprima 2")
    print("Para ejecutar el escenario 3 oprima 3")
    print("Para salir oprima cualquier otra tecla")
    print("Seleccione una opción:")
    opcion = input("Ingrese su opción: ")
    if opcion == '1':
        ejecutar_escenario(3, 4)
    elif opcion == '2':
        ejecutar_escenario(10, 200)
    elif opcion == '3':
        ejecutar_escenario(200, 10)
    else:
        print("Saliendo del programa")
        return
    main()

if __name__ == "__main__":
    main()
