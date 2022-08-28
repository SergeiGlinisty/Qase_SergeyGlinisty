package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Defect {
    String code;
    String title;
    String actual_result;
    int severity;

}
