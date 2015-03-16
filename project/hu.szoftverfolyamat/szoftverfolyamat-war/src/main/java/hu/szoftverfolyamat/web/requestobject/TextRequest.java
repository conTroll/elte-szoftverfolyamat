package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class TextRequest implements Serializable {

    private String text;
}
