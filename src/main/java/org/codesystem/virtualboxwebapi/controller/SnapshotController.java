package org.codesystem.virtualboxwebapi.controller;

import lombok.RequiredArgsConstructor;
import org.codesystem.virtualboxwebapi.response.general.ApiResponse;
import org.codesystem.virtualboxwebapi.service.SnapshotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/vm/snapshot")
@RequiredArgsConstructor
public class SnapshotController {
    private final SnapshotService snapshotService;

    @GetMapping("/{vmIdentifier}")
    public ResponseEntity<ApiResponse> getAllSnapshotsForVm(@PathVariable String vmIdentifier) {
        return snapshotService.getAllSnapshotsForVm(vmIdentifier);
    }

}
