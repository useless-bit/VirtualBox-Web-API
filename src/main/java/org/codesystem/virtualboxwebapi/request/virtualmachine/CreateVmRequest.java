package org.codesystem.virtualboxwebapi.request.virtualmachine;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVmRequest {
    private String vmName;
    private String osType;
}
