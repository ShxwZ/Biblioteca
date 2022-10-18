package biblioteca.Articulos;

public abstract class Articulo {
	protected int codeArticle;
	protected String titleBook;
	protected int publishingYear;
	
	
	
	public Articulo(int codeArticle, String titleBook, int publishingYear) {
		this.codeArticle = codeArticle;
		this.titleBook = titleBook;
		this.publishingYear = publishingYear;
	}
	
	
	/*
	 * Print information Book
	 */
	
	public String toString() {
		return "Código de articulo: " + codeArticle +
				"\nTitulo del libro: " + titleBook +
				"\nAño de publicación: " + publishingYear;
	}
	
	/*
	 * Getters n Setters
	 */

	public int getCodeArticle() {
		return codeArticle;
	}
	public void setCodeArticle(int codeArticle) {
		this.codeArticle = codeArticle;
	}
	public String getTitleBook() {
		return titleBook;
	}
	public void setTitleBook(String titleBook) {
		this.titleBook = titleBook;
	}
	public int getPublishingYear() {
		return publishingYear;
	}
	public void setPublishingYear(int publishingYear) {
		this.publishingYear = publishingYear;
	}
	
	
}
