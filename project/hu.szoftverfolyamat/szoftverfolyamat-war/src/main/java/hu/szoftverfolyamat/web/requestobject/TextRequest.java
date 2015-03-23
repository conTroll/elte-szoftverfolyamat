package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class TextRequest implements Serializable {

	private static final long serialVersionUID = -2816628695337212873L;
	
	private String text;
}
