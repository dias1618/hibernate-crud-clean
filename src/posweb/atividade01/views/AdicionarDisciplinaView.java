package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.cruds.DisciplinaCRUD;
import posweb.atividade01.interfaces.View;
import posweb.atividade01.pojos.Disciplina;
import posweb.atividade01.services.DisciplinaServices;

public class AdicionarDisciplinaView implements View {

	DisciplinaServices disciplinaServices = new DisciplinaServices();
	
	@Override
	public void generate() {
		try {
			String nomeDisciplina = JOptionPane.showInputDialog("Informe o nome do disciplina");
			
			if (nomeDisciplina != null) {
				Disciplina disciplina = new Disciplina(null /*id*/, nomeDisciplina);
				String mensagemRetorno = disciplinaServices.salvar(disciplina);
				JOptionPane.showMessageDialog(null, mensagemRetorno);
			}	
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao adicionar disciplina", JOptionPane.ERROR_MESSAGE);
		}
	}
}
