# Ejercicios tema_2

1. El ejercicio 2 tiene como objetivo comparar tres lenguajes de programación (Rust, Java y Python) implementando una estructura de datos basada en una **lista enlazada de nodos**, donde cada nodo contiene una firma SHA-256 generada a partir de datos aleatorios.

## Estructura del Proyecto

```
tema_2/
└── ejercicio_2/
    ├── lenguaje1/         # Implementación en Rust
    │   ├── Cargo.toml
    │   └── src/
    │       └── main.rs
    ├── lenguaje2/         # Implementación en Java
    │   └── lenguaje2.java
    └── lenguaje3/         # Implementación en Python
        └── lenguaje3.py
```

---

## Instrucciones de Ejecución

### 🦀 Rust (`lenguaje1`)
Requisitos:
- Tener Rust y Cargo instalados. Puedes instalarlo desde: https://www.rust-lang.org/es/tools/install

```bash
cd tema_2/ejercicio_2/lenguaje1
cargo run
```

---

### ☕ Java (`lenguaje2`)
Requisitos:
- Tener Java JDK instalado (versión 8 o superior).

```bash
cd tema_2/ejercicio_2/lenguaje2
javac lenguaje2.java
java lenguaje2
```

---

### 🐍 Python (`lenguaje3`)
Requisitos:
- Python 3.x instalado.

```bash
cd tema_2/ejercicio_2/lenguaje3
python lenguaje3.py
```

---

## Escenarios de Prueba

Los tres programas permiten ejecutar distintos escenarios con diferentes cantidades de nodos y elementos por nodo. Ejemplo de menú (en el caso de Python):

```
1. Ejecutar escenario 1 (3 nodos, 4 elementos)
2. Ejecutar escenario 2 (10 nodos, 200 elementos)
3. Ejecutar escenario 3 (200 nodos, 10 elementos)
```