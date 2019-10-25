package de.viawhs.backend.rest;

import de.viawhs.backend.model.ServerInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/server")
public class ServerController {
    @GetMapping("/git-repo")
    public String callGitServer(@RequestParam("url") String url) {
        return url;
    }
}
