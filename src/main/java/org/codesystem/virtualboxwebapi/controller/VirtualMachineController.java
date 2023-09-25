package org.codesystem.virtualboxwebapi.controller;

import lombok.RequiredArgsConstructor;
import org.codesystem.virtualboxwebapi.request.virtualmachine.CreateVmRequest;
import org.codesystem.virtualboxwebapi.response.general.ApiResponse;
import org.codesystem.virtualboxwebapi.service.VirtualMachineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vm")
@RequiredArgsConstructor
public class VirtualMachineController {
    private final VirtualMachineService virtualMachineService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllVirtualMachines() {
        return virtualMachineService.getAllVirtualMachines();
    }

    @GetMapping("/running")
    public ResponseEntity<ApiResponse> getAllRunningVirtualMachines() {
        return virtualMachineService.getAllRunningVirtualMachines();
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createNewVm(@RequestBody CreateVmRequest createVmRequest) {
        return virtualMachineService.createNewVm(createVmRequest);
    }

    @PostMapping("/start/{vmIdentifier}")
    public ResponseEntity<ApiResponse> createNewVm(@PathVariable String vmIdentifier) {
        return virtualMachineService.startVm(vmIdentifier);
    }
}
