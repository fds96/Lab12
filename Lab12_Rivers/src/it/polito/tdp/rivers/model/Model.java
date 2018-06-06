package it.polito.tdp.rivers.model;

import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private List<River> rivers;
	private RiversDAO dao;

	public Model() {
		dao = new RiversDAO();
		rivers=dao.getAllRivers();
	}

	public List<River> getRivers() {
		return rivers;
	}

	public void setRivers(List<River> rivers) {
		this.rivers = rivers;
	}
	
	public RiverData getDataFromRiver(River river) {
		river.setFlows(dao.getFlowsFromRiver(river));
		return dao.getDataFromRiver(river);
	}
	
	public RisultatiSimulazione simula(double k, River river) {
		Simulazione simulazione = new Simulazione (k,river);
		simulazione.initialize();
		simulazione.run();
		return new RisultatiSimulazione(simulazione.getGiorniCapienzaInsufficiente(), simulazione.getCapienzaMedia());
	}

}
