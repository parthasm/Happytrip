package com.oracle.happytrip.businesslayer.ejb.utilities;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.oracle.happytrip.businesslayer.entities.Genericuser;

/**
 * Session Bean implementation class UserDAO
 */
@Stateless(mappedName = "UserDAOMapping")
public class UserDAO implements UserDAORemote 
{
	@PersistenceContext(unitName="OracleDS")
	EntityManager em;

	public UserDAO() 
	{

	}

	@Override
	public boolean insertRegisteredUser(Genericuser r)
	{
		try
		{
			em.persist(r);
			em.flush();
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Genericuser getUser(String emailid, String password) throws NoResultException
	{

		Query q = em.createQuery("select u from Genericuser u where u.emailid=:a and u.password=:b");

		q.setParameter("a", emailid);
		q.setParameter("b", password);
		try
		{
			Genericuser u = (Genericuser) q.getSingleResult();

			return u;
		}
		catch (Exception e)
		{
			return null;
		}

	}

	@Override
	public boolean emailExists(String email) throws NoResultException 
	{
		Query q = em.createQuery("select u from Genericuser u where u.emailid=:a");

		q.setParameter("a", email);
		try
		{
			if((Genericuser)(q.getSingleResult())!=null)
				return true;
			else
				return false;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean updateGenericUser(Genericuser u)
	{
		try
		{
			em.merge(u);
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		
	}

}
