package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CreateInterestRequest implements Serializable {

    private static final long serialVersionUID = 3639216715016866171L;

    @Size(min=1, max=100)
    @NotNull
    private String name;

}
