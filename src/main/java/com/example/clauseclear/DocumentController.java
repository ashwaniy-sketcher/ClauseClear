
package com.example.clauseclear;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController {

    private final DocumentAnalysisService documentAnalysisService;

    public DocumentController(DocumentAnalysisService documentAnalysisService)
    {
        this.documentAnalysisService = documentAnalysisService;
    }

    @PostMapping("/analyze")
    public String analyzeDocument(@RequestBody String text) {
        return documentAnalysisService.analyzeText(text);
    }
}
