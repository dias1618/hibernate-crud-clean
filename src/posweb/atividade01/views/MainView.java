package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.factories.ViewFactory;
import posweb.atividade01.interfaces.View;

public class MainView implements View{

	@Override
	public void generate() {
		String opcao = "";
		
		while (true) {
			opcao = JOptionPane.showInputDialog(
					"Informe o código da operação\n"+
					"1 	- Adicionar novo curso\n"+
					"2 	- Listas todos os cursos\n"+
					"3 	- Buscar um curso\n"+
					"4 	- Adicionar nova disciplina\n"+
					"5 	- Listas todos as disciplians\n"+
					"6 	- Incluir disciplina em curso\n"+
					"7 	- Adicionar novo aluno\n"+
					"8 	- Listas todos os alunos\n"+
					"9 	- Incluir aluno em curso\n"+
					"10	- Incluir aluno em disciplina\n"+
					"11	- Listar disciplinas não concluídas\n"+
					"99 - Fechar\n"
			);
			if(opcao == null)
				break;
			
			View view = ViewFactory.create(opcao);
			view.generate();
		}	
	}
}
