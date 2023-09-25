package org.codesystem.virtualboxwebapi.utility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandExecuterResponse {
    private boolean commandExecutedSuccessfully;
    private String response;
}
