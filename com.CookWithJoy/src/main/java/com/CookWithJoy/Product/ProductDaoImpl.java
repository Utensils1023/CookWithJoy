package com.CookWithJoy.Product;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.dialect.function.TemplateRenderer;
import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
@EnableTransactionManagement
@Transactional
public class ProductDaoImpl implements ProductDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public void insert(Product p) {
		sessionFactory.getCurrentSession().save(p);
	}

	public void update(Product p) {
		sessionFactory.getCurrentSession().update(p);
		
	}

	public void delete(int pid) {
		sessionFactory.getCurrentSession().createQuery("delete from Product as p where p.productId = :id").setInteger("id", pid).executeUpdate();
	}

	public Product getProduct(int productId) {
		List<Product> l = sessionFactory.getCurrentSession().createQuery("from Product where productId = :id").setInteger("id", productId).list();
		if (l.size()>0)
		{
			return (Product)l.get(0);
		}
		else
		{
			return null;
		}
	}

	public List<Product> getAllProducts() {
		return sessionFactory.getCurrentSession().createQuery("from Product").list();
	}
	
	public Product getProductWithMaxId() 
	{
		List<Product> l = sessionFactory.getCurrentSession()
				.createQuery("from Product as p where p.productId = ( select max(a.productId) from Product as a )")
				.list();

		if (l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	
	}
	
}
