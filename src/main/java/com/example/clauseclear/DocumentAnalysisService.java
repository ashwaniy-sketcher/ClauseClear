
package com.example.clauseclear;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.stereotype.Service;

@Service
public class DocumentAnalysisService {

    private static final Logger logger =
            LoggerFactory.getLogger(DocumentAnalysisService.class);

    private final Client geminiClient;

    public DocumentAnalysisService() {
        String apiKey = System.getenv("GEMINI_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "GEMINI_API_KEY environment variable is not set."
            );
        }

        this.geminiClient = Client.builder()
                .apiKey(apiKey)
                .build();
    }

    public String analyzeText(String text) {
        if (text == null || text.isBlank()) {
            return "Please provide Terms & Conditions or Privacy Policy text.";
        }

        String prompt ="""
        You are ClauseClear, an assistant that explains Terms and
        Conditions and Privacy Policies in simple English.

        YOUR GOAL:
        Help users understand the most important terms in 10-15 seconds.
        Be concise, practical, and accurate. Do not write a long report.

        STRICT OUTPUT FORMAT:

        ## Important Things to Know

        Provide a maximum of 5 key points.
        Prioritize the following when present in the document:
        - Costs, fees, subscriptions, and automatic renewals.
        - Cancellation deadlines and refund restrictions.
        - Personal data collected and third-party data sharing.
        - Account suspension, termination, and unusual obligations.
        - Significant privacy risks or other unfavorable conditions.

        For each point:
        - Start with a short, meaningful heading.
        - Explain the condition in 1-2 short sentences.
        - Include exact amounts and deadlines when specified.
        - Explain the practical consequence for the user.
        - Do not repeat the same information in multiple points.

        ## Before You Agree

        Give a maximum of 2 short bullet points identifying
        important missing details or conditions worth checking.
        Only mention questions relevant to this document.

        ## Overall Takeaway

        Write ONE sentence explaining the most important thing
        the user should remember before accepting these terms.

        ACCURACY RULES:
        - Use only information explicitly supported by the document.
        - Never invent fees, deadlines, permissions, or restrictions.
        - Sharing information does not automatically mean selling it.
        - Do not claim that data is sold unless the text says so.
        - Distinguish what the document states from reasonable concerns.
        - If an important detail is missing, say "Not specified".
        - Do not list every collected data item unless it matters.
        - If a category is irrelevant or absent, omit it.
        - Use simple English and short sentences.
        - Avoid lengthy introductions and repeated disclaimers.
        - Do not provide legal advice.
        - Keep the complete response under 180 words.

        Analyze the following document:

        """ + text;


        try {
            GenerateContentResponse response = geminiClient.models.generateContent(
                    "gemini-3.8-flash",
                    prompt,
                    null
            );

            String answer = response.text();

            if (answer == null || answer.isBlank()) {
                return "Gemini returned an empty response. Please try again.";
            }

            return answer;

        } catch (Exception e) {
            logger.error("Gemini API call failed", e);
            return "Unable to analyze the document. Check the application console for details.";
        }
    }
}
