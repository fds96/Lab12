package it.polito.tdp.rivers.model;

import java.util.PriorityQueue;

public class Simulazione {
	
	private double capienzaTotale;
	private double fattoreScala;
	private River river;
	private double flussoMinimo;
	private float probabilitaFlussoEccessivo;
	private double capienzaAttuale;
	private double flussoEccessivo;
	private PriorityQueue<Event> queue;
	private double capienzaMedia;
	private int giorniCapienzaSufficiente;
	private int giorniCapienzaInsufficiente;
	private int giorniTracimazione;
	
	
	public Simulazione(double k, River river) {
		this.fattoreScala=k;
		this.river=river;
		this.capienzaTotale=(this.fattoreScala*this.river.getFlowAvg()*30*3600*24);
		this.flussoMinimo=0.8*this.river.getFlowAvg();
		this.flussoEccessivo=this.flussoMinimo*10;
		this.probabilitaFlussoEccessivo=(float) 0.05;
		this.capienzaAttuale=this.capienzaTotale/2;
		this.queue=new PriorityQueue<>();
	}
	
	
	
	public double getCapienzaMedia() {
		return capienzaMedia;
	}

	public int getGiorniCapienzaSufficiente() {
		return giorniCapienzaSufficiente;
	}
	
	public int getGiorniCapienzaInsufficiente() {
		return giorniCapienzaInsufficiente;
	}
	public int getGiorniTracimazione() {
		return giorniTracimazione;
	}
	
	public void initialize() {
		queue.clear();
		
		capienzaMedia=0.0;
		giorniCapienzaSufficiente=0;
		giorniCapienzaInsufficiente=0;
		giorniTracimazione=0;
		
		Double outputFlow=0.0;
		Double inputFlow=0.0;
		for(Flow f: this.river.getFlows()) {
			
			inputFlow=f.getFlow();
			
			
			//Calcolo il flusso in uscita
			if(Math.random()<this.probabilitaFlussoEccessivo) {	//Caso con flusso eccessivo
				outputFlow=this.flussoEccessivo;
			}
			else outputFlow=this.flussoMinimo; //Caso con flusso normale
			
			//Vedo se la capacità del bacino è adeguata a sopportare il flusso
			
			if(inputFlow<=outputFlow) { //Se il flusso in uscita è maggiore al flusso in entrata
										//Diminuisco la capienza della differenza tra i flussi nel giorno
				this.capienzaAttuale-=(outputFlow-inputFlow)*3600*24;
				if(this.capienzaAttuale<0) { //Se la capienza è negativa, la metto a zero e genero un evento flusso insufficiente
					this.capienzaAttuale=0;
					queue.add(new Event(f,outputFlow,capienzaAttuale,EventType.CAPACITA_INSUFFICIENTE));
				}
				else //Altrimenti il flusso è sufficiente a gestire la domanda
					queue.add(new Event(f,outputFlow,capienzaAttuale,EventType.CAPACITA_SUFFICIENTE));
			}
			else {// Se il flusso in uscita è minore del flusso in entrata incremento la capienza
				this.capienzaAttuale+=(inputFlow-outputFlow)*24*3600;
				if(this.capienzaAttuale>this.capienzaTotale) { //Se la capienza è maggiore di quella totale
																// Metto la capienza pari a quella massima 
																//e genero evento Tracimazione
					this.capienzaAttuale=this.capienzaTotale;
					queue.add(new Event(f,outputFlow,capienzaAttuale,EventType.TRACIMAZIONE));
				}
				else { //Se la capienza è minore di quella totale il flusso è sufficiente per gestire la domanda
					queue.add(new Event(f,outputFlow,capienzaAttuale,EventType.CAPACITA_SUFFICIENTE));
				}
			}
		}
		
	}

	public void run() {
		Event e;
		while((e = queue.poll())!=null) {
			this.processEvent(e);
			}
		this.capienzaMedia/=this.river.getFlows().size();
	}

	private void processEvent(Event e) {
		switch(e.getEvento()) {
		case CAPACITA_SUFFICIENTE:
			this.capienzaMedia+=e.getCapienzaAttuale();
			this.giorniCapienzaSufficiente++;
			break;
			
		case CAPACITA_INSUFFICIENTE:
			this.capienzaMedia+=e.getCapienzaAttuale();
			this.giorniCapienzaInsufficiente++;
			break;

		case TRACIMAZIONE:
			this.capienzaMedia+=e.getCapienzaAttuale();
			this.giorniTracimazione++;
			break;

		}
	}
}
