package com.lanc.app.controller;

import com.lanc.business.sse.SseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class SseController {

    @Autowired
    private SseManager sseManager;

    @GetMapping("/connect/{clientId}")
    public SseEmitter connect(@PathVariable String clientId) {
        return sseManager.connect(clientId);
    }
}

