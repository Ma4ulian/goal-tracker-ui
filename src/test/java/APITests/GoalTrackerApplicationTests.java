package APITests;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;


class GoalTrackerApplicationTests {


    class TestUser{
        public String BearerToken;
        public String Email;
    }
    public TestUser testUser = new TestUser()
    {{
        BearerToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJocnlob3JpaUB1a3IubmV0IFVTRVIifQ.WSFmls_hCSA-A6uBDRRASrgeI-W0IUbBXkZPvjB7buTxRqt5PVmN0zJxsew0Jv73jQtt0KSf85HV9ZQKMOw_ow";
        Email = "hryhorii@ukr.net";
    }};

    @Test
    void userAuthorized_should_accessOwnProfile() throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://34.222.107.139:8080/goaltracker/api/profile/me")
                .method("GET", null)
                .addHeader("Authorization", "Bearer " + testUser.BearerToken)
                .addHeader("Cookie", "JSESSIONID=EB92BA1DB400CD58275B34299CCD2971")
                .build();
        Response response = client.newCall(request).execute();
        String json = response.body().string();

        ObjectMapper om = new ObjectMapper();
        UserProfile userProfile = om.readValue(json, UserProfile.class);

        Assert.assertEquals(userProfile.email, testUser.Email);
    }

    @Test
    void notAuthorizedUser_shouldNot_accessResource() throws IOException {
        String invalidBearerToken = "edfgdfg";
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://34.222.107.139:8080/goaltracker/api/profile/me")
                .method("GET", null)
                .addHeader("Authorization", "Bearer " + invalidBearerToken)
                .addHeader("Cookie", "JSESSIONID=EB92BA1DB400CD58275B34299CCD2971")
                .build();
        Response response = client.newCall(request).execute();

        Assert.assertNotEquals(response.code(), 200);
    }
}