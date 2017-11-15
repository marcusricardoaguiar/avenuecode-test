package avenue.code.enumerators;

public enum ImageTypeEnum {

	/*
	 * The main types of images.
	 */
	
	BPM(1),
	GIF(2),
	JPG(3),
	JPEG(4),
	PNG(5);

	private int code;

	ImageTypeEnum(int value) {
		code = value;
	}
	
	public static ImageTypeEnum getByCode(int code){
		switch(code){
			case 1: return BPM;
			case 2: return GIF;
			case 3: return JPG;
			case 4: return JPEG;
			case 5: return PNG;
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}