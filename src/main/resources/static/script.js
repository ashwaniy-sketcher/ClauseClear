
const documentText = document.getElementById("documentText");
const analyzeButton = document.getElementById("analyzeButton");
const buttonText = document.getElementById("buttonText");
const charCount = document.getElementById("charCount");
const statusMessage = document.getElementById("statusMessage");
const resultSection = document.getElementById("resultSection");
const resultText = document.getElementById("resultText");
const copyButton = document.getElementById("copyButton");

documentText.addEventListener("input", () => {
    charCount.textContent =
        `${documentText.value.length.toLocaleString()} characters`;
});

analyzeButton.addEventListener("click", async () => {
    const text = documentText.value.trim();

    if (!text) {
        statusMessage.textContent =
            "Please paste a document before analyzing.";
        statusMessage.className = "status error";
        documentText.focus();
        return;
    }

    analyzeButton.disabled = true;
    buttonText.textContent = "Analyzing...";
    statusMessage.className = "status";
    statusMessage.textContent =
        "ClauseClear is analyzing your document. This may take a little time.";
    resultSection.hidden = true;

    try {
        const response = await fetch("/analyze", {
            method: "POST",
            headers: {
                "Content-Type": "text/plain"
            },
            body: text
        });

        const result = await response.text();

        if (!response.ok) {
            throw new Error(
                `The server returned HTTP ${response.status}.`
            );
        }

        if (result.startsWith("Unable to analyze the document.")) {
            throw new Error(
                "The AI analysis failed. Check the IntelliJ console for details."
            );
        }

        resultText.textContent = result;
        resultSection.hidden = false;
        statusMessage.textContent = "Analysis completed successfully.";
        statusMessage.className = "status";

        resultSection.scrollIntoView({
            behavior: "smooth",
            block: "start"
        });
    } catch (error) {
        statusMessage.textContent =
            error.message || "Something went wrong. Please try again.";
        statusMessage.className = "status error";
    } finally {
        analyzeButton.disabled = false;
        buttonText.textContent = "Analyze Document";
    }
});

copyButton.addEventListener("click", async () => {
    try {
        await navigator.clipboard.writeText(resultText.textContent);
        copyButton.textContent = "Copied!";
        setTimeout(() => {
            copyButton.textContent = "Copy Result";
        }, 1500);
    } catch {
        statusMessage.textContent =
            "Unable to copy automatically. Please select and copy the result.";
        statusMessage.className = "status error";
    }
});
