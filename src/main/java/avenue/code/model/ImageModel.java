package avenue.code.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import avenue.code.enumerators.ImageTypeEnum;

@JsonInclude(value = Include.NON_NULL)
public class ImageModel {

	/*
	 * Used in both, to receive and show data. Used in GET and POST/PUT methods. 
	 */
	
	private Long id;
	
	private ImageTypeEnum type;
	
	public ImageModel(){
		
	}
	
	public ImageModel(ImageTypeEnum type){
		this.id = null;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ImageTypeEnum getType() {
		return type;
	}

	public void setType(ImageTypeEnum type) {
		this.type = type;
	}
}
