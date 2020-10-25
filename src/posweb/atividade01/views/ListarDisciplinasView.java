package posweb.atividade01.views;

import java.util.List;

import javax.swing.JOptionPane;

import posweb.atividade01.daos.DisciplinaCRUD;
import posweb.atividade01.interfaces.View;
import posweb.atividade01.pojos.Disciplina;

public class ListarDisciplinasView implements View{

	@Override
	public void generate() {
		String listaDisciplinasToString = "";
		
		List<Disciplina> disciplinas = DisciplinaCRUD.listar();
		for (int i=0; i<disciplinas.size(); i++) {
			listaDisciplinasToString += (i!=0 ? "\n" : "");
			listaDisciplinasToString += disciplinas.get(i).toString();
		}
		
		JOptionPane.showMessageDialog(null, listaDisciplinasToString);
	}
}
