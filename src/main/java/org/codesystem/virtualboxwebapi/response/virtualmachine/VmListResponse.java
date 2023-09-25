package org.codesystem.virtualboxwebapi.response.virtualmachine;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codesystem.virtualboxwebapi.response.general.ApiResponse;
import org.codesystem.virtualboxwebapi.utility.CommandExecuterResponse;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VmListResponse implements ApiResponse {
    private List<VmResponse> vmResponseList = new ArrayList<>();

    public void addVmResponse(VmResponse vmResponse) {
        vmResponseList.add(vmResponse);
    }

    public VmListResponse(CommandExecuterResponse commandExecuterResponse) {
        String[] strings;
        strings = commandExecuterResponse.getResponse().split("\n");
        for (String vmInfo : strings) {
            if (!vmInfo.isBlank()) {
                VmResponse vmResponse = new VmResponse();
                vmResponse.setVmName(vmInfo.substring(vmInfo.indexOf("\"") + 1, vmInfo.indexOf("\" ")));
                vmResponse.setUuid(vmInfo.substring(vmInfo.indexOf("\" ") + 2).replace("{", "").replace("}", ""));
                this.addVmResponse(vmResponse);
            }
        }
    }
}