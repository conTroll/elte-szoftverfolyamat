package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TextRequest implements Serializable {
	
	private static final long serialVersionUID = -2816628695337212873L;

	@Size(min=1, max=21000) @NotNull
    private String text;
}
