package org.codesystem.virtualboxwebapi.response.virtualmachine;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codesystem.virtualboxwebapi.response.general.ApiResponse;
import org.codesystem.virtualboxwebapi.utility.CommandExecuterResponse;

@Getter
@Setter
@NoArgsConstructor
public class CreateVmResponse implements ApiResponse {
    private String uuid;
    private String vmName;
    private String pathSettingsFile;


    public CreateVmResponse(CommandExecuterResponse commandExecuterResponse) {
        String[] strings;
        strings = commandExecuterResponse.getResponse().split("\n");
        this.setVmName(strings[0].replace("Virtual machine '", "").replace("' is created and registered.", ""));
        this.setUuid(strings[1].replace("UUID: ", ""));
        this.setPathSettingsFile(strings[2].substring(strings[2].indexOf("'") + 1, strings[2].length() - 1));
    }
}