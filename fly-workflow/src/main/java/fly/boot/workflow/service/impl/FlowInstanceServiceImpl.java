package fly.boot.workflow.service.impl;

import fly.boot.workflow.factory.FlowServiceFactory;
import fly.boot.workflow.service.IFlowInstanceService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FlowInstanceServiceImpl extends FlowServiceFactory implements IFlowInstanceService {


    @Override
    public void delete(String instanceId, String reason, String dataId) {
        // 删除流程实例
        HistoricProcessInstance instance = getHistoricProcessInstanceById(instanceId);
        if (null != instance) {
            historyService.deleteHistoricProcessInstance(instance.getId());
        }
        runtimeService.deleteProcessInstance(instanceId, reason);
        // 删除业务数据
        //todo
    }

    private HistoricProcessInstance getHistoricProcessInstanceById(String processInstanceId) {
        return historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    }
}
