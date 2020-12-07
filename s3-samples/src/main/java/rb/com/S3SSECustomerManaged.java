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

        private static final String FILE_NAME = "/Users/ranjanbhandari/projects/sample-s3-java/s3-samples/src/main/resources/log4j2.xml";
        private static final String BUCKET_NAME = "REPACE_ME";
        private static final String ENCRYPTED_KEY3 = "REPACE_ME";
        private static final String keyId = "REPACE_ME";

        public static void main(String[] args) {
                out.println("Starting s3 encryption!");

                AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

                // Notice the keyId
                PutObjectRequest putRequest = new PutObjectRequest(BUCKET_NAME, ENCRYPTED_KEY3, new File(FILE_NAME))
                                .withSSEAwsKeyManagementParams(new SSEAwsKeyManagementParams(keyId));
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType("plain/text");
                metadata.addUserMetadata("title", "someTitle");
                putRequest.setMetadata(metadata);
                s3Client.putObject(putRequest);

                // out.println(s3Encryption.getObjectAsString(BUCKET_NAME, ENCRYPTED_KEY3));
        }
}
