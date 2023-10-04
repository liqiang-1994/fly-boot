package fly.boot.workflow.controller;

import fly.boot.workflow.service.IFlowDefinitionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fly.boot.core.api.vo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Slf4j
@Tag(name = "流程定义管理")
@RestController
@RequestMapping("/definition")
public class FlowDefinitionController {

    @Autowired
    private IFlowDefinitionService flowDefinitionService;

    @Operation(summary = "导入流程文件")
    @PostMapping("/import")
    public Result<?> importProcessDefinition(@RequestParam(required = false) String name,
                                             @RequestParam(required = false) String category,
                                             MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            flowDefinitionService.importFile(name, category, inputStream);
        } catch (IOException e) {
            log.error("导入流程定义失败", e);
            return Result.error("导入失败");
        }
        return Result.OK();
    }

    @Operation(summary = "启动流程-根据流程key")
    @PostMapping("/startByProcDefKey/{procDefKey}")
    public Result<?> startProcessByProcDefKey(@PathVariable String procDefKey,
                                               @RequestBody Map<String, Object> variables) {
        return flowDefinitionService.startProcessByProcDefKey(procDefKey, variables);
    }

    @Operation(summary = "启动流程-根据流程id")
    @PostMapping("/startByProcDefId/{procDefId}")
    public Result<?> startByProcDefId(@PathVariable String procDefId,
                                               @RequestBody Map<String, Object> variables) {
        return flowDefinitionService.startByProcDefId(procDefId, variables);
    }
}
