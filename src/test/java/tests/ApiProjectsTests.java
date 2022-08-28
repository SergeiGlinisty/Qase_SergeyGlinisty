package tests;

import com.google.gson.Gson;
import adapters.ProjectAdapter;
import models.Project;
import models.ProjectResponse;
import models.Result;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiProjectsTests {
    private final String expectedJson = "{\"status\":true,\"result\":{\"total\":5,\"filtered\":5,\"count\":5,\"entities\":[{\"title\":\"QA19\",\"code\":\"Q19\",\"counts\":{\"cases\":17,\"suites\":2,\"milestones\":0,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":10,\"open\":10}}},{\"title\":\"fddgdg\",\"code\":\"SDF\",\"counts\":{\"cases\":0,\"suites\":0,\"milestones\":0,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":0,\"open\":0}}},{\"title\":\"fghjk\",\"code\":\"CODE2\",\"counts\":{\"cases\":0,\"suites\":0,\"milestones\":0,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":0,\"open\":0}}},{\"title\":\"Hello\",\"code\":\"CODE3\",\"counts\":{\"cases\":0,\"suites\":0,\"milestones\":0,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":0,\"open\":0}}},{\"title\":\"Hi\",\"code\":\"DEMO\",\"counts\":{\"cases\":0,\"suites\":0,\"milestones\":0,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":2,\"open\":2}}}]}}";
    private final String expectedJsonByCode = "{\"status\":true,\"result\":{\"title\":\"Hi\",\"code\":\"DEMO\",\"counts\":{\"cases\":0,\"suites\":0,\"milestones\":0,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":3,\"open\":3}}}}";
    private final String expectedJsonDeleteByCode = "{\"status\":true}";
    private final static Gson gson = new Gson();
    ProjectAdapter projectAdapter = new ProjectAdapter();

    @Test
    public void getAllProjectsTest(){
    String responseBody = projectAdapter.getAllProjects(200);
    Assert.assertEquals(responseBody, expectedJson);
    }

    @Test
    public void createProjectTest(){
    String testCode = "DEMO";

    Project project = Project.builder()
            .title("Hi")
            .code(testCode)
            .description("world")
            .build();

        ProjectResponse expectedResponseBody = ProjectResponse
                .builder()
                .result(Result
                        .builder()
                        .code(testCode)
                        .build())
                .build();

        String actualResponseBody = projectAdapter.createProject(200,gson.toJson(project));
        Assert.assertEquals(gson.fromJson(actualResponseBody,ProjectResponse.class),expectedResponseBody);

    }


    @Test
    public void getProjectByCodeTest(){

        String responseBody = projectAdapter.getProjectByCode(200,"DEMO");
        Assert.assertEquals(responseBody, expectedJsonByCode);

    }


    @Test
    public void deleteProjectByCodeTest(){
        String responseBody = projectAdapter.deleteProjectByCode(200,"CODE3");
        Assert.assertEquals(responseBody, expectedJsonDeleteByCode);

    }

}
