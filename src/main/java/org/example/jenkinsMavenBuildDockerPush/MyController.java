package org.example.jenkinsMavenBuildDockerPush;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
@Controller
public class MyController {
    @GetMapping(path = "/index")
    public String index(){
        return "index.html";
    }
}
