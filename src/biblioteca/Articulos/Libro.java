package biblioteca.Articulos;

import java.util.Calendar;
import java.util.Date;

import biblioteca.Interfaces.Alquilar;
import biblioteca.Interfaces.Reservar;

public class Libro extends Articulo implements Reservar, Alquilar {

	private boolean rented, reserved;
	private Date date_rented, date_return;

	/*
	 * Constructors
	 */

	public Libro(int codeBook, String titleBook, int publishingYear) {
		super(codeBook, titleBook, publishingYear);
		setRented(false);
		setReserved(false);
	}

	/*
	 * toString
	 */

	@Override
	public String toString() {
		return super.toString() +
				"\n" + statusRent() +
				"\n" + statusReserve() +
				(rented ?
				"\nFecha alquilado: "+getDate_rented() +
				"\nFecha devolucion: "+ getDate_return():"");
	}

	/*
	 * Getters n Setters
	 */

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public Date getDate_rented() {
		return date_rented;
	}

	public void setDate_rented(Date i) {
		this.date_rented = i;
	}

	public Date getDate_return() {
		return date_return;
	}

	public void setDate_return(Date date_return) {
		this.date_return = date_return;
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
	public void Rent(int timeRented) {
		setRented(true);
		Calendar cal = Calendar.getInstance();
		setDate_rented(cal.getTime());
		cal.add(Calendar.MONTH, timeRented);
		setDate_return(cal.getTime());
	}

	@Override
	public void returnRent() {
		setRented(false);
		setDate_rented(null);
		setDate_rented(null);
	}

	@Override
	public void Reserved() {
		setReserved(true);
	}

	@Override
	public void notReserved() {
		setReserved(false);
	}

	@Override
	public String statusRent() {
		return "Estado alquiler: " + (isRented() ? "Alquilado" : 
										isReserved() ? "No disponible" : "Disponible");
	}

	@Override
	public String statusReserve() {
		return "Estado reserva: " + (isReserved() ? "Reservado" :
										isRented() ? "No disponible" : "Disponible");
	}

}
