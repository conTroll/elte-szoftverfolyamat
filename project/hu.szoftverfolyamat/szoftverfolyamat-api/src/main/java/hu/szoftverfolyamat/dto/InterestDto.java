package hu.szoftverfolyamat.dto;

import lombok.Data;

import java.util.Date;

@Data
public class InterestDto {
    private long id;
    private String name;
    private Date createdAt;
}
