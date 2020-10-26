package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.cruds.AlunoCRUD;
import posweb.atividade01.interfaces.View;
import posweb.atividade01.services.AlunoServices;
import posweb.atividade01.services.CursoServices;

public class IncluirAlunoCursoView implements View{
	
	CursoServices cursoServices = new CursoServices();
	
	@Override
	public void generate() {
		try {
			String idCurso	= JOptionPane.showInputDialog("Digite o id do curso");
			String idAluno = JOptionPane.showInputDialog("Digite o id do aluno");
			String mensagemRetorno = cursoServices.addAluno(idCurso, idAluno);
			JOptionPane.showMessageDialog(null, mensagemRetorno);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao incluir curso", JOptionPane.ERROR_MESSAGE);
		}
	}
}
