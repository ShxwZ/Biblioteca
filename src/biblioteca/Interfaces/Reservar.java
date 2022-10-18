package biblioteca.Interfaces;

public interface Reservar {

	void Reserved();
	void notReserved();
	String statusReserve();
	boolean isReserved();
	void setReserved(boolean reserved);
}
