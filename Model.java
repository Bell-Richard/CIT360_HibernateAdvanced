package hibernate.working;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Model {
	
	/*
	 * Owner Database Methods
	 */
	static void insertOwner(Owner ownerToInsert) {
		Session session = DatabaseConnection.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		session.save(ownerToInsert);

		transaction.commit();
	}

	static List<Owner> queryAllOwners() {
		Session session = DatabaseConnection.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		Query ownersQuery = session.createQuery("Select o from Owner as o");

		@SuppressWarnings("unchecked")
		List<Owner> ownersList = ownersQuery.list();

		transaction.commit();

		return ownersList;
	}

	static Owner queryOwnerById(Integer anId) {
		Session session = DatabaseConnection.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		Query singleOwnerQuery = session.createQuery("Select o from Owner as o where o.id = :anId");
		singleOwnerQuery.setParameter("anId", anId);

		Owner singleOwner = (Owner) singleOwnerQuery.uniqueResult();

		transaction.commit();

		return singleOwner;
	}

	static void mergeOwnerInformation(Owner anOwnerToUpdate) {
		Session session = DatabaseConnection.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		Query singleOwnerUpdate = session.createQuery("Select o from Owner as o where o.id = :anId");
		singleOwnerUpdate.setParameter("anId", anOwnerToUpdate.getId());

		session.merge(anOwnerToUpdate);

		transaction.commit();
	}

	static void deleteOwnerFromDatabase(Owner anOwnerToDelete) {
		Session session = DatabaseConnection.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		session.delete(anOwnerToDelete);

		transaction.commit();
	}

	/*
	 * Car Database Methods
	 */
	static void insertCar(Car carToInsert) {
		Session session = DatabaseConnection.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		session.save(carToInsert);

		transaction.commit();
	}
	
	static List<Car> queryAllCars() {
		Session session = DatabaseConnection.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		Query carsQuery = session.createQuery("Select c from Car as c");
		
		@SuppressWarnings("unchecked")
		List<Car> carsList = carsQuery.list();
		
		transaction.commit();
		return carsList;
	}
	
	static Car queryCarById(Integer anId) {
		Session session = DatabaseConnection.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		Query singleCarQuery = session.createQuery("Select c from Car as c where c.id = :anId");
		singleCarQuery.setParameter("anId", anId);

		Car singleCar = (Car) singleCarQuery.uniqueResult();

		transaction.commit();

		return singleCar;
	}
	
	static void mergeCarInformation(Car aCarToUpdate) {
		Session session = DatabaseConnection.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		Query singleCarUpdate = session.createQuery("Select c from Car as c where c.id = :anId");
		singleCarUpdate.setParameter("anId", aCarToUpdate.getId());

		session.merge(aCarToUpdate);

		transaction.commit();
	}
	
	static List<Car> queryCarsByOwner(Owner anOwner) {
		Session session = DatabaseConnection.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		Query carsListQuery = session.createQuery("Select c from Car as c where c.ownerMapping = :anOwner");
		carsListQuery.setParameter("anOwner", anOwner);
		
		@SuppressWarnings("unchecked")
		List<Car> carsList = carsListQuery.list();
		
		transaction.commit();
		
		return carsList;
	}
	
	static void deleteCarFromDatabase(Car aCarToDelete) {
		Session session = DatabaseConnection.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		session.delete(aCarToDelete);

		transaction.commit();
	}
}
