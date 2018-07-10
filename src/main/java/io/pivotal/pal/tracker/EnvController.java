package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@EnableAutoConfiguration
public class EnvController {

    private HashMap<String, String> envVars = new HashMap<String, String>();

    public EnvController(@Value("${PORT:NOT SET}") String port
                       , @Value("${MEMORY_LIMIT:NOT SET}") String memory_limit
                       , @Value("${CF_INSTANCE_INDEX:NOT SET}") String cf_instance_index
                       , @Value("${CF_INSTANCE_ADDR:NOT SET}") String cf_instance_addr) {

        envVars.put("PORT",port);
        envVars.put("MEMORY_LIMIT",memory_limit);
        envVars.put("CF_INSTANCE_INDEX",cf_instance_index);
        envVars.put("CF_INSTANCE_ADDR",cf_instance_addr);
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        return envVars;
    }
}
