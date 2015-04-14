package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateChannelRequest { 

	@Size(min=1, max=128) @NotNull	
	private String name;
	
	@Size(min=1, max=256) @NotNull	
	private String description;
	
	private boolean open;

}
