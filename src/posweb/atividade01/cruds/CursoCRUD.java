package posweb.atividade01.cruds;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;

import posweb.atividade01.SessionHibernate;
import posweb.atividade01.pojos.Curso;
import posweb.atividade01.pojos.Disciplina;

public class CursoCRUD {
	
	public static void salvar(Curso curso) {
		Session session = SessionHibernate.getInstance().openSession();
		Transaction tx = null;
		Integer cursoID = null;
  
		try {
			tx = session.beginTransaction();
			cursoID = (Integer) session.save(curso);
			curso.setId(cursoID);
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}	
	}
	
	public static boolean atualizar(Curso curso) {
		Session session = SessionHibernate.getInstance().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.update(curso); 
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
	
	public static boolean excluir(Integer cursoId) {
		Session session = SessionHibernate.getInstance().openSession();
		Transaction tx = null;
		  
		try {
			tx = session.beginTransaction();
			Curso curso = (Curso)session.get(Curso.class, cursoId); 
			session.delete(curso); 
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
	
	public static List<Curso> listar() {
		Session session = SessionHibernate.getInstance().openSession();
		Transaction tx = null;
		  
		try {
		   tx = session.beginTransaction();
		   List<Curso> cursos = session.createQuery(
				   "FROM Curso", Curso.class).list();
		   tx.commit();
		   return cursos;
		} catch (HibernateException e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		   return new ArrayList<Curso>();
		} finally {
		   session.close(); 
		}
	}
	
	public static Curso buscar(int cursoId) {
		Session session = SessionHibernate.getInstance().openSession();
		  
		try {
			Query queryCurso = session.createQuery(
					"FROM Curso c "+ 
					"	LEFT OUTER JOIN FETCH c.disciplinas "+
					"	LEFT OUTER JOIN FETCH c.alunos "+
					"WHERE c.id = :id", Curso.class);
			queryCurso.setParameter("id", cursoId);
			return (Curso) queryCurso.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close(); 
		}		
	}
	
}
