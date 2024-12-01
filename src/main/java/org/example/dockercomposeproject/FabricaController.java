package org.example.dockercomposeproject;

import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/fabrica")
public class FabricaController {

    @PostMapping("/build")
    public String build() throws IOException, InterruptedException {
        return executeCommand("docker-compose build");
    }

    @PostMapping("/up")
    public String up(@RequestBody String[] services) throws IOException, InterruptedException {
        String servicesList = String.join(" ", services);
        return executeCommand("docker-compose up -d " + servicesList);
    }

    @PostMapping("/down")
    public String down(@RequestBody String[] services) throws IOException, InterruptedException {
        String servicesList = String.join(" ", services);
        return executeCommand("docker-compose stop " + servicesList);
    }

    @PostMapping("/downAll") // Nuevo método
    public String downAll() throws IOException, InterruptedException {
        return executeCommand("docker-compose down");
    }

    private String executeCommand(String command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        return new String(process.getInputStream().readAllBytes());
    }
}