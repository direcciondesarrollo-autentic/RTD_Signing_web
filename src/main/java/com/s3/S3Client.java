package com.s3;

import com.Utils.FileUtils;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.InputStream;

/**
 * Created by root on 8/31/16.
 */
public class S3Client {
    public static void main(String[] args) {
        AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider());
        S3Object object = s3Client.getObject(
                new GetObjectRequest("vendettalifecycle", "sign.html"));
        InputStream objectData = object.getObjectContent();

        FileUtils.writeFileFromInputStream(objectData, "sign.html");
    }
}
