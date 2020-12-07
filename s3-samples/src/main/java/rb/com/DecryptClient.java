package rb.com;

import static java.lang.System.out;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3EncryptionClientV2Builder;
import com.amazonaws.services.s3.AmazonS3EncryptionV2;
import com.amazonaws.services.s3.model.CryptoConfigurationV2;
import com.amazonaws.services.s3.model.CryptoMode;
import com.amazonaws.services.s3.model.KMSEncryptionMaterialsProvider;

public class DecryptClient {
    private static final String BUCKET_NAME = "REPLACE_ME";
    private static final String S3_KEY = "REPLACE_ME";
    private static final String KMS_KEY_ID = "REPLACE_ME";

    public static void main(String[] args) {
        out.println("Starting s3 encryption!");

        // AmazonS3 s3Client =
        // AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        // Bucket myBucket = s3Client.createBucket(BUCKET_NAME);
        // out.print(myBucket);
        // s3Client.putObject(BUCKET_NAME, "age", "Yuvaan's Detail");

        AmazonS3EncryptionV2 s3Encryption = AmazonS3EncryptionClientV2Builder.standard().withRegion(Regions.US_EAST_1)
                .withCryptoConfiguration(
                        new CryptoConfigurationV2().withCryptoMode((CryptoMode.StrictAuthenticatedEncryption)))
                .withEncryptionMaterialsProvider(new KMSEncryptionMaterialsProvider(KMS_KEY_ID)).build();

        // s3Encryption.putObject(BUCKET_NAME, S3_KEY,
        // "This is the 3rd content to encrypt with a key created in the AWS Console");

        out.println(s3Encryption.getObjectAsString(BUCKET_NAME, S3_KEY));
    }
}
