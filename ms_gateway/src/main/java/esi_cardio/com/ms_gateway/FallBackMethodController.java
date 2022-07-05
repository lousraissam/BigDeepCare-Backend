package esi_cardio.com.ms_gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FallBackMethodController {

    @GetMapping("/CardioAuthServiceFallBack")
    public String CardioAuthServiceFallback(){
        return "Cardio Auth Service is taking longer than expected. Please try again later";
    }

    @GetMapping("/CardioDMServiceFallBack")
    public String CardioDMServiceFallback(){
        return "Cardio Dossier Medical Service is taking longer than expected. Please try again later";
    }
}
