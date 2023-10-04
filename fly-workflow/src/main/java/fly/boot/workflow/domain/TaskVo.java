package fly.boot.workflow.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

@Data
@Schema(description = "任务入参")
public class TaskVo {

    @Schema(description = "任务id")
    private String taskId;

    @Schema(description = "审批意见")
    private String comment;

    @Schema(description = "实例id")
    private String instanceId;

    @Schema(description = "抄送人")
    private String ccUsers;

    @Schema(description = "流程变量信息")
    private Map<String, Object> values;
}
