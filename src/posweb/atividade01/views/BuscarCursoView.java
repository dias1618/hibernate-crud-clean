package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.interfaces.View;
import posweb.atividade01.pojos.Curso;
import posweb.atividade01.services.CursoServices;

public class BuscarCursoView implements View {

	CursoServices cursoServices = new CursoServices();
	
	@Override
	public void generate() {
		try {
			String id = JOptionPane.showInputDialog("Informe o id do curso");
			Curso curso = cursoServices.verificarCurso(id);
			System.out.println(curso.getDisciplinas());
			String nomeDisciplinas = cursoServices.getNomeDisciplinas(curso);
			String nomeAlunos = cursoServices.getNomeAlunos(curso);
			JOptionPane.showMessageDialog(null, 
				"O nome do curso é " + curso.getNome() + "\n" +
				"Suas disciplinas são: " + nomeDisciplinas + "\n" +
				"Seus alunos são: " + nomeAlunos + "\n"
			);
			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao buscar curso", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
