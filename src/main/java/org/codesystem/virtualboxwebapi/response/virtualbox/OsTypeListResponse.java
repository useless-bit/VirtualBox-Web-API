package org.codesystem.virtualboxwebapi.response.virtualbox;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codesystem.virtualboxwebapi.response.general.ApiResponse;
import org.codesystem.virtualboxwebapi.utility.CommandExecuterResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
@NoArgsConstructor
public class OsTypeListResponse implements ApiResponse {
    private List<OsTypeResponse> osTypeResponseList = new ArrayList<>();

    public void addOsTypeResponse(OsTypeResponse osTypeResponse) {
        osTypeResponseList.add(osTypeResponse);
    }

    public OsTypeListResponse(CommandExecuterResponse commandExecuterResponse) {
        Scanner scanner = new Scanner(commandExecuterResponse.getResponse());
        while (scanner.hasNextLine()) {
            String tempValue = scanner.nextLine();
            if (!tempValue.isBlank()) {
                OsTypeResponse osTypeResponse = new OsTypeResponse();
                osTypeResponse.setId(tempValue.replace("ID:", "").trim());
                tempValue = scanner.nextLine();
                osTypeResponse.setDescription(tempValue.replace("Description:", "").trim());
                tempValue = scanner.nextLine();
                osTypeResponse.setFamilyId(tempValue.replace("Family ID:", "").trim());
                tempValue = scanner.nextLine();
                osTypeResponse.setFamilyDescription(tempValue.replace("Family Desc:", "").trim());
                tempValue = scanner.nextLine();
                osTypeResponse.setX86_64(tempValue.replace("64 bit:", "").trim().equalsIgnoreCase("true"));
                this.addOsTypeResponse(osTypeResponse);
            }
        }
        scanner.close();
    }
}