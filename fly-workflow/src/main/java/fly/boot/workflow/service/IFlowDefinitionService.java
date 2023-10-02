package fly.boot.workflow.service;

import fly.boot.core.api.vo.Result;

import java.io.InputStream;
import java.util.Map;

public interface IFlowDefinitionService {

    /**
     * 导入流程文件
     * @param name
     * @param category
     * @param in
     * @return
     */
    void importFile(String name, String category, InputStream in);

    Result startProcessByProcDefKey(String procDefKey, Map<String, Object> variables);

    Result startByProcDefId(String procDefId, Map<String, Object> variables);
}
