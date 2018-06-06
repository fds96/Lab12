package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class RiverData {
	
	private River river;
	private LocalDate dataInizioMisurazione;
	private LocalDate dataFineMisurazione;
	private int numeroMisurazioni;
	private double flowMedio;
	
	public RiverData(River river, LocalDate dataInizioMisurazione, LocalDate dataFineMisurazione, int numeroMisurazioni,
			double flowMedio) {
		super();
		this.river = river;
		this.dataInizioMisurazione = dataInizioMisurazione;
		this.dataFineMisurazione = dataFineMisurazione;
		this.numeroMisurazioni = numeroMisurazioni;
		this.flowMedio = flowMedio;
	}

	public River getRiver() {
		return river;
	}

	public void setRiver(River river) {
		this.river = river;
	}

	public LocalDate getDataInizioMisurazione() {
		return dataInizioMisurazione;
	}

	public void setDataInizioMisurazione(LocalDate dataInizioMisurazione) {
		this.dataInizioMisurazione = dataInizioMisurazione;
	}

	public LocalDate getDataFineMisurazione() {
		return dataFineMisurazione;
	}

	public void setDataFineMisurazione(LocalDate dataFineMisurazione) {
		this.dataFineMisurazione = dataFineMisurazione;
	}

	public int getNumeroMisurazioni() {
		return numeroMisurazioni;
	}

	public void setNumeroMisurazioni(int numeroMisurazioni) {
		this.numeroMisurazioni = numeroMisurazioni;
	}

	public double getFlowMedio() {
		return flowMedio;
	}

	public void setFlowMedio(double flowMedio) {
		this.flowMedio = flowMedio;
	}
	
	public String toString() {
		return "River: "+ this.river + " Giorno Prima Misurazione = " +this.dataInizioMisurazione +
				" Giorno Ultima Misurazione = " +this.dataFineMisurazione + " Numero Totale Misurazioni = " +
				this.numeroMisurazioni + " Portata Media= " +this.flowMedio;
	}

}
