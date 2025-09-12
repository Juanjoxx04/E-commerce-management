package com.E_commerce.DAO;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.E_commerce.model.Orders;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class OrdersDAOImpl implements OrdersDAO {

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
	public List<Orders> getAllOrders() {
		try {
			String sql = getQueryFromXML("getAllOrders");
			TypedQuery<Orders> query = (TypedQuery<Orders>) entityManager.createNativeQuery(sql, Orders.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Orders getOrderById(Long id) {
		try {
			String sql = getQueryFromXML("getOrdersById");

			Query query = entityManager.createNativeQuery(sql, Orders.class);
			query.setParameter(1, id);
			return (Orders) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Orders createOrder(Orders order) {
		try {
			entityManager.persist(order);
			return order;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Orders updateOrders(Orders order) {
		try {
			return entityManager.merge(order);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteOrders(Long id) {
		try {
			Orders order = entityManager.find(Orders.class, id);
			if (order != null) {
				entityManager.remove(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}