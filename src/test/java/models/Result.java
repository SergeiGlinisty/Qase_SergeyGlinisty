package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result {
    String code;
    int id;
}
