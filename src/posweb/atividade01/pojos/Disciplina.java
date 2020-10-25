package posweb.atividade01.pojos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "disciplina")
public class Disciplina {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;

	@Column
	private String nome;

    @ManyToMany(mappedBy = "disciplinas")
    private Set<Curso> cursos = new HashSet<>();
 
    @OneToMany(
        mappedBy = "disciplina",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    private Set<AlunoDisciplina> alunos = new HashSet<>();
 
    
	public Disciplina() {
		super();
	}
	
	public Disciplina(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public void addAluno(Aluno aluno, String semestre, Integer situacao) {
		AlunoDisciplina alunoDisciplina = new AlunoDisciplina(aluno, this, semestre, situacao);
		alunos.add(alunoDisciplina);
        aluno.getDisciplinas().add(alunoDisciplina);
    }
 
    public void removeAluno(Aluno aluno) {
        for (Iterator<AlunoDisciplina> iterator = alunos.iterator();
             iterator.hasNext(); ) {
        	AlunoDisciplina alunos = iterator.next();
 
            if (alunos.getDisciplina().equals(this) &&
            		alunos.getAluno().equals(aluno)) {
                iterator.remove();
                alunos.getAluno().getDisciplinas().remove(alunos);
                alunos.setDisciplina(null);
                alunos.setAluno(null);
            }
        }
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

	public Set<AlunoDisciplina> getAlunosDisciplina() {
		return alunos;
	}
	
	public String getListAlunos() {
		String alunosString = "";
		for(Object register : alunos.toArray()) {
			AlunoDisciplina alunos = (AlunoDisciplina) register;
			alunosString += alunos.getAluno() +", ";
		}
		return alunos.size() > 0 ? alunosString.substring(0, alunosString.length()-2) : alunosString;
	}
	
	@Override
	public String toString() {
		return "Disciplina [id=" + id + ", nome=" + nome+ ", alunos="+getListAlunos()+"]";
	}
	
	
}
