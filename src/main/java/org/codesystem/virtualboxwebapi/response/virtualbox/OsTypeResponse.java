package org.codesystem.virtualboxwebapi.response.virtualbox;

import lombok.Getter;
import lombok.Setter;
import org.codesystem.virtualboxwebapi.response.general.ApiResponse;

@Getter
@Setter
public class OsTypeResponse implements ApiResponse {
    private String id;
    private String description;
    private String familyId;
    private String familyDescription;
    private boolean x86_64;
}