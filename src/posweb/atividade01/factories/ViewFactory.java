package posweb.atividade01.factories;

import posweb.atividade01.interfaces.View;
import posweb.atividade01.views.*;

public class ViewFactory {

	public static final String ADD_CURSO 						= "1";
	public static final String LIST_CURSOS 						= "2";
	public static final String GET_CURSOS 						= "3";
	public static final String ADD_DISCIPLINA 					= "4";
	public static final String LIST_DISCIPLINAS					= "5";
	public static final String INCLUIR_DISCIPLINA_CURSO			= "6";
	public static final String ADD_ALUNO 						= "7";
	public static final String LIST_ALUNOS						= "8";
	public static final String INCLUIR_ALUNO_CURSO				= "9";
	public static final String INCLUIR_ALUNO_DISCIPLINA 		= "10";
	public static final String LISTAR_DISCIPLINAS_NAO_CONCLUIDAS = "11";
	public static final String FECHAR 							= "99";
	
	
	public static View create(String opcao) {
		View view = null;
		
		switch (opcao) {
			case ADD_CURSO:
				view = new AdicionarCursoView();
				break;
			case LIST_CURSOS:
				view = new ListarCursosView();
				break;
			case GET_CURSOS:
				view = new BuscarCursoView();
				break;
			case ADD_DISCIPLINA:
				view = new AdicionarDisciplinaView();
				break;
			case LIST_DISCIPLINAS:
				view = new ListarDisciplinasView();
				break;
			case INCLUIR_DISCIPLINA_CURSO:
				view = new IncluirDisciplinaCursoView();
				break;
			case ADD_ALUNO:
				view = new AdicionarAlunoView();
				break;
			case LIST_ALUNOS:
				view = new ListarAlunosView();
				break;
			case INCLUIR_ALUNO_CURSO:
				view = new IncluirAlunoCursoView();
				break;
			case INCLUIR_ALUNO_DISCIPLINA:
				view = new IncluirAlunoDisciplinaView();
				break;	
			case LISTAR_DISCIPLINAS_NAO_CONCLUIDAS:
				view = new ListarDisciplinasNaoConcluidasView();
				break;	
			case FECHAR:
				System.exit(0);
			default:
				view = new DefaultView();
		}
		
		return view;
		
		
	}
}
