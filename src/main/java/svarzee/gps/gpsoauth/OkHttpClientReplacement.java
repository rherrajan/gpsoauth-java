package svarzee.gps.gpsoauth;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public class OkHttpClientReplacement implements HttpClient{

	@Override
	public Response execute(Request request) throws IOException {
		
        final URL url = request.url().url();
        final String method = url.toString();
        
        final java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
        connection.setUseCaches(false);
        if (request.body() != null) {
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            BufferedSink outbuf;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            outbuf = Okio.buffer(Okio.sink(out));
            request.body().writeTo(outbuf);
            outbuf.close();

            System.out.println("Calling " + method + "\n" + new String(out.toByteArray()));
            outbuf = Okio.buffer(Okio.sink(connection.getOutputStream()));
            request.body().writeTo(outbuf);
            outbuf.close();
        } else {
        	System.out.println("Calling " + method);
        }

        final BufferedSource source = Okio.buffer(Okio.source(connection.getInputStream()));
        if (connection.getResponseCode() != 200) {
            throw new IOException("Fail to call " + method + " :: " + source.readUtf8());
        }
        Response response = new Response.Builder()
                .code(connection.getResponseCode())
                .message(connection.getResponseMessage())
                .request(request)
                .protocol(okhttp3.Protocol.HTTP_1_1)
                .body(new ResponseBody() {
                    @Override
                    public MediaType contentType() {
                        return MediaType.parse(connection.getContentType());
                    }

                    @Override
                    public long contentLength() {
                        return connection.getContentLengthLong();
                    }

                    @Override
                    public BufferedSource source() {
                        return source;
                    }
                })
                .build();
        System.out.println("Call response code: " + response.code() + " message: " + response.message());
        return response;
    }

}
