package it.polito.tdp.corsi.model;

import java.util.*;

import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDao;
	public Model() {
		this.corsoDAO= new CorsoDAO();
		this.studenteDao=new StudenteDAO();
	}
	public List<Corso> getCorsiByPeriodo(int periodo){
		return this.corsoDAO.getCorsoByPeriodo(periodo);
		
	}
	public Map<Corso, Integer> getIscritti(int periodo){
		return this.corsoDAO.getIscritti(periodo);
	}
	
	public List<Studente> getStudente(String codins){
		return this.studenteDao.getStudentiByCorso( codins);
	}
	
	public List<Divisione> getDivisioneStudenti(String codins){
		return this.studenteDao.getDivisioneStudenti(codins);
	}
}
