package posweb.atividade01.pojos;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "aluno_disciplina")
public class AlunoDisciplina implements Serializable{
	private static final long serialVersionUID = 5883196118410235190L;

	@Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idAluno")
    private Aluno aluno;
 
	@Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idDisciplina")
    private Disciplina disciplina;

	@Id
    @Column(name = "semestre")
	private String semestre;

	@Column
	private Integer situacao;
	
	public AlunoDisciplina() {
		// TODO Auto-generated constructor stub
	}

	public AlunoDisciplina(Aluno aluno, Disciplina disciplina, String semestre, Integer situacao) {
		super();
		this.aluno = aluno;
		this.disciplina = disciplina;
		this.semestre = semestre;
		this.situacao = situacao;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public String getSemestre() {
		return semestre;
	}
	
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	
	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "AlunoDisciplina [aluno=" + aluno + ", disciplina=" + disciplina + ", semestre=" + semestre
				+ ", situacao=" + situacao + "]";
	}

	
}
