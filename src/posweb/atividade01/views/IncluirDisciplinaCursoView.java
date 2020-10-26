package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.cruds.CursoCRUD;
import posweb.atividade01.interfaces.View;
import posweb.atividade01.services.CursoServices;

public class IncluirDisciplinaCursoView implements View{

	CursoServices cursoServices = new CursoServices();
	
	@Override
	public void generate() {

		try {
			String idCurso 		= JOptionPane.showInputDialog("Digite o id do curso");
			String idDisciplina = JOptionPane.showInputDialog("Digite o id do disciplina");
			String mensagem = cursoServices.addDisciplina(idCurso, idDisciplina);
			JOptionPane.showMessageDialog(null, mensagem);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao incluir disciplina", JOptionPane.ERROR_MESSAGE);
		}
			
	}
}
