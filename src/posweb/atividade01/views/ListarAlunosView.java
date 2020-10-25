package posweb.atividade01.views;

import java.util.List;

import javax.swing.JOptionPane;

import posweb.atividade01.daos.AlunoCRUD;
import posweb.atividade01.interfaces.View;
import posweb.atividade01.pojos.Aluno;

public class ListarAlunosView implements View{
	@Override
	public void generate() {
		String listaAlunosToString = "";
		
		List<Aluno> alunos = AlunoCRUD.listar();
		for (int i=0; i<alunos.size(); i++) {
			listaAlunosToString += (i!=0 ? "\n" : "");
			listaAlunosToString += alunos.get(i).toString();
		}
		
		JOptionPane.showMessageDialog(null, listaAlunosToString);
	}
}
