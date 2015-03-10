package hu.szoftverfolyamat.dto;

import hu.szoftverfolyamat.enums.MessageStatus;
import lombok.Data;

import java.util.Date;

@Data
public class MessageDto {

    private long id;
    private String text;
    private Date createdAt;
    private UserProfileDataDto userFrom;
    private UserProfileDataDto userTo;
    private MessageStatus status;
    private boolean isViewed;
}
