package biblioteca.Logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import biblioteca.Articulos.Articulo;
import biblioteca.Articulos.Libro;
import biblioteca.Articulos.Pasatiempo;
import biblioteca.Articulos.Revistas;
import biblioteca.Interfaces.Alquilar;
import biblioteca.Interfaces.Reservar;
import inputmanager.InputManager;

/**
 * @author Gabriel
 * @version 09/10/2022
 */

public class Biblioteca {
	
	List<Articulo> books = new ArrayList<>();
	Map<Articulo,Integer> booksNoAvailable = new HashMap<>();
	
	/**
	 * Add list of articles 
	 * @param l List<Articulo>
	 */
	public void importArticles(List<Articulo> l) {
		books.addAll(l);
		System.out.println("[Biblioteca] Articulos añadidos");
	}
	
	/**
	 * Create Articulo
	 * @param option (1) Revista (2) Pasatiempo (3) Libro
	 */
	public void createArticle(int option) {
		int identificador =(int) (Math.random() * (900 - 100) + 100);
		if(option == 1){
			System.out.println("[Biblioteca] Dando de alta revista");
			books.add(new Revistas(identificador, 
			InputManager.getStringNotEmpty("titulo de la Revista"),
			InputManager.getNumberBiggerCondition("año de publicacion",
			0),
			InputManager.getNumber("número de revista")));
		}
		if(option == 2){
			System.out.println("[+] Dando de alta pasatiempo");
			books.add(new Pasatiempo(identificador,
			InputManager.getStringNotEmpty("titulo del pasatiempo"),
			InputManager.getNumber("año de publicacion")));
		}
		if(option == 3){
			System.out.println("[+] Dando de alta libro");
			books.add(new Libro(identificador,
			InputManager.getStringNotEmpty("titulo del libro"),
			InputManager.getNumber("año de publicacion")));
		}

		System.out.println("[Biblioteca] Articulo añadido");
	}
	
	/**
	 * Print all items from library and print
	 */
	public void printAllItems() {
		System.out.println("=".repeat(20));
		System.out.println("Revistas:");
		System.out.println("=".repeat(20));
		printArticle(getAllTypeBook(1,books));
		System.out.println("=".repeat(20));
		System.out.println("Pasatiempos:");
		System.out.println("=".repeat(20));
		printArticle(getAllTypeBook(2,books));
		System.out.println("=".repeat(20));
		System.out.println("Libros:");
		System.out.println("=".repeat(20));
		printArticle(getAllTypeBook(3,books));
	}
	
	/**
	 * Print both article
	 */
	public void printArticle(List<Articulo> arti) {
		arti.forEach(b -> {
			System.out.println(b);
			System.out.println();
		});
	}
	/**
	 * Get all items one type
	 * @param type,articles (1) Revistas (2) Pasatiempo (3) Libro
	 */
	public List<Articulo> getAllTypeBook(int type, List<Articulo> article) {
		List<Articulo> revistasTemp = new ArrayList<>();
		article.forEach(b ->{
			if (type == 1) 
				if (b instanceof Revistas) revistasTemp.add(b);
			if (type == 2) 
				if (b instanceof Pasatiempo) revistasTemp.add(b);
			if (type == 3) 
				if (b instanceof Libro) revistasTemp.add(b);
		});
		return revistasTemp;
	}
	
	/**
	 * Print reserve article or rent article
	 * @param option (1) reserve (2) rent
	 */
	public void menuReserveOrRent(int option) {
		System.out.println("*".repeat(20));
		System.out.println(option == 1 ? "Reservar articulo" : "Alquilar articulo");
		System.out.println("*".repeat(20));
		int numberSC = InputManager.getNumberBiggerCondition("tu numero de socio", 0);
		if (option == 1) {
			System.out.println("=".repeat(20));
			System.out.println("1) Revistas");
			System.out.println("2) Libros");
			System.out.println("=".repeat(20));
			int optionType = InputManager.getNumberRange("que tipo de articulos vas a reservar", 1, 2);
			if(getNumberOfArticle(optionType > 1 ? 3 : 1) != 0){
				reserveArticle(numberSC, optionType > 1 ? 3 : 1);
			} else{
				System.out.println("[!] No existe articulos de ese tipo para reservar");
			}
		}
		if (option == 2) {
			if(getNumberOfArticle(3) != 0){
				rentArticle(numberSC, 3);
			} else{
				System.out.println("[!] No existe articulos registrados para alquilar");
			}
			
		}
	}
	
