package it.polito.tdp.rivers.db;

import java.util.List;

import it.polito.tdp.rivers.model.River;

public class TestRiversDAO {

	public static void main(String[] args) {
		RiversDAO dao = new RiversDAO();
		List<River> rivers = dao.getAllRivers();
		System.out.println(rivers);
		System.out.println(dao.getFlowsFromRiver(rivers.get(3)).size());
		System.out.println(dao.getDataFromRiver(rivers.get(3)));
	}

}
