package com.htetznaing.bcfsender;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;

public class BCFSender {
    private WebView webView;
    private OnSendListener sendListener;
    private String site,name,email,message;
    private Context context;
    private boolean isRunning = false;

    public BCFSender(@NonNull Context context, @NonNull String yourSite, @NonNull OnSendListener onSendListener){
        site = yourSite;
        sendListener = onSendListener;
        this.context=context;
        init();
    }

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface", "AddJavascriptInterface"})
    private void init(){
        webView = new WebView(context);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyInterface(),"BCFSenderByHtetzNaing");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (isRunning && url.contains(site)) {
                    injectJs2WebView(createFormData(name, email, message));
                }
            }
        });
    }

    public void send(@NonNull String name,@NonNull String email,@NonNull String message){
        if (!isRunning) {
            isRunning = true;
            this.name = name;
            this.email = email;
            this.message = message;
            if (URLUtil.isValidUrl(site)) {
                if (isValidEmail(email)) {
                    if (name.length() > 1) {
                        if (message.length() > 1) {
                            webView.loadUrl(site);
                        } else sendListener.onResult(false, "Please input your message.");
                    } else sendListener.onResult(false, "Please input your name.");
                } else sendListener.onResult(false, "A valid email address is required.");
            } else sendListener.onResult(false, "Please input valid your site url!");
        }
    }

    private class MyInterface{
        @SuppressWarnings("unused")
        @JavascriptInterface
        public void success(final String message){
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    isRunning = false;
                    sendListener.onResult(message.toLowerCase().contains("your message has been sent"),message);
                }
            });
        }

        @SuppressWarnings("unused")
        @JavascriptInterface
        public void error(final String message){
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    isRunning = false;
                    sendListener.onResult(false,message);
                }
            });
        }
    }

    private void injectJs2WebView(String js){
        webView.loadUrl("javascript: (function() {" + js + "})()");
    }

    public interface OnSendListener{
        void onResult(boolean success,String message);
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private String createFormData(String name,String email,String message){
        return "/* \n" +
                "Auto sent email from blogspot form with Javascript\n" +
                "    By\n" +
                "Khun Htetz Naing\n" +
                "*/\n" +
                "function errorListener(e) {\n" +
                "    if (error.textContent) {\n" +
                "        console.log('Error => ' + error.textContent);\n" +
                "        BCFSenderByHtetzNaing.error(error.textContent);\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "function successListener(e) {\n" +
                "    if (success.textContent) {\n" +
                "        if (success.textContent.toLocaleLowerCase().indexOf('sending') == -1) {\n" +
                "            console.log('Success => ' + success.textContent);\n" +
                "            BCFSenderByHtetzNaing.success(success.textContent);\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "\n" +
                "var f_name = document.getElementById('ContactForm1_contact-form-name'),\n" +
                "    email = document.getElementById('ContactForm1_contact-form-email'),\n" +
                "    message = document.getElementById('ContactForm1_contact-form-email-message'),\n" +
                "    submit = document.getElementById('ContactForm1_contact-form-submit'),\n" +
                "    error = document.getElementById('ContactForm1_contact-form-error-message'),\n" +
                "    success = document.getElementById('ContactForm1_contact-form-success-message');\n" +
                "\n" +
                "function canWork() {\n" +
                "    return isHas(f_name) && isHas(email) && isHas(message) && isHas(submit) && isHas(error) && isHas(success);\n" +
                "}\n" +
                "\n" +
                "function isHas(e) {\n" +
                "    return document.body.contains(e);\n" +
                "}\n" +
                "\n" +
                "if (canWork()) {\n" +
                "    console.log('**BCFSenderByHtetzNaing is Ready**');\n" +
                "    error.addEventListener('DOMSubtreeModified', errorListener);\n" +
                "    success.addEventListener('DOMSubtreeModified', successListener);\n" +
                "    letSend('"+name+"', '"+email+"', '"+message+"');\n" +
                "} else {\n" +
                "    var msg = 'You need to add Contact Form in your blog! => '+window.location.href;\n" +
                "    console.log(msg);\n" +
                "    BCFSenderByHtetzNaing.error(msg);\n" +
                "}\n" +
                "\n" +
                "function letSend(yourName, yourEmail, yourMessage) {\n" +
                "    f_name.value = yourName, email.value = yourEmail, message.value = yourMessage;\n" +
                "    submit.click();\n" +
                "}";
    }
}

