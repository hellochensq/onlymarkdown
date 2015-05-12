package com.onlymarkdown.view;

import org.pegdown.PegDownProcessor;
//import org.parboiled.BaseParser;
//import org.parboiled.Rule;
//import org.parboiled.support.StringBuilderVar;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import com.github.rjeschke.txtmark.Processor;
import com.onlymarkdown.MainApp;

public class EditorController {
	@FXML
	private ScrollPane editor_scroll;
	@FXML
	private TextArea editor_text;
	@FXML
	private WebView editor_webview;
	
	private WebEngine webEngine ;
	
	private PegDownProcessor processor;
	
	// Reference to the main application.
    private MainApp mainApp;
    
    private static String doc = "<!DOCTYPE html><html><head><link href=\"%s\" rel=\"stylesheet\"/></head><body>%s</body></html>";
    private static String css = 
//    		  "https://raw.github.com/nicolashery/markdownpad-github/master/markdownpad-github.css"; 
            "http://kevinburke.bitbucket.org/markdowncss/markdown.css";
//            "http://github.com/huizhong/cnblogs-markdown-css/blob/master/cnblogs-markdown.css";
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public EditorController() {
    }
    
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
	}
	
	 /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
//    	editor_text.getSelectedText()
    	webEngine=editor_webview.getEngine();
    	processor=new PegDownProcessor();
    	editor_text.setOnKeyReleased(new EventHandler<KeyEvent>() {
    	    public void handle(KeyEvent t) {
    	    	preview(editor_text.getText());
    	    }
    	});
    }
    
    private void preview(String text) {
//    	txtmark½âÎömarkdown
//		String html = Processor.process(text);

    	//    	PegDown½âÎömarkdown
    	String html = processor.markdownToHtml(text);
		 html = String.format( doc, css, html);
         webEngine.loadContent(html, "text/html");
	}

}
