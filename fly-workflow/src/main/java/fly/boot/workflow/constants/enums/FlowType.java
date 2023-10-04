package fly.boot.workflow.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FlowType {
    NORMAL("0", "正常"),
    REJECT("1", "驳回"),
    BACK("2", "退回"),
    DELEGATE("3", "委派"),
    TRANSFER("4", "转办"),
    STOP("5", "终止"),
    REVOKE("6", "撤回");


    private final String code;

    private final String type;

}
