package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import investec.com.assessment.controller.ClientController;
import investec.com.assessment.dto.ClientRequestDto;
import investec.com.assessment.service.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest(controllers = ClientController.class)
@RunWith(SpringRunner.class)

public class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    public void addClientTestSuccess() throws Exception {

        when(clientService.addClient(Mockito.any())).thenReturn("Client added successfully");

        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setFirstName("Thabang");
        clientRequestDto.setLastName("Ngwenya");
        clientRequestDto.setIdentityNumber("9001075555555");
        clientRequestDto.setPhysicalAddress("johannesburg");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/client/signup")
                        .content(asJasonString(clientRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(content().string("Client added successfully"));
    }
    @Test
    public void addClientTestFail() throws Exception {

        when(clientService.addClient(Mockito.any())).thenReturn("Client added successfully");

        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setFirstName("Thabang");
        clientRequestDto.setLastName("Ngwenya");
        clientRequestDto.setIdentityNumber("9");
        clientRequestDto.setPhysicalAddress("johannesburg");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/client/signup")
                        .content(asJasonString(clientRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(content().json("{\"identityNumber\":\"Invalid Id Number, please try again.\"}"));
    }

    @Test
    public void updateClientTestSuccess() throws Exception {

        when(clientService.updateClient(Mockito.any())).thenReturn("Client details Updated");

        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setFirstName("Thabang");
        clientRequestDto.setLastName("Ngwenya");
        clientRequestDto.setIdentityNumber("9001075555555");
        clientRequestDto.setPhysicalAddress("johannesburg");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/client/update")
                        .content(asJasonString(clientRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(content().string("Client added successfully"));
    }

    @Test
    public void updateClientTestFail() throws Exception {

        when(clientService.addClient(Mockito.any())).thenReturn("Client not updated");

        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setFirstName("Thabang");
        clientRequestDto.setLastName("Ngwenya");
        clientRequestDto.setIdentityNumber("9001075555555");
        clientRequestDto.setPhysicalAddress("johannesburg");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/client/update")
                        .content(asJasonString(clientRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(content().string("Client not updated"));
    }

    @Test
    public void getClientByFirstnameSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/firstname/", "Thabang"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$firstName", is("Thabang")))
                .andExpect((ResultMatcher) jsonPath("$.identity", is("Jack"))
                );
    }

    @Test
    public void getClientByFirstnameFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/firstname/", "Thabang"))
                //   .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$firstName", is("Thabang")))
                .andExpect((ResultMatcher) jsonPath("$.identity", is("Jack"))
                );
    }

    @Test
    public void getClientByIdentityNumberPass() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/identity-number/", "900000"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$firstName", is("Thabang")))
                .andExpect((ResultMatcher) jsonPath("$.identity", is("Jack"))
                );

    }



    @Test
    public void getClientByIdentityNumberFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/identity-number/", "900000"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$firstName", is("Thabang")))
                .andExpect((ResultMatcher) jsonPath("$.identity", is("Jack"))
                );
    }
    @Test
    public void getClientByPhoneNumberPass() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/phone-number/", "900000"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$firstName", is("Thabang")))
                .andExpect((ResultMatcher) jsonPath("$.identity", is("Jack"))
                );

    }

    @Test
    public void getClientByPhoneNumberFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/phone-number/", "900000"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$firstName", is("Thabang")))
                .andExpect((ResultMatcher) jsonPath("$.identity", is("Jack"))
                );
    }

    private byte[] asJasonString(ClientRequestDto clientRequestDto) {
        try {
            return new ObjectMapper().writeValueAsString(clientRequestDto).getBytes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
