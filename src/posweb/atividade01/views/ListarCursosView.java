package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.interfaces.View;
import posweb.atividade01.services.CursoServices;

public class ListarCursosView implements View{

	CursoServices cursoServices = new CursoServices();
	
	@Override
	public void generate() {
		String listaToString = cursoServices.listar();
		JOptionPane.showMessageDialog(null, listaToString);
	}
}
