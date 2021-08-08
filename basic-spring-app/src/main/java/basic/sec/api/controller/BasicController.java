package basic.sec.api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @GetMapping("test/")
    @ResponseBody
    public String test(){
        return "test";
    }
}
