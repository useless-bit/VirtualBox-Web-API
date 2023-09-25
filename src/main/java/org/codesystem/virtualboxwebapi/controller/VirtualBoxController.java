package org.codesystem.virtualboxwebapi.controller;

import lombok.RequiredArgsConstructor;
import org.codesystem.virtualboxwebapi.response.general.ApiResponse;
import org.codesystem.virtualboxwebapi.service.VirtualBoxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/virtualbox")
@RequiredArgsConstructor
public class VirtualBoxController {
    private final VirtualBoxService virtualBoxService;

    @GetMapping("/supportedGuestOS")
    public ResponseEntity<ApiResponse> getAllSupportedOperatingSystems() {
        return virtualBoxService.getAllSupportedOperatingSystems();
    }

}
