package biblioteca.Articulos;

import biblioteca.Interfaces.Reservar;

public class Revistas extends Articulo implements Reservar{
	private boolean reserved;
	private int numberMagazine;
	/*
	 * Constructor
	 */
	public Revistas(int codeBook, String titleBook, int publishingYear,int numberMagazine) {
		super(codeBook, titleBook, publishingYear);
		this.numberMagazine = numberMagazine;
		setReserved(false);
	}
	
	/*
	 * toString
	 */
	@Override
	public String toString() {
		return super.toString()+"\nNumero de revista: " + getNumberMagazine() +
				"\n" + statusReserve();
	}
	
	/*
	 * Getters n Setters
	 */
	public int getNumberMagazine() {
		return numberMagazine;
	}

	public void setNumberMagazine(int numberMagazine) {
		this.numberMagazine = numberMagazine;
	}

	public boolean isReserved() {
		return reserved;
	}


	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	/*
	 * Implemented methods
	 */

	@Override
	public void Reserved() {
		setReserved(true);
	}


	@Override
	public void notReserved() {
		setReserved(false);
	}

	@Override
	public String statusReserve() {
		return isReserved() ? "Estado reserva: Reservado":"Estado reserva: Disponible";
	}


	
	
	
}
