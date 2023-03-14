package service;

import com.sun.jdi.request.DuplicateRequestException;
import investec.com.assessment.dto.ClientRequestDto;
import investec.com.assessment.model.Client;
import investec.com.assessment.service.ClientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
public class ClientServiceTest {

    private ClientService clientService = new ClientService();
    private ClientRequestDto clientRequestDto = new ClientRequestDto();
    private Client client = new Client();
    @Before
    public void init(){

        clientRequestDto.setFirstName("Name");
        clientRequestDto.setLastName("LastName");
        clientRequestDto.setIdentityNumber("125486");
        clientRequestDto.setPhysicalAddress("johannesburg");

        client.setFirstName("Thabang");
        client.setLastName("Ngwenya");
        client.setMobileNumber("0832561478");
        client.setIdentityNumber("9000000000000");
        client.setPhysicalAddress("johannesburg");
    }

    @Test
    public void addClientTestFail(){

        clientService.addClient(clientRequestDto);
        Assert.assertThrows(DuplicateRequestException.class, () -> clientService.addClient(clientRequestDto));

    }

    @Test
    public void addClientTestPass(){

        Assert.assertEquals("Client added successfully", clientService.addClient(clientRequestDto));
    }

    @Test
    public void updateClientFail(){
        Assert.assertEquals("Client not updated", clientService.updateClient(clientRequestDto));
    }

    @Test
    public void updateClientTestPass(){


        clientRequestDto.setFirstName("Thabang");
        clientRequestDto.setLastName("Ngwenya");
        clientRequestDto.setIdentityNumber("9000000000000");
        clientRequestDto.setPhysicalAddress("johannesburg");

        Assert.assertEquals("Client details Updated", clientService.updateClient(clientRequestDto));
    }

    @Test
    public void getClientByFirstNameTestFail(){
        Assert.assertThrows(NoSuchElementException.class, ()->clientService.getClientByFirstName("Test").get());
    }

    @Test
    public void getClientByFirstNameTestPass(){
        Assert.assertEquals(client, clientService.getClientByFirstName("Thabang").get());
    }

    @Test
    public void getClientByIdNumberFail(){
        Assert.assertThrows(NoSuchElementException.class, ()->clientService.getClientByIdNumber("Test-Id").get());
    }

    @Test
    public void getClientByIdNumberTestPass(){
        Assert.assertEquals(client, clientService.getClientByIdNumber("9000000000000").get());
    }

    @Test
    public void getClientByPhoneNumberTestFail(){
        Assert.assertThrows(NoSuchElementException.class, ()->clientService.getClientByPhoneNumber("Test-phone").get());
    }

    @Test
    public void getClientByPhoneNumberTestPass(){
        Assert.assertEquals(client, clientService.getClientByPhoneNumber("0832561478").get());
    }
}
