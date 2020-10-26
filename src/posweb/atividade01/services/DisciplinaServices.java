package posweb.atividade01.services;

import java.util.List;

import posweb.atividade01.cruds.AlunoDisciplinaCRUD;
import posweb.atividade01.cruds.CursoCRUD;
import posweb.atividade01.cruds.DisciplinaCRUD;
import posweb.atividade01.enums.SituacaoDisciplinaEnum;
import posweb.atividade01.pojos.Aluno;
import posweb.atividade01.pojos.AlunoDisciplina;
import posweb.atividade01.pojos.Curso;
import posweb.atividade01.pojos.Disciplina;

public class DisciplinaServices {

	AlunoServices alunoServices = new AlunoServices();
	
	public String salvar(Disciplina disciplina) throws Exception{
		validarNomeDisciplina(disciplina.getNome());
		DisciplinaCRUD.salvar(disciplina);
		return "Disciplina salvo com sucesso";
	}

	private void validarNomeDisciplina(String nome) throws Exception {
		if(nome == null || nome.trim().equals("")) {
			throw new Exception("Nome do disciplina não pode ser vazio");
		}
	}
	
	public String addAluno(String idDisciplina, String idAluno, String semestre, String situacao) throws Exception {
		Disciplina disciplina = verificarDisciplina(idDisciplina);
		Aluno aluno = alunoServices.verificarAluno(idAluno);
		verificarCursoAluno(aluno.getCurso().getId(), disciplina.getId());
		verificarSemestre(semestre);
		Integer tipoSituacao = verificarSituacao(situacao);
		verificarAlunoDisciplina(aluno, disciplina, semestre);
		disciplina.addAluno(aluno, semestre, tipoSituacao);
		DisciplinaCRUD.atualizar(disciplina);
		return "Aluno adicionado com sucesso";
	}
	
	public Disciplina verificarDisciplina(String idDisciplina) throws Exception{
		try {
			Integer id = Integer.parseInt(idDisciplina);
			Disciplina disciplina = DisciplinaCRUD.buscar(id);
			if(disciplina == null) {
				throw new Exception("Disciplina não encontrada");
			}
			return disciplina;
		}
		catch(NumberFormatException nfe) {
			throw new Exception("Formato do id da disciplina está incorreto");
		}
	}
	
	private void verificarCursoAluno(Integer idCurso, Integer idDisciplina) throws Exception {
		boolean alunoPertenceAoCurso = false;
		Curso curso = CursoCRUD.buscar(idCurso);
		for(Disciplina disciplina : curso.getDisciplinas()) {
			if(disciplina.getId() == idDisciplina) {
				alunoPertenceAoCurso = true;
			}
		}
		
		if(!alunoPertenceAoCurso) {
			throw new Exception("A Disciplina não está vinculada ao curso do aluno");
		}
	}
	
	private void verificarSemestre(String semestre) throws Exception{
		String erroFormato = "Formato do semestre incorreto";
		try {
		
			if(semestre.length() != 5) {
				throw new Exception(erroFormato);
			}
		
			Integer ano = Integer.parseInt(semestre.substring(0, 4));
			Integer parteSemestre = Integer.parseInt(semestre.substring(4));
			
			if(parteSemestre != 1 && parteSemestre != 2)
				throw new Exception("O quinto dígito do semestre deve ser 1 ou 2");
			
			if(ano < 1970 || ano > 2100)
				throw new Exception("Os quatro primeiros dígitos devem perfazer um ano válido");
		}
		catch(NumberFormatException nfe) {
			throw new Exception(erroFormato);
		}
	}
	
	private Integer verificarSituacao(String situacao) throws Exception{
		try {
			Integer tipoSituacao = Integer.parseInt(situacao);
			boolean pertenceAoConjunto = false;
			for(Integer index=0; index < SituacaoDisciplinaEnum.values().length; index++) {
				if(tipoSituacao == SituacaoDisciplinaEnum.values()[index].getKey()) {
					pertenceAoConjunto = true;
				}
			}
			
			if(!pertenceAoConjunto)
				throw new Exception("Situação fora do conjunto possível");
			
			return tipoSituacao;
		}
		catch(NumberFormatException nfe) {
			throw new Exception("Formato da situação está incorreto");
		}
	}
	
	private void verificarAlunoDisciplina(Aluno aluno, Disciplina disciplina, String semestre) throws Exception {
		AlunoDisciplina alunoDisciplina = AlunoDisciplinaCRUD.buscar(aluno.getId(), disciplina.getId(), semestre);
		if(alunoDisciplina != null)
			throw new Exception("Aluno já cadastrado na disciplina");
	}
	
	public String listar() {
		String listaDisciplinas = "";
		
		List<Disciplina> disciplinas = DisciplinaCRUD.listar();
		for (int i=0; i<disciplinas.size(); i++) {
			listaDisciplinas += (i!=0 ? "\n" : "");
			listaDisciplinas += disciplinas.get(i).toString();
		}
		
		return listaDisciplinas;
	}

	public String listarNaoConcluidas(String idAluno) throws Exception{
		String listaDisciplinas = "";
		Aluno aluno = alunoServices.verificarAluno(idAluno);
		List<AlunoDisciplina> alunoDisciplinas = DisciplinaCRUD.listarNaoConcluidas(aluno.getId());
		for (int i=0; i<alunoDisciplinas.size(); i++) {
			listaDisciplinas += (i!=0 ? "\n" : "");
			listaDisciplinas += alunoDisciplinas.get(i).getSemestre() + " - " + alunoDisciplinas.get(i).getDisciplina().getNome();
		}
		
		return "O aluno " + aluno.getNome() + " possui os seguintes semestres/disciplinas não concluídos:\n" + listaDisciplinas;
	}
}
