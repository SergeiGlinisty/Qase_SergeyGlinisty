package tests;

import adapters.DefectAdapter;
import com.google.gson.Gson;
import models.Defect;
import models.DefectResponse;
import models.Result;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiDefectsTests {
    private final static Gson gson = new Gson();
    private final String expectedJson = "{\"status\":true,\"result\":{\"total\":3,\"filtered\":3,\"count\":3,\"entities\":[{\"id\":1,\"title\":\"Defect_1\",\"actual_result\":\"bag\",\"status\":\"open\",\"milestone_id\":null,\"project_id\":237235,\"severity\":\"normal\",\"member_id\":1,\"attachments\":[],\"custom_fields\":[],\"external_data\":\"{}\",\"resolved_at\":null,\"created\":\"2022-08-28 15:38:37\",\"updated\":\"2022-08-28 15:38:37\",\"created_at\":\"2022-08-28T15:38:37+00:00\",\"updated_at\":\"2022-08-28T15:38:37+00:00\",\"tags\":[]},{\"id\":2,\"title\":\"Defect_2\",\"actual_result\":\"The button isn\\u0027t pressed\",\"status\":\"open\",\"milestone_id\":null,\"project_id\":237235,\"severity\":\"major\",\"member_id\":1,\"attachments\":[],\"custom_fields\":[],\"external_data\":\"{}\",\"resolved_at\":null,\"created\":\"2022-08-28 15:39:02\",\"updated\":\"2022-08-28 15:39:02\",\"created_at\":\"2022-08-28T15:39:02+00:00\",\"updated_at\":\"2022-08-28T15:39:02+00:00\",\"tags\":[]},{\"id\":3,\"title\":\"Defect_3\",\"actual_result\":\"The text isn\\u0027t displayed\",\"status\":\"open\",\"milestone_id\":null,\"project_id\":237235,\"severity\":\"blocker\",\"member_id\":1,\"attachments\":[],\"custom_fields\":[],\"external_data\":\"{}\",\"resolved_at\":null,\"created\":\"2022-08-28 15:39:26\",\"updated\":\"2022-08-28 15:39:26\",\"created_at\":\"2022-08-28T15:39:26+00:00\",\"updated_at\":\"2022-08-28T15:39:26+00:00\",\"tags\":[]}]}}";
    private final String expectedJsonSpecific = "{\"status\":true,\"result\":{\"id\":1,\"title\":\"Defect_1\",\"actual_result\":\"bag\",\"status\":\"open\",\"milestone_id\":null,\"project_id\":237235,\"severity\":\"normal\",\"member_id\":1,\"attachments\":[],\"custom_fields\":[],\"external_data\":\"{}\",\"resolved_at\":null,\"created\":\"2022-08-28 15:38:37\",\"updated\":\"2022-08-28 15:38:37\",\"created_at\":\"2022-08-28T15:38:37+00:00\",\"updated_at\":\"2022-08-28T15:38:37+00:00\",\"tags\":[]}}";
    private final String expectedJsonDelete = "{\"status\":true,\"result\":{\"id\":9}}";
    private final String expectedJsonUpdate = "";
    DefectAdapter defectAdapter = new DefectAdapter();

    @Test
    public void getAllDefectsTest() {
        String responseBody = defectAdapter.getAllDefects(200, "DEMO");
        Assert.assertEquals(responseBody, expectedJson);

    }


    @Test
    public void createDefectTest() {
        String testCode = "DEMO";

        Defect defect = Defect.builder()
                .title("Defect_4")
                .code(testCode)
                .actual_result("The login page does not open")
                .severity(4)
                .build();

        DefectResponse expectedResponseBody = DefectResponse
                .builder()
                .result(Result
                        .builder()
                        .id(7)
                        .build())
                .build();

        String actualResponseBody = defectAdapter.createDefect(200, gson.toJson(defect));
        Assert.assertEquals(gson.fromJson(actualResponseBody, DefectResponse.class), expectedResponseBody);

    }


    @Test
    public void getSpecificDefectTest() {

        String responseBody = defectAdapter.getSpecificDefect(200, "DEMO", 1);
        Assert.assertEquals(responseBody, expectedJsonSpecific);

    }

    @Test
    public void deleteDefectTest() {
        String responseBody = defectAdapter.deleteDefect(200, "DEMO", 9);
        Assert.assertEquals(responseBody, expectedJsonDelete);

    }

    @Test
    public void updateDefectTest() {
        String responseBody = defectAdapter.updateDefect(200, "Q19", 8);
        Assert.assertEquals(responseBody, expectedJsonUpdate);

    }

}
