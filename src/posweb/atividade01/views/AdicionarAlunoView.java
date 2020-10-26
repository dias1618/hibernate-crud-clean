package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.cruds.AlunoCRUD;
import posweb.atividade01.interfaces.View;
import posweb.atividade01.pojos.Aluno;
import posweb.atividade01.services.AlunoServices;

public class AdicionarAlunoView implements View{
	
	AlunoServices alunoServices = new AlunoServices();
	
	@Override
	public void generate() {
		try {
			String nomeAluno = JOptionPane.showInputDialog("Informe o nome do aluno");
			
			if (nomeAluno != null) {
				Aluno aluno = new Aluno(null /*id*/, nomeAluno);
				String mensagemRetorno = alunoServices.salvar(aluno);
				JOptionPane.showMessageDialog(null, mensagemRetorno);
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao adicionar aluno", JOptionPane.ERROR_MESSAGE);
		}
	}
}
