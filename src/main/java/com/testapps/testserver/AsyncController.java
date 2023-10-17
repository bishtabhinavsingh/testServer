package com.testapps.testserver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
public class AsyncController {

    @GetMapping("/async/gettest")
    public CompletableFuture<ResponseEntity<String>> asyncGet(@RequestParam("variable") String variable){
        return CompletableFuture.supplyAsync(() ->{
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return myDebugger(variable);
        });
    }
    @PutMapping("/async/puttest")
    public CompletableFuture<ResponseEntity<String>> asyncPut(@RequestBody String put){
        return CompletableFuture.supplyAsync(() ->{
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return myDebugger(put);
        });
    }
    @PostMapping("/async/posttest")
    public CompletableFuture<ResponseEntity<String>> asyncPost(@RequestBody String post){
        return CompletableFuture.supplyAsync(() ->{
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return myDebugger(post);
        });
    }
    @DeleteMapping("/async/deletetest/{del}")
    public CompletableFuture<ResponseEntity<String>> asyncDelete(@PathVariable String del){
        return CompletableFuture.supplyAsync(() ->{
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return myDebugger(del);
        });
}
    private ResponseEntity<String> myDebugger(String param){
        if ("check".equalsIgnoreCase(param)){
            return ResponseEntity.status(HttpStatus.OK).body(param);
        } else if ("server".equalsIgnoreCase(param)){
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(param);
        } else if("client".equalsIgnoreCase(param)){
            return ResponseEntity.status(HttpStatus.valueOf(400)).body(param);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(param);
    }
}
