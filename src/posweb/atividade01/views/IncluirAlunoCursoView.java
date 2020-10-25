package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.daos.AlunoCRUD;
import posweb.atividade01.interfaces.View;

public class IncluirAlunoCursoView implements View{
	@Override
	public void generate() {

		String idCursoAluno	= JOptionPane.showInputDialog("Digite o id do curso");
		String idAluno = JOptionPane.showInputDialog("Digite o id do aluno");
		String mensagemRetorno = AlunoCRUD.addCurso(Integer.parseInt(idCursoAluno), Integer.parseInt(idAluno));
		JOptionPane.showMessageDialog(null, mensagemRetorno);
		
	}
}
