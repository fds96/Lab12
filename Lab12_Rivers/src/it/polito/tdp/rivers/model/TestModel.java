package it.polito.tdp.rivers.model;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		River river = model.getRivers().get(3);
		System.out.println(model.getDataFromRiver(river));
		System.out.println(model.simula(10, river));
		
	}

}
