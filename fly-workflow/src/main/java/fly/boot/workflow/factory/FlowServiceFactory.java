package fly.boot.workflow.factory;


import jakarta.annotation.Resource;
import lombok.Getter;
import org.flowable.engine.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Getter
public class FlowServiceFactory {
    @Resource
    protected RepositoryService repositoryService;

    @Resource
    protected RuntimeService runtimeService;

    @Resource
    protected IdentityService identityService;

    @Resource
    protected TaskService taskService;

    @Resource
    protected FormService formService;

    @Resource
    protected HistoryService historyService;

    @Resource
    protected ManagementService managementService;

    @Qualifier("processEngine")
    @Resource
    protected ProcessEngine processEngine;
}
