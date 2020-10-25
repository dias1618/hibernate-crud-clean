package posweb.atividade01.views;

import java.util.List;

import javax.swing.JOptionPane;

import posweb.atividade01.daos.CursoCRUD;
import posweb.atividade01.interfaces.View;
import posweb.atividade01.pojos.Curso;

public class ListarCursosView implements View{

	@Override
	public void generate() {
		String listaToString = "";
		
		List<Curso> cursos = CursoCRUD.listar();
		for (int i=0; i<cursos.size(); i++) {
			listaToString += (i!=0 ? "\n" : "");
			listaToString += cursos.get(i).toString();
		}
		
		JOptionPane.showMessageDialog(null, listaToString);
		
	}
}
