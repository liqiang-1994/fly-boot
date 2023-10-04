package fly.boot.workflow.controller;

import fly.boot.core.api.vo.Result;
import fly.boot.workflow.domain.TaskVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "流程任务管理")
@RequestMapping("/task")
@RestController
public class FlowTaskController {

    @Operation(summary = "审批任务")
    @PostMapping("/complete")
    public Result complete(@RequestBody TaskVo taskVo) {
        return Result.OK();
    }

    @Operation(summary = "拒绝任务")
    @PostMapping("/reject")
    public Result reject(@RequestBody TaskVo taskVo) {
        return Result.OK();
    }

    @Operation(summary = "委派任务")
    @PostMapping("/delegate")
    public Result delegate(@RequestBody TaskVo taskVo) {
        return Result.OK();
    }

    @Operation(summary = "转办任务")
    @PostMapping("/transfer")
    public Result transfer(@RequestBody TaskVo taskVo) {
        return Result.OK();
    }


}
