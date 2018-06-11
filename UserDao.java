package com.zycus.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zycus.dao.IUserDao;
import com.zycus.model.Employee;
import com.zycus.model.User;

@Repository
@Scope("prototype")
public class UserDao implements IUserDao {

	@PersistenceContext(unitName = "testing")
	// @PersistenceContext
	private EntityManager entityManager;

	private static final Logger log = LoggerFactory.getLogger(UserDao.class);

	@Override
	public List<User> getAllUsers() {
		log.info("UserDao>getAllUsers()");
		try {
			return entityManager.createQuery("FROM User u", User.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Transactional
	@Override
	public User getUserByName(String userName) {
		log.info("EmployeeDaoImpl>getEmployeeById()");
		try {
			String str = "select user from User user where user.userName = :userName and user.active = :active";
			Query query = entityManager.createQuery(str).setParameter("userName", userName).setParameter("active", true);
			if (query.getResultList() != null && !query.getResultList().isEmpty()) {
				return (User) query.getResultList().get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	@Transactional(value = "jpaTx", propagation = Propagation.REQUIRED)
	public long saveUser(User user) {
		log.info("UserDao>saveUser()");
		try {
			entityManager.persist(user);
			return user.getUserId();
		} catch (Exception e) {
			//e.printStackTrace();
			throw e;
		}
	}

}
