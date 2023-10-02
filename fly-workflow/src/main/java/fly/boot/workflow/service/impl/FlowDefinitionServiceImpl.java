package fly.boot.workflow.service.impl;

import fly.boot.core.api.vo.Result;
import fly.boot.workflow.constants.ProcessConstants;
import fly.boot.workflow.constants.TaskConstants;
import fly.boot.workflow.constants.enums.ProcessStatus;
import fly.boot.workflow.factory.FlowServiceFactory;
import fly.boot.workflow.service.IFlowDefinitionService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

import static fly.boot.workflow.constants.Constants.BPMN_FILE_SUFFIX;

@Slf4j
@Service
public class FlowDefinitionServiceImpl extends FlowServiceFactory implements IFlowDefinitionService {

    @Override
    public void importFile(String name, String category, InputStream in) {
        Deployment deployment = repositoryService.createDeployment().addInputStream(name + BPMN_FILE_SUFFIX, in).name(name).category(category).deploy();
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
        repositoryService.setProcessDefinitionCategory(definition.getId(), category);
    }

    @Override
    @Transactional
    public Result startProcessByProcDefKey(String procDefKey, Map<String, Object> variables) {
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(procDefKey)
                .latestVersion().singleResult();
        startByProcDefId(definition.getId(), variables);
        return Result.OK();
    }

    @Override
    @Transactional
    public Result startByProcDefId(String procDefId, Map<String, Object> variables) {
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(procDefId)
                .latestVersion().singleResult();
        if (Objects.isNull(definition) || definition.isSuspended()) {
            return Result.error("流程定义不存在或已挂起，请先激活流程");
        }

        variables.put(TaskConstants.PROCESS_INITIATOR, "");
        identityService.setAuthenticatedUserId("");
        // 设置流程状态为进行中
        variables.put(ProcessConstants.PROCESS_STATUS_KEY, ProcessStatus.RUNNING.getStatus());
        // 发起流程实例
        ProcessInstance instance = runtimeService.startProcessInstanceById(procDefId, variables);
        //todo 设置下个节点
        return Result.OK();
    }


}
