package it.polito.tdp.rivers.db;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.RiverData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public List<Flow> getFlowsFromRiver(River river){

		final String sql = "select day, flow, river " + 
				"from flow " + 
				"where river=? order by day";
		
		List<Flow> flows = new LinkedList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				flows.add(new Flow(res.getDate("day").toLocalDate(), res.getDouble("flow"),river));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		river.setFlows(flows);

		return flows;
		
	}
	
	public RiverData getDataFromRiver(River river) {
		
		final String sql = "select Min(day) as dataInizio, " + 
				"		Max(day) as dataFine, " + 
				"		count(*) as numeroMisurazioni, " + 
				"		avg(flow) as portataMedia " + 
				"from flow " + 
				"where river=?";
		
		RiverData result =null;
		double portataMedia = 0.0;
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());
			ResultSet res = st.executeQuery();

			if(res.next()) {
				portataMedia= res.getDouble("portataMedia");
				result = new RiverData(river, res.getDate("dataInizio").toLocalDate(), res.getDate("dataFine").toLocalDate(),
						res.getInt("numeroMisurazioni"),portataMedia); 
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		river.setFlowAvg(portataMedia);
		
		return result;
	}
}
