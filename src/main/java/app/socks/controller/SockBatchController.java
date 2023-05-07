package app.socks.controller;

import app.socks.entity.SockBatch;
import app.socks.enums.Operations;
import app.socks.service.SockBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/socks")
@RequiredArgsConstructor
public class SockBatchController {
    private final SockBatchService sockBatchService;

    @PostMapping("income")
    public ResponseEntity<SockBatch> income(@RequestBody SockBatch sockBatch){
        return ResponseEntity.ok(sockBatchService.income(sockBatch));
    }

    @PostMapping("outcome")
    public ResponseEntity<SockBatch> outcome(@RequestBody SockBatch sockBatch){
        return ResponseEntity.ok(sockBatchService.outcome(sockBatch));
    }

    @GetMapping("")
    public ResponseEntity<Integer> get(@RequestParam String color, @RequestParam Operations operation, @RequestParam Integer cottonPart){
        return ResponseEntity.ok(sockBatchService.get(color, operation, cottonPart));
    }
}
