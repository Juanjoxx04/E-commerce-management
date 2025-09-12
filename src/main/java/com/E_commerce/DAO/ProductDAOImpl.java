package com.E_commerce.DAO;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.E_commerce.model.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private EntityManager entityManager;

	private String getQueryFromXML(String queryId) throws Exception {
		InputStream is = getClass().getClassLoader().getResourceAsStream("queries/Native.xml");
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

		var queryNodes = doc.getElementsByTagName("query");
		for (int i = 0; i < queryNodes.getLength(); i++) {

			Element queryElement = (Element) queryNodes.item(i);

			if (queryElement.getAttribute("id").equals(queryId)) {
				return queryElement.getTextContent().trim();
			}
		}
		throw new RuntimeException("Consulta no encontrada con el ID " + queryId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProduct() {
		try {
			String sql = getQueryFromXML("getAllProducts");
			TypedQuery<Product> query = (TypedQuery<Product>) entityManager.createNativeQuery(sql, Product.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Product getProductsById(Long id) {
		try {
			String sql = getQueryFromXML("getProductsById");

			Query query = entityManager.createNativeQuery(sql, Product.class);
			query.setParameter(1, id);
			return (Product) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Product saveProduct(Product product) {

		try {
			entityManager.persist(product);
			return entityManager.merge(product);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Product updateProduct(Product product) {
		try {
			return entityManager.merge(product);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteProduct(Long id) {
		try {
			Product product = entityManager.find(Product.class, id);
			if (product != null) {
				entityManager.remove(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
