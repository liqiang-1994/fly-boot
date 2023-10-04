package fly.boot.workflow.service;

import fly.boot.core.api.vo.Result;
import fly.boot.workflow.domain.TaskVo;

public interface IFlowTaskService {

        Result complete(TaskVo taskVo);
    
        Result reject(TaskVo taskVo);
    
        void delegate(TaskVo taskVo);
    
        void transfer(TaskVo taskVo);
}
