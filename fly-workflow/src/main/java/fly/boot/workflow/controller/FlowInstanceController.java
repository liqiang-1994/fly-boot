package fly.boot.workflow.controller;

import fly.boot.core.api.vo.Result;
import fly.boot.workflow.service.IFlowInstanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "流程实例管理")
@RequestMapping("/instance")
@RestController
public class FlowInstanceController {

    @Autowired
    private IFlowInstanceService flowInstanceService;

    @Operation(summary = "删除流程实例")
    @Parameters(value = {
            @Parameter(name = "instanceId", description = "流程实例ID", required = true),
            @Parameter(name = "reason", description = "删除原因", required = false),
            @Parameter(name = "dataId", description = "业务数据ID", required = false)
    })
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(required = true) String instanceId, String reason, String dataId) {
        flowInstanceService.delete(instanceId, reason, dataId);
        return Result.OK("删除流程实例成功");
    }
}
