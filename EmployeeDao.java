package com.zycus.dao.impl;

import java.util.List;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zycus.model.Employee;

@Repository
@Scope("prototype")
public class EmployeeDao implements com.zycus.dao.IEmployeeDao {

	@PersistenceContext(unitName = "testing")
	private EntityManager entityManager;

	private static final Logger log = LoggerFactory.getLogger(EmployeeDao.class);

	@SuppressWarnings("unchecked")
	@Override
	public Employee getEmployeeById(long employeeId) {
		try {
			log.info("EmployeeDaoImpl>getEmployeeById()");
			List<Employee> employeeList = null;
			EntityGraph<Employee> eg = entityManager.createEntityGraph(Employee.class);
			eg.addAttributeNodes("accounts");
			StringBuilder sb = new StringBuilder();
			sb.append("FROM Employee employee WHERE employee.employeeId=:employeeId");
			Query query = entityManager.createQuery(sb.toString());
			query.setParameter("employeeId", employeeId);
			query.setHint("javax.persistence.fetchgraph", eg);
			query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.USE);
			employeeList = query.getResultList();
			if (employeeList != null && !employeeList.isEmpty()) {
				return employeeList.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() {
		log.info("EmployeeDaoImpl>getAllEmployees()");
		try {
			List<Employee> employeeList = null;
			EntityGraph<Employee> eg = entityManager.createEntityGraph(Employee.class);
			eg.addAttributeNodes("accounts");
			StringBuilder sb = new StringBuilder();
			sb.append("Select Distinct employee FROM Employee employee");
			Query query = entityManager.createQuery(sb.toString());
			query.setHint("javax.persistence.fetchgraph", eg);
			query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.USE);
			employeeList = query.getResultList();
			return employeeList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	@Transactional(value = "jpaTx", propagation = Propagation.REQUIRED)
	public long saveEmployee(Employee employee) {
		log.info("EmployeeDaoImpl>saveEmployee()");
		try {
			entityManager.persist(employee);
			return employee.getEmployeeId();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateEmployee(Employee employee) {
		log.info("EmployeeDaoImpl>updateEmployee()");
		try {
			entityManager.merge(employee);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteEmployee(long employeeId) {
		log.info("EmployeeDaoImpl>deleteEmployee()");
		try {
			Employee emp = entityManager.find(Employee.class, employeeId);
			if (emp == null) {
				return false;
			} else {
				entityManager.remove(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return true;
	}
}
