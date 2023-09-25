package org.codesystem.virtualboxwebapi.utility;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@RequiredArgsConstructor
public class CommandExecuter {
    public CommandExecuterResponse executeCommand(String command) {
        String info = "";
        String errorInfo = "";
        try {
            Runtime r = Runtime.getRuntime();

            command = "vboxmanage " + command;
            Process p = r.exec(command);

            BufferedReader bufferedReaderResult =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader bufferedReaderError =
                    new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String tempString;
            while ((tempString = bufferedReaderResult.readLine()) != null) {
                if (!tempString.isBlank()) {
                    info += tempString;
                    info += "\n";
                }
            }
            while ((tempString = bufferedReaderError.readLine()) != null) {
                if (!tempString.isBlank()) {
                    errorInfo += tempString;
                    errorInfo += "\n";
                }
            }
            bufferedReaderResult.close();
            bufferedReaderError.close();

        } catch (IOException e) {
//            System.out.println(e.getMessage());
        }

        CommandExecuterResponse commandExecuterResponse = new CommandExecuterResponse();
        if (!errorInfo.isBlank()) {
            commandExecuterResponse.setCommandExecutedSuccessfully(false);
            commandExecuterResponse.setResponse(errorInfo);
        } else {
            commandExecuterResponse.setCommandExecutedSuccessfully(true);
            commandExecuterResponse.setResponse(info);
        }
        return commandExecuterResponse;
    }
}