	/**
	 * Print End reserve article or End rent article
	 * @param option (1) reserve (2) rent
	 */
	public void menuEndReserveOrRent(int option) {
		System.out.println("*".repeat(30));
		System.out.println("Terminar " + (option == 1 ? "reserva articulo" : "alquiler articulo"));
		System.out.println("*".repeat(30));
		if (option == 1) {
			System.out.println("=".repeat(30));
			System.out.println("1) Terminar reserva revista");
			System.out.println("2) Terminar reserva libros");
			System.out.println("=".repeat(30));
			int optionType = InputManager.getNumberRange("tipo de articulo que va a terminar reserva", 1, 2);
			if (optionType == 1 && getNumberOfNoAvailables(2, 1) != 0) {
			    endReserve(1);
            } else if (optionType == 2 && getNumberOfNoAvailables(2, 3) != 0) {
			    endReserve(3);
			} else {
			    System.out.println("[!] No hay articulos de ese tipo reservados");
			}
		}
		if(option == 2){
			endRent(3);
		}
	}
	
	/**
	 * Reserve an article
	 * @param numberSC member code
	 * @param option Type article
	 */
	public void reserveArticle(int numberSC, int option) {
		int codeArticle;
		boolean valid;
		do {
			printArticle(getAllTypeBook(option,books));
			codeArticle = InputManager.getNumberBiggerCondition("el codigo del articulo", 0);
			valid = checkArticleExists(codeArticle, option) && checkArticleAvailable(codeArticle,  option);
			System.out.print(!checkArticleAvailable(codeArticle, option)? "[!] El articulo no esta disponible\n" : "");
			if(!valid){
				continueMenu();
			}
		} while (!valid);
		int codeCheck = codeArticle;
		booksNoAvailable.put(getArticle(codeArticle, option), numberSC);
		changeStatus(codeArticle, option, 1);
		System.out.println();
		System.out.println(getArticleNoAvailable(codeCheck).toString());
		System.out.println("[>] Articulo reservado!");
	}
	
	/**
	 * Change status article
	 * @param codeArticle code of article
	 * @param option (1) Revistas (2) Pasatiempo (3) Libro
	 * @param reserveOrRent (1) Reserve (2) Rent
	 */
	public void changeStatus(int codeArticle, int option,int reserveOrRent) {
		if (reserveOrRent == 1) {
			if (!((Reservar)getArticle(codeArticle, option)).isReserved())
				((Reservar)getArticle(codeArticle, option)).Reserved();
			 else 
				((Reservar)getArticle(codeArticle, option)).notReserved();
		}
		if (reserveOrRent == 2) {
			if (!((Alquilar)getArticle(codeArticle, option)).isRented())
				((Alquilar)getArticle(codeArticle, option)).Rent(InputManager.getNumberBiggerCondition("cuantos meses alquilar", 0));
			 else 
				((Alquilar)getArticle(codeArticle, option)).returnRent();
		}
		
	}
	
	/**
	 * Check article exist
	 * @param codeArticle code of article
	 * @param option (1) Revistas (2) Pasatiempo (3) Libro
	 */
	
	public boolean checkArticleExists(int codeArticle, int option) {
		for (Articulo articulo : getAllTypeBook(option,books)) {
			if (articulo.getCodeArticle() == codeArticle) {
				return true;
			}
		}
		System.out.println("[!] El codigo no es valido");
		continueMenu();
		return false;
	}
	
	/**
	 * Check article available
	 * @param codeArticle code of article
	 * @param option (1) Revistas (2) Pasatiempo (3) Libro
	 */
	
	public boolean checkArticleAvailable(int codeArticle, int option) {
		return !booksNoAvailable.containsKey(getArticle(codeArticle, option));
	}
	
	/**
	 * Get article
	 * @param codeArticle code of article
	 * @param option (1) Revistas (2) Pasatiempo (3) Libro
	 */
	
	public Articulo getArticle(int codeArticle, int option) {
		Articulo arReturn = null;
		for (Articulo articulo : getAllTypeBook(option,books)) {
			if (articulo.getCodeArticle() == codeArticle) {
				arReturn = articulo;
			}
		}
		return arReturn;
	}
	
	/**
	 * Get article from booksNoAvailable
	 * @param codeArticle code of article
	 * @return Articulo
	 */
	
	public Articulo getArticleNoAvailable(int codeArticle) {
		for (Map.Entry<Articulo, Integer> article: booksNoAvailable.entrySet()) {
			if (article.getKey().getCodeArticle() == codeArticle) {
				return article.getKey();
			}
		}
		return null;
	}

