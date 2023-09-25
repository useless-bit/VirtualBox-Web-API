package org.codesystem.virtualboxwebapi.service;

import lombok.RequiredArgsConstructor;
import org.codesystem.virtualboxwebapi.response.general.ApiError;
import org.codesystem.virtualboxwebapi.response.general.ApiResponse;
import org.codesystem.virtualboxwebapi.response.snapshot.SnapshotListResponse;
import org.codesystem.virtualboxwebapi.utility.CommandExecuter;
import org.codesystem.virtualboxwebapi.utility.CommandExecuterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SnapshotService {
    private final CommandExecuter commandExecuter;

    public ResponseEntity<ApiResponse> getAllSnapshotsForVm(String vmIdentifier) {
        CommandExecuterResponse commandExecuterResponse = commandExecuter.executeCommand("snapshot " + vmIdentifier + " list");
        if (commandExecuterResponse.isCommandExecutedSuccessfully()) {
            return ResponseEntity.ok().body(new SnapshotListResponse(commandExecuterResponse));
        }
        return ResponseEntity.badRequest().body(new ApiError("Error executing command", commandExecuterResponse.getResponse()));
    }
}
