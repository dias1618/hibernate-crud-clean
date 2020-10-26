package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.interfaces.View;
import posweb.atividade01.services.DisciplinaServices;

public class ListarDisciplinasView implements View{

	DisciplinaServices disciplinaServices = new DisciplinaServices();
	
	@Override
	public void generate() {
		String listaDisciplinasToString = disciplinaServices.listar();
		JOptionPane.showMessageDialog(null, listaDisciplinasToString);
	}
}
