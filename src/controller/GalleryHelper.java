package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.GalleryPiece;

public class GalleryHelper {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ArtGalleryWyngarden");

	public void insertPiece(GalleryPiece toAdd) {
		// TODO Auto-generated method stub

		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(toAdd);
		em.getTransaction().commit();
		em.close();
	}

	public void deletePiece(GalleryPiece toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		GalleryPiece find = em.find(GalleryPiece.class, toDelete.getId());
		em.remove(find);
		em.getTransaction().commit();
		em.close();
	}

	public List<GalleryPiece> showAllPieces() {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<GalleryPiece> allResults = em.createQuery("select gp from GalleryPiece gp", GalleryPiece.class);
		List<GalleryPiece> allPieces = allResults.getResultList();
		em.close();
		return allPieces;
	}

	public void cleanUp() {
		emfactory.close();
	}

	public List<GalleryPiece> searchForPieceByTitle(String artworkTitle) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<GalleryPiece> typedQuery = em
				.createQuery("select gp from GalleryPiece gp where gp.title = :selectedTitle", GalleryPiece.class);
		typedQuery.setParameter("selectedTitle", artworkTitle);
		List<GalleryPiece> result = typedQuery.getResultList();
		em.close();
		return result;
	}

	public List<GalleryPiece> searchForPiecebyArtist(String artist) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<GalleryPiece> typedQuery = em.createQuery(
				"select gp from GalleryPiece gp where gp.artistName = :selectedArtist", GalleryPiece.class);
		typedQuery.setParameter("selectedArtist", artist);
		List<GalleryPiece> result = typedQuery.getResultList();
		em.close();
		return result;
	}

	public GalleryPiece searchForPieceById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		GalleryPiece foundPiece = em.find(GalleryPiece.class, idToEdit);
		em.close();
		return foundPiece;
	}

	public void updatePiece(GalleryPiece toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

}
