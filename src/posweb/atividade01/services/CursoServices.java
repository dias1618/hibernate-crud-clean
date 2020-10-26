package posweb.atividade01.services;

import java.util.List;
import java.util.Set;

import posweb.atividade01.cruds.AlunoCRUD;
import posweb.atividade01.cruds.CursoCRUD;
import posweb.atividade01.pojos.Aluno;
import posweb.atividade01.pojos.Curso;
import posweb.atividade01.pojos.Disciplina;

public class CursoServices {

	DisciplinaServices disciplinaServices = new DisciplinaServices();
	AlunoServices alunoServices = new AlunoServices();
	
	public String salvar(Curso curso) throws Exception{
		validarNomeCurso(curso.getNome());
		CursoCRUD.salvar(curso);
		return "Curso salvo com sucesso";
	}

	private void validarNomeCurso(String nome) throws Exception {
		if(nome == null || nome.trim().equals("")) {
			throw new Exception("Nome do curso não pode ser vazio");
		}
	}
	
	public String addDisciplina(String cursoId, String disciplinaId) throws Exception{
		Curso curso = verificarCurso(cursoId);
		Disciplina disciplina = disciplinaServices.verificarDisciplina(disciplinaId);
		curso.addDisciplina(disciplina);
		CursoCRUD.atualizar(curso);
		return "Disciplina adicionada com sucesso";
 	}
	

	public String addAluno(String cursoId, String alunoId) throws Exception {
		Curso curso = verificarCurso(cursoId);
		Aluno aluno = alunoServices.verificarAluno(alunoId);
		aluno.setCurso(curso);
		AlunoCRUD.atualizar(aluno);
		return "Aluno adicionada com sucesso";
	}


	public Curso verificarCurso(String idCurso) throws Exception{
		try {
			Integer id = Integer.parseInt(idCurso);
			Curso curso = CursoCRUD.buscar(id);
			if(curso == null) {
				throw new Exception("Curso não encontrado");
			}
			return curso;
		}
		catch(NumberFormatException nfe) {
			throw new Exception("Formato do id da curso está incorreto");
		}
	}
		

	public String getNomeDisciplinas(Curso curso) {
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

	public String getNomeAlunos(Curso curso) {
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
	
	public String listar() {
		String lista = "";
		
		List<Curso> cursos = CursoCRUD.listar();
		for (int i=0; i<cursos.size(); i++) {
			lista += (i!=0 ? "\n" : "");
			lista += cursos.get(i).toString();
		}
		
		return lista;
	}
}
