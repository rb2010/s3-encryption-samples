package rb.com;

import static java.lang.System.out;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3EncryptionClientV2Builder;
import com.amazonaws.services.s3.AmazonS3EncryptionV2;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CryptoConfigurationV2;
import com.amazonaws.services.s3.model.CryptoMode;
import com.amazonaws.services.s3.model.KMSEncryptionMaterialsProvider;

/**
 * Hello world!
 *
 */
public class S3ClientEncryption {

        private static final String BUCKET_NAME = "REPACE_ME";
        private static final String ENCRYPTED_KEY3 = "REPACE_ME";
        private static final String keyId = "REPACE_ME";

        public static void main(String[] args) {
                out.println("Starting s3 encryption!");

                // AmazonS3 s3Client =
                // AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
                // Bucket myBucket = s3Client.createBucket(BUCKET_NAME);
                // out.print(myBucket);
                // s3Client.putObject(BUCKET_NAME, "age", "Yuvaan's Detail");

                AmazonS3EncryptionV2 s3Encryption = AmazonS3EncryptionClientV2Builder.standard()
                                .withRegion(Regions.US_EAST_1)
                                .withCryptoConfiguration(new CryptoConfigurationV2()
                                                .withCryptoMode((CryptoMode.StrictAuthenticatedEncryption)))
                                .withEncryptionMaterialsProvider(new KMSEncryptionMaterialsProvider(keyId)).build();

                s3Encryption.putObject(BUCKET_NAME, ENCRYPTED_KEY3,
                                "This is the 3rd content to encrypt with a key created in the AWS Console");

                out.println(s3Encryption.getObjectAsString(BUCKET_NAME, ENCRYPTED_KEY3));
        }
}
