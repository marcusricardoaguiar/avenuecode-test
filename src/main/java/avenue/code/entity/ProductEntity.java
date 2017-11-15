package avenue.code.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "parent_product_id", nullable = true)
	private ProductEntity parent;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_product_id")
	private Set<ProductEntity> childProducts;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
    @OrderBy(value = "id")
	private Set<ImageEntity> images;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<ProductEntity> getChildProducts() {
		return childProducts;
	}

	public void setChildProducts(Set<ProductEntity> childProducts) {
		this.childProducts = childProducts;
	}

	public Set<ImageEntity> getImages() {
		return images;
	}

	public void setImages(Set<ImageEntity> images) {
		this.images = images;
	}

	public ProductEntity getParent() {
		return parent;
	}

	public void setParent(ProductEntity parent) {
		this.parent = parent;
	}
}