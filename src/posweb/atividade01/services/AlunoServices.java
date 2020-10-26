package posweb.atividade01.services;

import java.util.List;

import posweb.atividade01.cruds.AlunoCRUD;
import posweb.atividade01.pojos.Aluno;

public class AlunoServices {
	
	public String salvar(Aluno aluno) throws Exception{
		validarNomeAluno(aluno.getNome());
		AlunoCRUD.salvar(aluno);
		return "Aluno salvo com sucesso";
	}
	
	private void validarNomeAluno(String nome) throws Exception {
		if(nome == null || nome.trim().equals("")) {
			throw new Exception("Nome do aluno não pode ser vazio");
		}
	}
	
	public Aluno verificarAluno(String idAluno) throws Exception{
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
	
	public String listar() {
		String listaAlunos = "";
		List<Aluno> alunos = AlunoCRUD.listar();
		for (int i=0; i<alunos.size(); i++) {
			listaAlunos += (i!=0 ? "\n" : "");
			listaAlunos += alunos.get(i).toString();
		}
		return listaAlunos;
	}
}
