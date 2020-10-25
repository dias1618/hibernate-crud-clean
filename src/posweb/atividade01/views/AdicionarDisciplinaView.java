package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.daos.DisciplinaCRUD;
import posweb.atividade01.interfaces.View;
import posweb.atividade01.pojos.Disciplina;

public class AdicionarDisciplinaView implements View {

	@Override
	public void generate() {
		String nomeDisciplina = JOptionPane.showInputDialog("Informe o nome do disciplina");
		
		if (nomeDisciplina != null) {
			Disciplina disciplina = new Disciplina(null /*id*/, nomeDisciplina);
			DisciplinaCRUD.salvar(disciplina);
			System.out.println(disciplina);
		}	
	}
}
