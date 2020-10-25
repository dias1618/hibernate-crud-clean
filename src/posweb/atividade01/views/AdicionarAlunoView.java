package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.daos.AlunoCRUD;
import posweb.atividade01.interfaces.View;
import posweb.atividade01.pojos.Aluno;

public class AdicionarAlunoView implements View{
	@Override
	public void generate() {
		String nomeAluno = JOptionPane.showInputDialog("Informe o nome do aluno");
		
		if (nomeAluno != null) {
			Aluno aluno = new Aluno(null /*id*/, nomeAluno);
			AlunoCRUD.salvar(aluno);
			System.out.println(aluno);
		}
			
	}
}