	/**
	 * Print one type of articles rented or reserved
	 * @param option (1) Revistas (2) Pasatiempo (3) Libro & RentOrReserved
	 */
	public void printAllRentedOrReserve(int option, int RentOrReserve) {
		List<Articulo> noAvaibleArticlesTemp = new ArrayList<>();
		booksNoAvailable.forEach((k,v)-> noAvaibleArticlesTemp.add(k));
		getAllTypeBook(option,noAvaibleArticlesTemp).forEach(b ->{
			if (RentOrReserve == 1) {
				if(((Alquilar)b).isRented()){
					System.out.println(b+
					"\nCodigo socio: " + booksNoAvailable.get(b));
					System.out.println();
				}
			}
			if (RentOrReserve == 2) {
				if(((Reservar)b).isReserved()){
					System.out.println(b+
					"\nCodigo socio: " + booksNoAvailable.get(b));
					System.out.println();
				}
			}
		});
	}
	/**
	 * getNumber of no available articles (Rent or Reserv)
	 * @param option (1) Rent or (2) Reserv
	 */
	public List<Articulo> getAllNoAvailables(int option){
		List <Articulo> listArticlesROR = new ArrayList<>();
		if (option == 1){
			for (Map.Entry<Articulo,Integer> a : booksNoAvailable.entrySet()) {
				if(a.getKey() instanceof Alquilar)
				    listArticlesROR.add(a.getKey());
			}
		}
		if (option == 2){
			for (Map.Entry<Articulo,Integer> a : booksNoAvailable.entrySet()) {
				if(a.getKey() instanceof Reservar)
				    listArticlesROR.add(a.getKey());
			}
		}
		return listArticlesROR;
	}
	   /**
     * getNumber of no available articles (Rent or Reserv)
     * @param option (1) Rent or (2) Reserv && Type (1) Revistas (2) Pasatiempo (3) Libro
     */
    public int getNumberOfNoAvailables(int option,int type){
        int total = 0;
        for (Articulo articulo : getAllNoAvailables(option)) {
            total += (type == 1 && articulo instanceof Revistas) ? 1 : 0;
            total += (type == 2 && articulo instanceof Pasatiempo) ? 1 : 0;
            total += (type == 3 && articulo instanceof Libro) ? 1 : 0;
        }        
        return total;
    }


	/**
	 * getNumber of articles
	 * @param option (1) Revistas, (2) Pasatiempos or (3) Libros
	 */
	public int getNumberOfArticle(int option){
		int total = 0;
		if (option == 1){
			for (Articulo a : books) {
				if(a instanceof Revistas && !((Revistas)a).isReserved())
					total++;
			}
		}
		if (option == 2){
			for (Articulo a : books) {
				if(a instanceof Pasatiempo)
					total++;
			}
		}
		if (option == 3){
			for (Articulo a : books) {
				if(a instanceof Libro && !((Libro)a).isReserved() && !((Libro)a).isRented())
					total++;
			}
		}
		return total;
	}

	/**
	 * End reserve
	 * @param option (1) Revistas (2) Pasatiempo (3) Libro
	 */
	public void endReserve(int option) {
		int codeArticle;
		boolean valid;
		do {
			printAllRentedOrReserve(option,2);
			codeArticle = InputManager.getNumberBiggerCondition("el codigo del articulo", 0);
			valid = checkArticleExists(codeArticle, option) && !checkArticleAvailable(codeArticle,  option);
			System.out.println();
		} while (!valid);
		int codeCheck = codeArticle;
		booksNoAvailable.remove(getArticle(codeArticle, option));
		changeStatus(codeArticle, option, 1);
		System.out.println(getArticle(codeCheck,option).toString());
		System.out.println("[>] Articulo devuelto!");
	}
	
	/**
     * Rent an article
     * @param numberSC member code
	 * @param option type article
     */
    public void rentArticle(int numberSC, int option) { 
        int codeArticle;
        boolean valid;
        do {
            printArticle(getAllTypeBook(option,books));
            codeArticle = InputManager.getNumberBiggerCondition("el codigo del articulo", 0);
            valid = checkArticleExists(codeArticle, option) && checkArticleAvailable(codeArticle,  option);
            System.out.print(!checkArticleAvailable(codeArticle, option)? "[!] El articulo no esta disponible\n" : "");
			if(!valid){
				continueMenu();
			}
        } while (!valid);
        int codeCheck = codeArticle;
        booksNoAvailable.put(getArticle(codeArticle, option), numberSC);
        changeStatus(codeArticle, option, 2);
        System.out.println();
        System.out.println(getArticleNoAvailable(codeCheck).toString());
        System.out.println("[>] Articulo alquilado!");
    } 
	
