package org.codesystem.virtualboxwebapi.response.snapshot;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codesystem.virtualboxwebapi.response.general.ApiResponse;
import org.codesystem.virtualboxwebapi.utility.CommandExecuterResponse;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SnapshotListResponse implements ApiResponse {
    private List<SnapshotResponse> vmResponseList = new ArrayList<>();

    public void addVmResponse(SnapshotResponse snapshotResponse) {
        vmResponseList.add(snapshotResponse);
    }

    public SnapshotListResponse(CommandExecuterResponse commandExecuterResponse) {
        String[] strings;
        strings = commandExecuterResponse.getResponse().split("\n");
        for (String snapshotInfo : strings) {
            if (!snapshotInfo.isBlank()) {
                SnapshotResponse snapshotResponse = new SnapshotResponse();
                snapshotResponse.setName(snapshotInfo.substring(snapshotInfo.indexOf("Name: ") + 6, snapshotInfo.indexOf(" (")));
                snapshotResponse.setUuid(snapshotInfo.substring(snapshotInfo.indexOf("UUID: ") + 6, snapshotInfo.indexOf(")")));
                snapshotResponse.setSnapshotActive(snapshotInfo.endsWith("*"));
                this.addVmResponse(snapshotResponse);
            }
        }
    }
}