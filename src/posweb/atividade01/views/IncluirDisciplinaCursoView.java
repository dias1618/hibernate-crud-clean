package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.daos.CursoCRUD;
import posweb.atividade01.interfaces.View;

public class IncluirDisciplinaCursoView implements View{

	@Override
	public void generate() {

		String idCurso 		= JOptionPane.showInputDialog("Digite o id do curso");
		String idDisciplina = JOptionPane.showInputDialog("Digite o id do disciplina");
		String mensagem = CursoCRUD.addDisciplina(Integer.parseInt(idCurso), Integer.parseInt(idDisciplina));
		JOptionPane.showMessageDialog(null, mensagem);
			
	}
}
