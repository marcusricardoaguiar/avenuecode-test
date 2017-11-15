package avenue.code.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avenue.code.entity.ProductEntity;

@Service
@Transactional
public class ProductDAO implements IProductDAO {

	@Autowired
	private EntityManager entityManager;
	
	private static final String ALL_PRODUCTS = "SELECT distinct p FROM ProductEntity as p"
													+ " left join fetch p.images as i"
													+ " left join fetch p.childProducts as chds"
													+ " order by p.id asc";
	
	private static final String FIND_PRODUCT_BY_ID = "SELECT distinct p FROM ProductEntity as p"
													+ " left join fetch p.images as i"
													+ " left join fetch p.childProducts as chds"
													+ " where p.id = :pid";

	private static final String ALL_CHILDS_BY_PID = "SELECT distinct p FROM ProductEntity as p"
													+ " where p.parent.id = :pid"
													+ " order by p.id asc";
	@Override
	public List<ProductEntity> findAll() {
		
		return entityManager
				.createQuery(ALL_PRODUCTS, ProductEntity.class)
				.getResultList();
	}

	@Override
	public ProductEntity getById(Long idProduct) {
		
		ProductEntity product = entityManager
				.createQuery(FIND_PRODUCT_BY_ID, ProductEntity.class)
				.setParameter("pid", idProduct)
				.getSingleResult();
		return product;
	}

	@Override
	public ProductEntity create(ProductEntity productEntity) {
		return entityManager.merge(productEntity);
	}

	@Override
	public ProductEntity update(ProductEntity productEntity) {
		return entityManager.merge(productEntity);	
	}

	@Override
	public void delete(ProductEntity productEntity) {
		Object c = entityManager.merge(productEntity);
		entityManager.remove(c);
	}

	@Override
	public List<ProductEntity> findAllChilds(Long idProduct) {
		return entityManager
				.createQuery(ALL_CHILDS_BY_PID, ProductEntity.class)
				.setParameter("pid", idProduct)
				.getResultList();
	}
}