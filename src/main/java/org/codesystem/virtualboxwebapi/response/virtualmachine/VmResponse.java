package org.codesystem.virtualboxwebapi.response.virtualmachine;

import lombok.Getter;
import lombok.Setter;
import org.codesystem.virtualboxwebapi.response.general.ApiResponse;

@Getter
@Setter
public class VmResponse implements ApiResponse {
    private String uuid;
    private String vmName;
}