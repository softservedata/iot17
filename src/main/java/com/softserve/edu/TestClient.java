package com.softserve.edu;

//package de.laliluna.example;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestClient {

	public static void main(String[] args) {
		TestClient client = new TestClient();

		try {
			Honey honey = new Honey();
			honey.setName("favourite white2");
			honey.setTaste("very sweet2");
			honey = client.createHoney(honey);
			//
			System.out.println("primary key is " + honey.getId());
			client.listHoney();
			//
			honey.setName("greece forest++2");
			honey.setTaste("very good++2");
			client.updateHoney(honey);
			client.listHoney();
			//
		} catch (HibernateException e) {
			// problem with Hibernate happened
			e.printStackTrace();
		}
	}

	public TestClient() {
	}

	private Honey createHoney(Honey honey) {

		// get the session from the factory
		Session session = HibernateSessionFactory.currentSession();

		// always start a transaction before doing something
		// (even reading) from the database
		Transaction tx = session.beginTransaction();

		// save it to the database, Hibernate returns your object
		// with the primary key field updated!
		Integer id = (Integer) session.save(honey);
		honey.setId(id);
		

		// commit your transaction or nothing is wrote to the db
		tx.commit();

		// clean up (close the session)
		HibernateSessionFactory.closeSession();

		return honey;
	}

	private void updateHoney(Honey honey) {

		// get the session from the factory
		Session session = HibernateSessionFactory.currentSession();

		// always start a transaction before doing something
		// (even reading) from the database
		Transaction tx = session.beginTransaction();

		// load object from the database
		Honey dbHoney = (Honey) session.get(Honey.class, honey.getId());

		// honey is null when no object was found
		if (honey != null) {
			dbHoney.setName(honey.getName());
			dbHoney.setTaste(honey.getTaste());
		}
		session.flush();
		// commit your transaction or nothing is wrote to the db
		tx.commit();

		// clean up (close the session)
		HibernateSessionFactory.closeSession();
	}

	private void listHoney() {

		// get the session from the factory
		Session session = HibernateSessionFactory.currentSession();

		// always start a transaction before doing something
		// (even reading) from the database
		Transaction tx = session.beginTransaction();

		List<?> honeys =  session.createQuery("from Honey").list();

		for (Iterator<?> iter = honeys.iterator(); iter.hasNext();) {
			Honey honey = (Honey) iter.next();
			System.out.println("Id " + honey.getId() + " Name " + honey.getName());
		}
		// commit your transaction or nothing is wrote to the db
		tx.commit();
		// clean up (close the session)
		HibernateSessionFactory.closeSession();
	}
} 