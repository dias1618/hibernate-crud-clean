package posweb.atividade01.cruds;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;

import posweb.atividade01.SessionHibernate;
import posweb.atividade01.pojos.Aluno;
import posweb.atividade01.pojos.Curso;

public class AlunoCRUD {

	public static void salvar(Aluno aluno) {
		Session session = SessionHibernate.getInstance().openSession();
		Transaction tx = null;
		Integer alunoID = null;
  
		try {
			tx = session.beginTransaction();
			alunoID = (Integer) session.save(aluno);
			aluno.setId(alunoID);
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}	
	}
	
	public static boolean atualizar(Aluno aluno) {
		Session session = SessionHibernate.getInstance().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.update(aluno); 
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
	
	public static boolean excluir(Integer alunoId) {
		Session session = SessionHibernate.getInstance().openSession();
		Transaction tx = null;
		  
		try {
			tx = session.beginTransaction();
			Aluno aluno = (Aluno)session.get(Aluno.class, alunoId); 
			session.delete(aluno); 
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
	
	public static List<Aluno> listar() {
		Session session = SessionHibernate.getInstance().openSession();
		Transaction tx = null;
		  
		try {
		   tx = session.beginTransaction();
		   List<Aluno> alunos = session.createQuery("FROM Aluno", Aluno.class).list(); 
		   tx.commit();
		   
		   return alunos;
		} catch (HibernateException e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		   return new ArrayList<Aluno>();
		} finally {
		   session.close(); 
		}
	}
	
	public static Aluno buscar(int alunoId) {
		Session session = SessionHibernate.getInstance().openSession();
		Transaction tx = null;
		  
		try {
			tx = session.beginTransaction();
			Aluno aluno = (Aluno)session.get(Aluno.class, alunoId); 
			tx.commit();
			return aluno;
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close(); 
		}		
	}

}
