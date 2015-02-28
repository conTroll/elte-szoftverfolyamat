package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;
import java.io.Serializable;

@Data
public class IdRequest implements Serializable {

    private Long id;
}
