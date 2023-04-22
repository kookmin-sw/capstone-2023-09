package com.capstone.timepay.controller.board.request;

import com.capstone.timepay.domain.freeRegister.FreeRegister;
import com.capstone.timepay.service.board.service.FreeRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/free-register")
@RequiredArgsConstructor
public class FreeRegisterController {

    private final FreeRegisterService freeRegisterService;

    @GetMapping
    public ResponseEntity<List<FreeRegister>> getAllFreeRegisters() {
        List<FreeRegister> freeRegisters = freeRegisterService.getAllFreeRegisters();
        if (freeRegisters.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(freeRegisters);
        }
    }
}
