package dao;
import entity.Anagrafica;



import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Anagrafica;

import org.hibernate.Transaction;

import util.HibernateUtil;


public class UserDaoHibernate {	
	
	
	private static UserDaoHibernate instance;

	private UserDaoHibernate() {
	}

	public static UserDaoHibernate getInstance() {
		if (null == instance) {
			instance = new UserDaoHibernate();
		}
		return instance;
	}	


	// metodo ritorno lista utenti
	public List<Anagrafica> getAllUsers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Anagrafica> aList = null;
		try {
			session.getTransaction().begin();
			Query query = session.createQuery("from Anagrafica");
			aList = query.list();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		
		return aList;		
	}
	
	
	public Anagrafica getUserById(int iduser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Anagrafica anagrafica = null;
		try {
			session.getTransaction().begin();
			Query query = session.createQuery("from Anagrafica where iduser = :iduser");
			query.setParameter("iduser", iduser);
			anagrafica = (Anagrafica) query.uniqueResult();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		
		return anagrafica;		
	}
	
	// metodo aggiunta utente
	public void addUser(Anagrafica an ) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			session.save(an);
			session.getTransaction().commit();
		} catch (Exception e) {			
			session.getTransaction().rollback();		
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
	}
	
	//metodo modifica utente
	public void updateUser(Anagrafica an ) {	
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			session.update(an);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}	
	}
	
	
	// metodo eliminazione utente
	public void deleteUser(Anagrafica an) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			session.delete(an);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
	}

}
