package posweb.atividade01.views;

import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import posweb.atividade01.daos.CursoCRUD;
import posweb.atividade01.interfaces.View;
import posweb.atividade01.pojos.Aluno;
import posweb.atividade01.pojos.Curso;
import posweb.atividade01.pojos.Disciplina;

public class BuscarCursoView implements View {

	@Override
	public void generate() {
		String id = JOptionPane.showInputDialog("Informe o id do curso");
		Curso curso = CursoCRUD.buscar(Integer.parseInt(id));
		
		if (curso != null) {
			String nomeDisciplinas = getDisciplinas(curso);
			String nomeAlunos = getAlunos(curso);
			JOptionPane.showMessageDialog(null, 
				"O nome do curso é " + curso.getNome() + "\n" +
				"Suas disciplinas são: " + nomeDisciplinas + "\n" +
				"Seus alunos são: " + nomeAlunos + "\n"
			);
		}
		else
			JOptionPane.showMessageDialog(null, "Inexistência de cursos com o id informado!");						
		
	}
	
	private String getDisciplinas(Curso curso) {
		List<Disciplina> disciplinas = curso.getDisciplinas();
		String nomeDisciplinas = "";
		for(Disciplina disciplina : disciplinas) {
			nomeDisciplinas += disciplina.getNome() + ", ";
		}
		if(!disciplinas.isEmpty())
			nomeDisciplinas = nomeDisciplinas.substring(0, nomeDisciplinas.length()-2);
		else
			nomeDisciplinas = "Nenhuma disciplina cadastrada";
		return nomeDisciplinas;
	}

	private String getAlunos(Curso curso) {
		Set<Aluno> alunos = curso.getAlunos();
		String nomeAlunos = "";
		for(Aluno aluno : alunos) {
			nomeAlunos += aluno.getNome() + ", ";
		}
		if(!alunos.isEmpty())
			nomeAlunos = nomeAlunos.substring(0, nomeAlunos.length()-2);
		else
			nomeAlunos = "Nenhum aluno cadastrado";
		return nomeAlunos;
	}
}
