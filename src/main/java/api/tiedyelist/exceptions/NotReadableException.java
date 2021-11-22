package api.tiedyelist.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.InputStream;

public class NotReadableException implements HttpInputMessage {
    @Override
    public InputStream getBody() {
        return null;
    }

    @Override
    public HttpHeaders getHeaders() {
        return null;
    }
}
