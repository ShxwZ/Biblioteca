package biblioteca.Logica;

import java.util.ArrayList;
import java.util.List;

import biblioteca.Articulos.Articulo;
import biblioteca.Articulos.Libro;
import biblioteca.Articulos.Pasatiempo;
import biblioteca.Articulos.Revistas;

public class Main {

	public static void main(String[] args) {
		
		List<Articulo> list = new ArrayList<>();
		list.add(new Revistas(123,"Prueba revista 1",2001,33));
		list.add(new Revistas(125,"Prueba revista 2",2001,33));
		list.add(new Revistas(126,"Prueba revista 3",2001,33));
		list.add(new Libro(111,"Prueba Libro 1",2002));
		list.add(new Libro(112,"Prueba Libro 2",2003));
		list.add(new Libro(113,"Prueba Libro 3",2001));
		list.add(new Pasatiempo(521,"Prueba Pasatiempo 1",2007));
		list.add(new Pasatiempo(522,"Prueba Pasatiempo 2",2006));
		list.add(new Pasatiempo(523,"Prueba Pasatiempo 3",2005));
		
		Biblioteca b = new Biblioteca();
		b.importArticles(list);
		b.menu();
	}

}
