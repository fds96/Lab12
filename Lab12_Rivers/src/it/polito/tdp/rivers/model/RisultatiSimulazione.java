package it.polito.tdp.rivers.model;

public class RisultatiSimulazione {
	
	private int giorniCapienzaInsufficiente;
	private double capienzaMedia;
	public RisultatiSimulazione(int giorniCapienzaInsufficiente, double capienzaMedia) {
		super();
		this.giorniCapienzaInsufficiente = giorniCapienzaInsufficiente;
		this.capienzaMedia = capienzaMedia;
	}
	public int getGiorniCapienzaInsufficiente() {
		return giorniCapienzaInsufficiente;
	}
	public double getCapienzaMedia() {
		return capienzaMedia;
	}
	
	public String toString() {
		return String.format("Giorni capienza insufficiente=%d e capienza media=%.2f", this.giorniCapienzaInsufficiente, this.capienzaMedia);
	}

}
