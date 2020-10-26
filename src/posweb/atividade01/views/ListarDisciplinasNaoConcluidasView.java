package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.interfaces.View;
import posweb.atividade01.services.DisciplinaServices;

public class ListarDisciplinasNaoConcluidasView implements View {

	DisciplinaServices disciplinaServices = new DisciplinaServices();
	
	@Override
	public void generate() {
		try {
			String idAluno = JOptionPane.showInputDialog("Informe o id do aluno");
			String listaDisciplinas = disciplinaServices.listarNaoConcluidas(idAluno);
			JOptionPane.showMessageDialog(null, listaDisciplinas);
		}
		catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao buscar curso", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
