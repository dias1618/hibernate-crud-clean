package posweb.atividade01.daos;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import posweb.atividade01.SessionHibernate;
import posweb.atividade01.pojos.AlunoDisciplina;

public class AlunoDisciplinaCRUD {
	
	public static AlunoDisciplina buscar(Integer idAluno, Integer idDisciplina, String semestre) {
		Session session = SessionHibernate.getInstance().openSession();
		  
		try {
		   Query queryAlunoDisciplina = session.createQuery(
				   "FROM AlunoDisciplina ad "+ 
				   " WHERE ad.aluno.id = :idAluno "+ 
				   "   AND ad.disciplina.id = :idDisciplina"+
				   "   AND ad.semestre = :semestre", AlunoDisciplina.class);
		   queryAlunoDisciplina.setParameter("idAluno", idAluno);
		   queryAlunoDisciplina.setParameter("idDisciplina", idDisciplina);
		   queryAlunoDisciplina.setParameter("semestre", semestre);
		   return (AlunoDisciplina) queryAlunoDisciplina.uniqueResult();
		} catch (HibernateException e) {
		   e.printStackTrace(); 
		   return null;
		} finally {
		   session.close(); 
		}
	}

}
