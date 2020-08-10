package testares;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ModuleData {
    private String token;
    private String ws_name;
    private String project_name;
    private String status;
    private String module_name;
    private String starttime;
    private String totaltests;
    private String runId;
}
