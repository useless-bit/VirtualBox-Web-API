package org.codesystem.virtualboxwebapi.service;

import lombok.RequiredArgsConstructor;
import org.codesystem.virtualboxwebapi.request.virtualmachine.CreateVmRequest;
import org.codesystem.virtualboxwebapi.response.general.ApiError;
import org.codesystem.virtualboxwebapi.response.general.ApiResponse;
import org.codesystem.virtualboxwebapi.response.virtualmachine.CreateVmResponse;
import org.codesystem.virtualboxwebapi.response.virtualmachine.VmListResponse;
import org.codesystem.virtualboxwebapi.utility.CommandExecuter;
import org.codesystem.virtualboxwebapi.utility.CommandExecuterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VirtualMachineService {
    private final CommandExecuter commandExecuter;

    public ResponseEntity<ApiResponse> getAllVirtualMachines() {
        CommandExecuterResponse commandExecuterResponse = commandExecuter.executeCommand("list vms");
        if (commandExecuterResponse.isCommandExecutedSuccessfully()) {
            return ResponseEntity.ok().body(new VmListResponse(commandExecuterResponse));
        }
        return ResponseEntity.badRequest().body(new ApiError("Error executing command", commandExecuterResponse.getResponse()));
    }

    public ResponseEntity<ApiResponse> getAllRunningVirtualMachines() {
        CommandExecuterResponse commandExecuterResponse = commandExecuter.executeCommand("list runningvms");
        if (commandExecuterResponse.isCommandExecutedSuccessfully()) {
            return ResponseEntity.ok().body(new VmListResponse(commandExecuterResponse));
        }
        return ResponseEntity.badRequest().body(new ApiError("Error executing command", commandExecuterResponse.getResponse()));
    }

    public ResponseEntity<ApiResponse> createNewVm(CreateVmRequest createVmRequest) {
        if (createVmRequest.getOsType() == null || createVmRequest.getOsType().isBlank() || createVmRequest.getVmName() == null || createVmRequest.getVmName().isBlank()) {
            return ResponseEntity.badRequest().body(new ApiError("Request incomplete", "One of the Request Fields is unspecified/empty"));
        }
        if (createVmRequest.getVmName().contains(" ")) {
            return ResponseEntity.badRequest().body(new ApiError("No space allowed", "The vmName-Attribute cannot include whitespaces"));
        }
        CommandExecuterResponse commandExecuterResponse = commandExecuter.executeCommand("createvm --name " + createVmRequest.getVmName() + " --ostype " + createVmRequest.getOsType() + " --register");
        if (commandExecuterResponse.isCommandExecutedSuccessfully()) {
            return ResponseEntity.ok().body(new CreateVmResponse(commandExecuterResponse));
        }
        return ResponseEntity.badRequest().body(new ApiError("Error executing command", commandExecuterResponse.getResponse()));
    }

    public ResponseEntity<ApiResponse> startVm(String vmIdentifier) {
        CommandExecuterResponse commandExecuterResponse = commandExecuter.executeCommand("startvm " + vmIdentifier);
        if (commandExecuterResponse.isCommandExecutedSuccessfully()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body(new ApiError("Error executing command", commandExecuterResponse.getResponse()));
    }
}