	/**
	 * End rent
	 * @param option (1) Revistas (2) Pasatiempo (3) Libro
	 */
	public void endRent(int option) {
		int codeArticle;
		boolean valid;
		do {
			printAllRentedOrReserve(option,1);
			codeArticle = InputManager.getNumberBiggerCondition("el codigo del articulo", 0);
			valid = checkArticleExists(codeArticle, option) && !checkArticleAvailable(codeArticle,  option);
			System.out.println();
		} while (!valid);
		int codeCheck = codeArticle;
		booksNoAvailable.remove(getArticle(codeArticle, option));
		changeStatus(codeArticle, option, 1);
		System.out.println(getArticle(codeCheck,option).toString());
		System.out.println("[>] Articulo devuelto!");
	}

	/**
	 * Menu library
	 */
	public void menu(){
		int option;
		boolean valid;
		InputManager.limpiarConsola();
		do {
			System.out.println("[-] Menu Biblioteca [-]");
			System.out.println("=".repeat(50));
			System.out.println("1.  Dar de alta pasa tiempos, revistas y libros");
			System.out.println("2.  Mostrar artículos de la biblioteca");
			System.out.println("3.  Mostrar sólo las revistas");
			System.out.println("4.  Mostrar sólo los libros");
			System.out.println("5.  Mostrar sólo los pasa tiempos");
			System.out.println("6.  Reservar artículo");
			System.out.println("7.  Finalizar reserva");
			System.out.println("8.  Mostrar artículos reservados");
			System.out.println("9.  Alquilar libro");
			System.out.println("10. Devolver libro");
			System.out.println("11. Mostrar libros alquilados");
			System.out.println("=".repeat(50));
			option = InputManager.getNumberRange("el numero de opcion", 1, 11);
			valid = option >= 0 && option <= 11;
		} while (!valid);
		switch (option) {
			case 1 -> {
				InputManager.limpiarConsola();
				System.out.println("=".repeat(20));
				System.out.println("1. Revista");
				System.out.println("2. Pasatiempo");
				System.out.println("3. Libro");
				System.out.println("=".repeat(20));
				createArticle(InputManager.getNumberRange("que quieres dar de alta", 1, 3));
				continueMenu();
				menu();
			}
			case 2 -> {
				InputManager.limpiarConsola();
				printAllItems();
				continueMenu();
				menu();
			}
			case 3 -> {
				InputManager.limpiarConsola();
				System.out.println("=".repeat(20));
				System.out.println("Revistas:");
				System.out.println("=".repeat(20));
				printArticle(getAllTypeBook(1, books));
				continueMenu();
				menu();
			}
			case 4 -> {
				InputManager.limpiarConsola();
				System.out.println("=".repeat(20));
				System.out.println("Libros:");
				System.out.println("=".repeat(20));
				printArticle(getAllTypeBook(3, books));
				continueMenu();
				menu();
			}
			case 5 -> {
				InputManager.limpiarConsola();
				System.out.println("=".repeat(20));
				System.out.println("Pasatiempos:");
				System.out.println("=".repeat(20));
				printArticle(getAllTypeBook(2, books));
				continueMenu();
				menu();
			}
			case 6 -> {
				InputManager.limpiarConsola();
				menuReserveOrRent(1);
				continueMenu();
				menu();
			}
			case 7 -> {
				InputManager.limpiarConsola();
				if (getAllNoAvailables(2).size() != 0) {
					menuEndReserveOrRent(1);
				} else {
					System.out.println("[!] No existen articulos reservados");
				}
				continueMenu();
				menu();
			}
			case 8 -> {
				InputManager.limpiarConsola();
				printAllRentedOrReserve(1, 2);
				printAllRentedOrReserve(3, 2);
				continueMenu();
				menu();
			}
			case 9 -> {
				InputManager.limpiarConsola();
				menuReserveOrRent(2);
				continueMenu();
				menu();
			}
			case 10 -> {
				InputManager.limpiarConsola();
				if (getAllNoAvailables(1).size() != 0) {
					menuEndReserveOrRent(2);
				} else {
					System.out.println("[!] No existen articulos alquilados");
				}
				continueMenu();
				menu();
			}
			case 11 -> {
				InputManager.limpiarConsola();
				printAllRentedOrReserve(3, 1);
				continueMenu();
				menu();
			}
			default -> System.out.println("[!] ERROR: No existe esa opcion");
		}
	}

	/**
	 * Continue message + action needed to continue
	 */
	protected void continueMenu() {
		System.out.println();
		System.out.println("===============================");
		System.out.println("Presiona [ENTER] para continuar");
		System.out.println("===============================");
		InputManager.pressEnter(); 
	}
}
