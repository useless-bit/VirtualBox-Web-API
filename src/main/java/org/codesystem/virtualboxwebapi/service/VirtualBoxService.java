package org.codesystem.virtualboxwebapi.service;

import lombok.RequiredArgsConstructor;
import org.codesystem.virtualboxwebapi.response.general.ApiError;
import org.codesystem.virtualboxwebapi.response.general.ApiResponse;
import org.codesystem.virtualboxwebapi.response.virtualbox.OsTypeListResponse;
import org.codesystem.virtualboxwebapi.utility.CommandExecuter;
import org.codesystem.virtualboxwebapi.utility.CommandExecuterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VirtualBoxService {
    private final CommandExecuter commandExecuter;

    public ResponseEntity<ApiResponse> getAllSupportedOperatingSystems() {
        CommandExecuterResponse commandExecuterResponse = commandExecuter.executeCommand("list ostypes");
        if (commandExecuterResponse.isCommandExecutedSuccessfully()) {
            return ResponseEntity.ok().body(new OsTypeListResponse(commandExecuterResponse));
        }
        return ResponseEntity.badRequest().body(new ApiError("Error executing command", commandExecuterResponse.getResponse()));
    }
}
