package testares;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class RunIdData {
    private String token;
    private String ws_name;
    private String project_name;
    private String status;
}
