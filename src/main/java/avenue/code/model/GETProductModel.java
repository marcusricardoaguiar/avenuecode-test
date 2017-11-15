package avenue.code.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class GETProductModel {

	/*
	 * Only used to return data to users.
	 */
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private GETProductModel parent;
	
	private List<GETProductModel> childs;
	
	private List<ImageModel> images;

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

	public List<GETProductModel> getChilds() {
		return childs;
	}

	public void setChilds(List<GETProductModel> childs) {
		this.childs = childs;
	}

	public List<ImageModel> getImages() {
		return images;
	}

	public void setImages(List<ImageModel> images) {
		this.images = images;
	}

	public GETProductModel getParent() {
		return parent;
	}

	public void setParent(GETProductModel parent) {
		this.parent = parent;
	}
}
