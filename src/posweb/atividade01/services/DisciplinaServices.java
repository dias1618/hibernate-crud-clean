package posweb.atividade01.services;

import posweb.atividade01.daos.AlunoCRUD;
import posweb.atividade01.daos.AlunoDisciplinaCRUD;
import posweb.atividade01.daos.CursoCRUD;
import posweb.atividade01.daos.DisciplinaCRUD;
import posweb.atividade01.enums.SituacaoDisciplinaEnum;
import posweb.atividade01.pojos.Aluno;
import posweb.atividade01.pojos.AlunoDisciplina;
import posweb.atividade01.pojos.Disciplina;

public class DisciplinaServices {

	public String addAluno(String idDisciplina, String idAluno, String semestre, String situacao) throws Exception {
		Disciplina disciplina = verificarDisciplina(idDisciplina);
		Aluno aluno = verificarAluno(idAluno);
		verificarCursoAluno(aluno.getCurso().getId(), disciplina.getId());
		verificarSemestre(semestre);
		Integer tipoSituacao = verificarSituacao(situacao);
		verificarAlunoDisciplina(aluno, disciplina, semestre);
		disciplina.addAluno(aluno, semestre, tipoSituacao);
		DisciplinaCRUD.atualizar(disciplina);
		return "Aluno adicionado com sucesso";
	}
	
	private Disciplina verificarDisciplina(String idDisciplina) throws Exception{
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
	
	private Aluno verificarAluno(String idAluno) throws Exception{
		try {
			Integer id = Integer.parseInt(idAluno);
			Aluno aluno = AlunoCRUD.buscar(id);
			if(aluno == null) {
				throw new Exception("Aluno não encontrada");
			}
			return aluno;
		}
		catch(NumberFormatException nfe) {
			throw new Exception("Formato do id do aluno está incorreto");
		}
	}
	
	private void verificarCursoAluno(Integer idCurso, Integer idDisciplina) throws Exception {
		if(!CursoCRUD.cursoHasDisciplina(idCurso, idDisciplina)) {
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
	
}
