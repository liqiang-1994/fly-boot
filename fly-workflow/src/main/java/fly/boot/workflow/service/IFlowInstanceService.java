package fly.boot.workflow.service;

import fly.boot.workflow.factory.FlowServiceFactory;

public interface IFlowInstanceService  {

    void delete(String instanceId, String reason, String dataId);
}
