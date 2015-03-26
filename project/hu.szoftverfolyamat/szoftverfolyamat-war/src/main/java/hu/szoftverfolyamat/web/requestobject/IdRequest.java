package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class IdRequest implements Serializable {

	private static final long serialVersionUID = 741719424010398648L;
	
	private Long id;
}
