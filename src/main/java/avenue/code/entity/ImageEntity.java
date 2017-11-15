package avenue.code.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import avenue.code.enumerators.ImageTypeEnum;

@Entity
@Table(name = "image")
public class ImageEntity implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "type")
	private ImageTypeEnum type;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public ImageTypeEnum getType() {
		return type;
	}

	public void setType(ImageTypeEnum type) {
		this.type = type;
	}
}
