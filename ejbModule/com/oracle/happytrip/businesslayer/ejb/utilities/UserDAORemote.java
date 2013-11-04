package com.oracle.happytrip.businesslayer.ejb.utilities;
import java.util.List;

import javax.ejb.Remote;

import com.oracle.happytrip.businesslayer.entities.Genericuser;

@Remote
public interface UserDAORemote 
{
	boolean insertRegisteredUser(Genericuser r);
	Genericuser getUser(String userName,String password);
	boolean emailExists(String email);
	boolean updateGenericUser(Genericuser u);
}
