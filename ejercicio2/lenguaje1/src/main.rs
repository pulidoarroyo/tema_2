use sha2::{Sha256, Digest};
use chrono::Local;
use rand::Rng;

struct Nodo {
    partida: String,
    cuerpo: Vec<u32>,
    firma: String,
    siguiente: Option<Box<Nodo>>,
}

impl Nodo {
    fn new(partida: String, k: usize) -> Self {
        let cuerpo = Nodo::generar_cuerpo(k);
        let firma = Nodo::generar_firma(&partida, &cuerpo);
        Nodo {
            partida,
            cuerpo,
            firma,
            siguiente: None,
        }
    }

    fn generar_cuerpo(k: usize) -> Vec<u32> {
        let mut rng = rand::thread_rng();
        (0..k).map(|_| rng.gen_range(1..=100000)).collect()
    }

    fn generar_firma(partida: &str, cuerpo: &Vec<u32>) -> String {
        let mut contenido = partida.to_string();
        for val in cuerpo {
            contenido.push_str(&format!(" {}", val));
        }
        let mut hasher = Sha256::new();
        hasher.update(contenido.as_bytes());
        let result = hasher.finalize();
        format!("{:x}", result)
    }

    fn imprimir(&self, index: usize) {
        println!("Nodo #{}", index);
        println!("Partida: {}", self.partida);
        println!("Cuerpo: {:?}", self.cuerpo);
        println!("Firma: {}\n", self.firma);
    }
}

fn sha256_fecha_hora() -> String {
    let ahora = Local::now().format("%Y-%m-%d %H:%M:%S").to_string();
    let mut hasher = Sha256::new();
    hasher.update(ahora.as_bytes());
    let result = hasher.finalize();
    format!("{:x}", result)
}

fn crear_lista(n: usize, k: usize) -> Box<Nodo> {
    let partida_inicial = sha256_fecha_hora();
    let mut cabeza = Box::new(Nodo::new(partida_inicial, k));
    let mut actual = &mut cabeza;

    for _ in 1..n {
        let nuevo = Box::new(Nodo::new(actual.firma.clone(), k));
        actual.siguiente = Some(nuevo);
        actual = actual.siguiente.as_mut().unwrap();
    }

    cabeza
}

fn imprimir_lista(mut nodo: &Nodo) {
    let mut i = 1;
    loop {
        nodo.imprimir(i);
        match &nodo.siguiente {
            Some(siguiente) => {
                nodo = &siguiente;
                i += 1;
            },
            None => break,
        }
    }
}

fn ejecutar_escenario(n: usize, k: usize) {
    let inicio = std::time::Instant::now();
    let lista = crear_lista(n, k);
    let duracion = inicio.elapsed();
    imprimir_lista(&lista);
    println!("La lista fue generada en: {:.3} ms", duracion.as_secs_f64() * 1000.0);
}

fn main() {
    use std::io::{self, Write};

    loop {
        println!("Seleccione una opción:");
        println!("1: Ejecutar escenario 1 (3 nodos, 4 elementos)");
        println!("2: Ejecutar escenario 2 (10 nodos, 200 elementos)");
        println!("3: Ejecutar escenario 3 (200 nodos, 10 elementos)");
        println!("Cualquier otra tecla para salir.");

        print!("Ingrese su opción: ");
        io::stdout().flush().unwrap();

        let mut opcion = String::new();
        io::stdin().read_line(&mut opcion).unwrap();

        match opcion.trim() {
            "1" => ejecutar_escenario(3, 4),
            "2" => ejecutar_escenario(10, 200),
            "3" => ejecutar_escenario(200, 10),
            _ => {
                println!("Saliendo del programa.");
                break;
            }
        }
    }
}
