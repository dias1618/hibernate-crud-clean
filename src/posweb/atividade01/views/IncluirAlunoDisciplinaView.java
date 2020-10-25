package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.daos.DisciplinaCRUD;
import posweb.atividade01.interfaces.View;
import posweb.atividade01.services.DisciplinaServices;

public class IncluirAlunoDisciplinaView implements View{
	@Override
	public void generate() {
		try {
			DisciplinaServices disciplinaServices = new DisciplinaServices();
			
			String idDisciplina	= JOptionPane.showInputDialog("Digite o id do Disciplina");
			String idAluno = JOptionPane.showInputDialog("Digite o id do aluno");
			String semestre = JOptionPane.showInputDialog("Digite o semestre no formato yyyyn, onde yyyy é o ano e n representa o semestre (1 ou 2)");
			String situacao = JOptionPane.showInputDialog("Digite a situação (0 - Em Curso, 1 - Aprovado, 2 - Reprovado ou 3 - Trancado)");
			String mensagemRetornoAlunoDisciplina = disciplinaServices.addAluno(idDisciplina, idAluno, semestre, situacao);
			JOptionPane.showMessageDialog(null, mensagemRetornoAlunoDisciplina);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao incluir aluno", JOptionPane.ERROR_MESSAGE);
		}
	}
}
