package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.cruds.CursoCRUD;
import posweb.atividade01.interfaces.View;
import posweb.atividade01.pojos.Curso;
import posweb.atividade01.services.CursoServices;

public class AdicionarCursoView implements View{

	CursoServices cursoServices = new CursoServices();
	
	@Override
	public void generate() {
		try {
			String nome = JOptionPane.showInputDialog("Informe o nome do curso");
			
			if (nome != null) {
				Curso curso = new Curso(null /*id*/, nome);
				String mensagemRetorno = cursoServices.salvar(curso);
				JOptionPane.showMessageDialog(null, mensagemRetorno);
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao adicionar curso", JOptionPane.ERROR_MESSAGE);
		}
	}
}
