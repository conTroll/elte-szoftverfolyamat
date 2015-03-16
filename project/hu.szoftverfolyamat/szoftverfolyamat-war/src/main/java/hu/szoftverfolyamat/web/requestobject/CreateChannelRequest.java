package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;

@Data
public class CreateChannelRequest {

	private String name;
	private String description;
	private boolean open;

}
