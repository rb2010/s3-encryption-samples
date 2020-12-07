package rb.com;

import static java.lang.System.out;

import java.io.File;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.SSEAwsKeyManagementParams;

/**
 * Hello world!
 *
 */
public class S3SSECustomerManaged {

        private static final String FILE_NAME = "REPLACE_ME";
        private static final String BUCKET_NAME = "REPLACE_ME";
        private static final String S3_KEY = "REPLACE_ME";
        private static final String KMS_KEY_ID = "REPLACE_ME";

        public static void main(String[] args) {
                out.println("Starting s3 encryption!");

                AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

                //Make sure bucket exists or create one 
                // Notice the keyId
                PutObjectRequest putRequest = new PutObjectRequest(BUCKET_NAME, S3_KEY, new File(FILE_NAME))
                                .withSSEAwsKeyManagementParams(new SSEAwsKeyManagementParams(KMS_KEY_ID));
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType("plain/text");
                metadata.addUserMetadata("title", "someTitle");
                putRequest.setMetadata(metadata);
                s3Client.putObject(putRequest);

                out.println(s3Client.getObjectAsString(BUCKET_NAME, S3_KEY));
        }
}
