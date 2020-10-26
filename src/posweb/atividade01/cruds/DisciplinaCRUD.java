package posweb.atividade01.cruds;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;

import posweb.atividade01.SessionHibernate;
import posweb.atividade01.enums.SituacaoDisciplinaEnum;
import posweb.atividade01.pojos.Aluno;
import posweb.atividade01.pojos.AlunoDisciplina;
import posweb.atividade01.pojos.Curso;
import posweb.atividade01.pojos.Disciplina;

public class DisciplinaCRUD {

	public static void salvar(Disciplina disciplina){
		Session session = SessionHibernate.getInstance().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Integer disciplinaID = null;
	  		disciplinaID = (Integer) session.save(disciplina);
			disciplina.setId(disciplinaID); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close(); 
		}
	}
	
	public static void atualizar(Disciplina disciplina) throws Exception{
		Session session = SessionHibernate.getInstance().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(disciplina); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			throw new Exception("Erro ao conectar ao banco dados");
		} finally {
			session.close(); 
		}
	}
	
	public static boolean excluir(Integer disciplinaId) {
		Session session = SessionHibernate.getInstance().openSession();
		Transaction tx = null;
		  
		try {
			tx = session.beginTransaction();
			Disciplina disciplina = (Disciplina)session.get(Disciplina.class, disciplinaId); 
			session.delete(disciplina); 
			tx.commit();
			return true;
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close(); 
		}
	}
	
	public static List<Disciplina> listar() {
		Session session = SessionHibernate.getInstance().openSession();
		Transaction tx = null;
		  
		try {
		   tx = session.beginTransaction();
		   List<Disciplina> disciplinas = session.createQuery("FROM Disciplina", Disciplina.class).list(); 
		   tx.commit();
		   
		   return disciplinas;
		} catch (HibernateException e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		   return new ArrayList<Disciplina>();
		} finally {
		   session.close(); 
		}
	}
	
	public static List<AlunoDisciplina> listarNaoConcluidas(Integer idAluno) {
		Session session = SessionHibernate.getInstance().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   Query<AlunoDisciplina> query = session.createQuery(
				   "FROM AlunoDisciplina ad "
				   + " WHERE ad.aluno.id =:idAluno"
				   + "   AND ad.situacao IN (:tiposSituacao)", AlunoDisciplina.class);
		   query.setParameter("idAluno", idAluno);
		   query.setParameter("tiposSituacao", getTiposSituacaoNaoConcluidos());
		   tx.commit();
		   return (List<AlunoDisciplina>) query.getResultList();
		} catch (HibernateException e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		   return new ArrayList<AlunoDisciplina>();
		} finally {
		   session.close(); 
		}
	}
	
	private static List<Integer> getTiposSituacaoNaoConcluidos(){
		List<Integer> tipos = new ArrayList<Integer>();
		tipos.add(SituacaoDisciplinaEnum.EM_CURSO.getKey());
		tipos.add(SituacaoDisciplinaEnum.TRANCADO.getKey());
		tipos.add(SituacaoDisciplinaEnum.REPROVADO.getKey());
		return tipos;
	}
	
	public static Disciplina buscar(int disciplinaId) {
		Session session = SessionHibernate.getInstance().openSession();
		Transaction tx = null;
		  
		try {
			tx = session.beginTransaction();
			Disciplina disciplina = (Disciplina)session.get(Disciplina.class, disciplinaId); 
			tx.commit();
			return disciplina;
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close(); 
		}		
	}

}
