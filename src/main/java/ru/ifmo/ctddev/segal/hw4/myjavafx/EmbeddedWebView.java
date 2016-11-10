package ru.ifmo.ctddev.segal.hw4.myjavafx;

import com.sun.javafx.webkit.Accessor;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * A WebView which has getters and setters for content or a document url.
 * It also sets the page background color to transparent.
 *
 * Usage in FXML element is:
 *
 *   by specifying CDATA escaped html content:
 *
 *     <EmbeddedWebView fx:id="embeddedWebView">
 *         <content>
 *             <![CDATA[
 *                 <h3>Embedded WebView</h3>
 *                 <p>HTML content inline in FXML</p>
 *             ]]>
 *         </content>
 *     </EmbeddedWebView>
 *
 */
public class EmbeddedWebView extends StackPane {

    final private WebEngine webEngine;

    private String content;

    public EmbeddedWebView() {
        WebView webView = new WebView();
        getChildren().add(webView);
        webEngine = webView.getEngine();
    }

    public String getContent() {
        return content;
    }

    /**
     * Loads HTML content directly into the WebView.
     * @param content an HTML content string to load into the WebView.
     */
    public void setContent(String content) {
        this.content = content;
        webEngine.loadContent(content);
        Accessor.getPageFor(webEngine).setBackgroundColor(0);
    }

}
