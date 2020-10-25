package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.daos.CursoCRUD;
import posweb.atividade01.interfaces.View;
import posweb.atividade01.pojos.Curso;

public class AdicionarCursoView implements View{

	@Override
	public void generate() {
		String nome = JOptionPane.showInputDialog("Informe o nome do curso");
		
		if (nome != null) {
			Curso curso = new Curso(null /*id*/, nome);
			CursoCRUD.salvar(curso);
		}
		
	}
}
