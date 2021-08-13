package multi_provider.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Multi1Controller {

    @GetMapping("test/")
    @ResponseBody
    public String test() {
        return "multi provider security access cleared";
    }
}
