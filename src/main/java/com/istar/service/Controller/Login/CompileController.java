package com.istar.service.Controller.Login;

import com.istar.service.Compiler.JavaCodeCompiler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compile")
public class CompileController {

    private final JavaCodeCompiler compiler = new JavaCodeCompiler();

    @PostMapping
    public String compile(@RequestParam String sourceFilePath) {
        boolean success = compiler.compile(sourceFilePath);
        return success ? "Compilation successful!" : "Compilation failed!";
    }
}
