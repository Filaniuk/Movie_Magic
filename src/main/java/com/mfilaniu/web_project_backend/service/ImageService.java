package com.mfilaniu.web_project_backend.service;

import netscape.javascript.JSObject;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@Service
public class ImageService {

    private static final String API_KEY_IMGBB = "bc54196ea232a07cd3060cad414d38fc";
    private static final String UPLOAD_URL = "https://api.imgbb.com/1/upload";

    public String uploadAndGetLink(MultipartFile file) throws IOException {

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("key", API_KEY_IMGBB)
                .addFormDataPart("image", file.getOriginalFilename(),
                        RequestBody.create(file.getBytes(), MediaType.parse(file.getContentType())))
                .build();

        Request request = new Request.Builder()
                .url(UPLOAD_URL)
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();

        Response response = client.newCall(request).execute();

        if(response.isSuccessful()) {
            return getLink(response);
        }

        throw new IOException("Unexpected code " + response.code());

    }

    private String getLink(Response response) throws IOException {
        JSONObject obj = new JSONObject(response.body().string());

        return obj.getJSONObject("data").getString("url");
    }

}
