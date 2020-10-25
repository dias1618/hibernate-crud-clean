package posweb.atividade01.pojos;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "curso")
public class Curso {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
	@Column
	private String nome;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "curso_disciplina",
	    joinColumns = @JoinColumn(name = "idCurso"),
	    inverseJoinColumns = @JoinColumn(name = "idDisciplina")
	)
	private List<Disciplina> disciplinas = new ArrayList<>();
	
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "curso")
	private Set<Aluno> alunos = new HashSet<Aluno>();
	
	public Curso() {
		super();		
	}
	
	public Curso(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}
	
	public void addDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }
 
    public void removeDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
    }

	public void addAluno(Aluno aluno) {
        alunos.add(aluno);
    }
 
    public void removeAluno(Aluno aluno) {
        alunos.remove(aluno);
    }
	
	@Override
	public String toString() {
		return "Curso [id=" + id + ", nome=" + nome +"]";
	}
	
}
