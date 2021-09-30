package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.BirdEntry;

/**
 * @author Amy Miles - almiles
 * CIS 175 - Fall 2021
 * Sep 15, 2021
 */
public class BirdEntryHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("IowaBirdWatch");
	
	/**
	 * this method inserts a BirdEntry object into the database
	 * @param be the BirdEntry object
	 */
	public void insertItem(BirdEntry be) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(be);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * this method returns all data in the database
	 * @return allSightings columns county and bird
	 */
	public List<BirdEntry> showAllSightings() {
		EntityManager em = emfactory.createEntityManager();
		List<BirdEntry> allSightings = em.createQuery("SELECT i FROM BirdEntry i").getResultList();
		return allSightings;
		
	}
	
	/**
	 * this method deletes an entry in the database.
	 * @param toDelete the BirdEntry object to delete
	 */
	public void deleteSighting(BirdEntry toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<BirdEntry> typedQuery = em.createQuery("select be from BirdEntry be where be.county = :selectedCounty and be.bird = :selectedBird", BirdEntry.class);
		typedQuery.setParameter("selectedCounty", toDelete.getCounty());
		typedQuery.setParameter("selectedBird", toDelete.getBird());
		typedQuery.setMaxResults(1);
		BirdEntry result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
		
	}

	/**
	 * @param idToEdit the id of the object in the database
	 * @return found
	 */
	public BirdEntry searchForSightingById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		BirdEntry found = em.find(BirdEntry.class, idToEdit);
		em.close();
		return found;
	}

	/**
	 * @param toEdit an object of BirdEntry to edit
	 */
	public void updateSighting(BirdEntry toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
		
	}

	/**
	 * @param countyName the county name from the county column in database that will be searched
	 * @return foundsightings the objects in the database with the corresponding matching counties
	 */
	public List<BirdEntry> searchForItemByCounty(String countyName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<BirdEntry> typedQuery = em.createQuery("select be from BirdEntry be where be.county = :selectedCounty", BirdEntry.class);
		typedQuery.setParameter("selectedCounty", countyName);
		List<BirdEntry> foundSightings = typedQuery.getResultList();
		em.close();
		return foundSightings;
	}

	/**
	 * @param birdName the name column that will be edited in the database
	 * @return foundSightings
	 */
	public List<BirdEntry> searchForItemByBird(String birdName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<BirdEntry> typedQuery = em.createQuery("select be from BirdEntry be where be.bird = :selectedBird", BirdEntry.class);
		typedQuery.setParameter("selectedBird", birdName);
		List<BirdEntry> foundSightings = typedQuery.getResultList();
		em.close();
		return foundSightings;
	}
	
	/**
	 * closes emfactory
	 */
	public void cleanUp() {
		emfactory.close();
	}

}
