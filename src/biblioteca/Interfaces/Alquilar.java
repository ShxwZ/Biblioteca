package biblioteca.Interfaces;

import java.util.Date;

public interface Alquilar {
	void Rent(int timeRented);
	void returnRent();
	String statusRent();
	boolean isRented();
	void setRented(boolean rented);
	Date getDate_rented();
	void setDate_rented(Date i);
	Date getDate_return();
}
