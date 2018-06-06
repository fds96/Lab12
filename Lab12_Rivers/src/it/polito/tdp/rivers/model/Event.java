package it.polito.tdp.rivers.model;

public class Event implements Comparable<Event>{
	
	private Flow inputFlow;
	private double outputFlow;
	private double capienzaAttuale;
	private EventType evento;
	
	public Event(Flow inputFlow, double outputFlow, double capienzaAttuale, EventType evento) {
		super();
		this.inputFlow = inputFlow;
		this.outputFlow = outputFlow;
		this.capienzaAttuale = capienzaAttuale;
		this.evento = evento;
	}

	public Flow getInputFlow() {
		return inputFlow;
	}

	public double getOutputFlow() {
		return outputFlow;
	}

	public double getCapienzaAttuale() {
		return capienzaAttuale;
	}

	public EventType getEvento() {
		return evento;
	}

	@Override
	public int compareTo(Event other) {
		return this.inputFlow.compareTo(other.getInputFlow());
	}
	
	public String toString() {
		return this.evento.toString();
	}


}
