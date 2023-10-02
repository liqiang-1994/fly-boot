package fly.boot.workflow.controller;

import fly.boot.workflow.service.IFlowDefinitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fly.boot.core.api.vo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/flow/definition")
public class FlowDefinitionController {

    @Autowired
    private IFlowDefinitionService flowDefinitionService;

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

    @RequestMapping("/startByProcDefKey/{procDefKey}")
    public Result<?> startProcessByProcDefKey(@PathVariable String procDefKey,
                                               @RequestBody Map<String, Object> variables) {
        return flowDefinitionService.startProcessByProcDefKey(procDefKey, variables);
    }

    @RequestMapping("/startByProcDefId/{procDefId}")
    public Result<?> startByProcDefId(@PathVariable String procDefId,
                                               @RequestBody Map<String, Object> variables) {
        return flowDefinitionService.startByProcDefId(procDefId, variables);
    }
}
