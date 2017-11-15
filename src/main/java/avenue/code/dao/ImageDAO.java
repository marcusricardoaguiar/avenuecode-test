package avenue.code.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avenue.code.entity.ImageEntity;

@Service
@Transactional
public class ImageDAO implements IImageDAO {

	@Autowired
	private EntityManager entityManager;
	
	private static final String ALL_IMAGES = "SELECT i FROM ImageEntity as i";
	
	private static final String FIND_IMAGES_BY_ID = "SELECT i FROM ImageEntity as i"
														+ " where i.id = :pid";
			
	private static final String ALL_IMAGES_BY_PRODUCT_ID = "SELECT i FROM ImageEntity as i"
																+ " where i.product.id = :pid";

	@Override
	public List<ImageEntity> findAll() {
		List<ImageEntity> images = entityManager
				.createQuery(ALL_IMAGES, ImageEntity.class)
				.getResultList();
		return images;
	}
	
	@Override
	public ImageEntity findById(Long idImage) {
		ImageEntity products = entityManager
				.createQuery(FIND_IMAGES_BY_ID, ImageEntity.class)
				.setParameter("pid", idImage)
				.getSingleResult();
		return products;
	}
	
	@Override
	public List<ImageEntity> findAllByProductId(Long idProduct) {
		List<ImageEntity> products = entityManager
				.createQuery(ALL_IMAGES_BY_PRODUCT_ID, ImageEntity.class)
				.setParameter("pid", idProduct)
				.getResultList();
		return products;
	}

	@Override
	public ImageEntity create(ImageEntity imageEntity) {
		return entityManager.merge(imageEntity);
	}

	@Override
	public ImageEntity update(ImageEntity imageEntity) {
		return entityManager.merge(imageEntity);
	}

	@Override
	public void delete(ImageEntity imageEntity) {
		Object c = entityManager.merge(imageEntity);
		entityManager.remove(c);
	}
}
