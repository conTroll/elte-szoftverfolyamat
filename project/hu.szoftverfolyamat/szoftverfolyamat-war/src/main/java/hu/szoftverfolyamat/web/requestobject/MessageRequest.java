package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;

import java.io.Serializable;


@Data
public class MessageRequest implements Serializable {

    private Long recipientId;
    private String text;
}
