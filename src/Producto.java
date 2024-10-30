import java.util.concurrent.locks.ReentrantLock;

public class Producto extends Thread {
    // Array y cerrojo estáticos para ser compartidos entre instancias de Producto
    private static PrecioProducto[] producto = new PrecioProducto[10];
    private static final ReentrantLock cerrojo = new ReentrantLock();

    // Constructor vacío
    public Producto() {
    }

    // Método para mostrar el producto con exclusión mutua
    public static void mostrarProducto() {
        cerrojo.lock();
        try {
            for (int i = 0; i < producto.length; i++) {
                if (producto[i] != null)
                    System.out.println(producto[i].getProveedor() + " precio: " + producto[i].getPrecio());
            }
        } finally {
            cerrojo.unlock();
        }
    }

    // Método para insertar un producto de manera segura
    public void insertarProducto(PrecioProducto precioProducto, int posicion) {
        cerrojo.lock();
        try {
            if (posicion < 10) {
                producto[posicion] = precioProducto;
            }
        } finally {
            cerrojo.unlock();
        }
    }

    // Método que se ejecuta cuando el hilo arranca
    public void run() {
        cerrojo.lock();
        try {
            for (int i = 0; i < producto.length; i++) {
                producto[i] = new PrecioProducto(getName() + i, i * 10 + 1);
            }
        } finally {
            cerrojo.unlock();
        }
    }
}

class PrecioProducto {
    private String proveedor;
    private float precio;

    // Constructor para inicializar proveedor y precio
    public PrecioProducto(String proveedor, float precio) {
        this.proveedor = proveedor;
        this.precio = precio;
    }

    // Getters y Setters
    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
