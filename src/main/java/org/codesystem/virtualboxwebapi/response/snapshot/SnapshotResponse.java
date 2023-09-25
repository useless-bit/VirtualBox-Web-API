package org.codesystem.virtualboxwebapi.response.snapshot;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codesystem.virtualboxwebapi.response.general.ApiResponse;

@Getter
@Setter
@NoArgsConstructor
public class SnapshotResponse implements ApiResponse {
    private String name;
    private String uuid;
    private boolean snapshotActive;
}
