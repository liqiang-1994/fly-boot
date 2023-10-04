package fly.boot.workflow.service.impl;

import cn.hutool.core.util.ObjectUtil;
import fly.boot.core.api.vo.Result;
import fly.boot.workflow.constants.ProcessConstants;
import fly.boot.workflow.constants.enums.FlowType;
import fly.boot.workflow.constants.enums.ProcessStatus;
import fly.boot.workflow.domain.TaskVo;
import fly.boot.workflow.factory.FlowServiceFactory;
import fly.boot.workflow.service.IFlowTaskService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FlowTaskServiceImpl extends FlowServiceFactory implements IFlowTaskService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result complete(TaskVo taskVo) {
        Task task = taskService.createTaskQuery().taskId(taskVo.getTaskId()).singleResult();
        if (null == task) {
            return Result.error("任务不存在");
        }
        //todo
        taskService.setAssignee(taskVo.getTaskId(), "");
        return Result.OK();
    }

    @Override
    public Result reject(TaskVo taskVo) {
        Task task = taskService.createTaskQuery().taskId(taskVo.getTaskId()).singleResult();
        if (ObjectUtil.isNull(task)) {
            throw new RuntimeException("任务不存在");
        }
        if (task.isSuspended()) {
            throw new RuntimeException("任务处于挂起状态");
        }
        ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(taskVo.getInstanceId())
                .singleResult();
        if (ObjectUtil.isNull(instance)) {
            throw new RuntimeException("流程实例不存在");
        }
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(task.getProcessDefinitionId())
                .singleResult();
        taskService.addComment(taskVo.getTaskId(), taskVo.getInstanceId(), FlowType.REJECT.getCode(), taskVo.getComment());
        //设置流程状态为终结
        runtimeService.setVariable(instance.getId(), ProcessConstants.PROCESS_STATUS_KEY, ProcessStatus.TERMINATED.getStatus());
        return Result.OK();
    }

    @Override
    public void delegate(TaskVo taskVo) {

    }

    @Override
    public void transfer(TaskVo taskVo) {

    }
}
