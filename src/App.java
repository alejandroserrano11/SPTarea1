public class App {

    public static void main(String[] args) throws Exception {

        Producto uno = new Producto();
        Producto dos = new Producto();
        Producto tres = new Producto();
        uno.start();
        dos.start();
        tres.start();

        uno.join();
        dos.join();
        tres.join();

        Producto.mostrarProducto();

    }
}
