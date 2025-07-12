package com.istar.service.Compiler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class JavaCodeCompiler {
    public boolean compile(String filePath) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        if (compiler == null) {
            throw new IllegalStateException("JavaCompiler not found. Make sure you're running a JDK, not a JRE.");
        }

        int result = compiler.run(null, null, null, filePath);
        return result == 0;
    }
}
