package investec.com.assessment.service;

import com.sun.jdi.request.DuplicateRequestException;
import investec.com.assessment.dto.ClientRequestDto;
import investec.com.assessment.model.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Service
public class ClientService {
    private List<Client> clientList;
    public ClientService(){

        clientList = new ArrayList<>();

        Client client1 = Client.buildClient("Thabang", "Ngwenya", "0832561478","9000000000000","sowewhere" );
        Client client2 = Client.buildClient("Sam", "Tam", "000000000","80000000000","africa" );
        Client client3 = Client.buildClient("Tom", "Jerry", "1111111111","70000000000","asia" );
        Client client4 = Client.buildClient("John", "Cena", "2222222222","60000000000","tokyo" );
        Client client5 = Client.buildClient("Tim", "Trin", "10111","9001075650487","soweto" );

        clientList.addAll(Arrays.asList(client1,client2,client3,client3,client4,client5));
    }

    public String addClient(ClientRequestDto clientRequestDto) {

        Client client = Client.buildClient(clientRequestDto.getFirstName(), clientRequestDto.getLastName(), clientRequestDto.getMobileNumber(), clientRequestDto.getIdentityNumber(), clientRequestDto.getPhysicalAddress());

        if(clientList.contains(client)){
            throw new DuplicateRequestException("Cannot add duplicate client. ID number " + client.getIdentityNumber());
        }

        if(clientList.add(client)) {
            return "Client added successfully";
        }else {
            return "Something went wrong";
        }

    }
    public String updateClient(ClientRequestDto clientRequestDto) {

        String updateClientString = "Client not updated";
        Client updateClient = Client.buildClient(clientRequestDto.getFirstName(), clientRequestDto.getLastName(), clientRequestDto.getMobileNumber(), clientRequestDto.getIdentityNumber(), clientRequestDto.getPhysicalAddress());

        for (int i = 0; i < clientList.size(); i++) {
            Client client = clientList.get(i);
            if (client.getIdentityNumber().equals(clientRequestDto.getIdentityNumber())) {
                clientList.set(i, updateClient);
                updateClientString = "Client details Updated";
                break;
            }
        }
        return updateClientString;
    }
    public Optional<Client> getClientByFirstName(String firstName) {
        Client clientFound = clientList.stream()
                .filter((client) -> client.getFirstName().equals(firstName))
                .findFirst()
                .get();

        return Optional.of(clientFound);
    }
    public Optional<Client> getClientByIdNumber(String idnumber) {
        Client clientFound = clientList.stream()
                .filter((client) -> client.getIdentityNumber().equals(idnumber))
                .findFirst()
                .get();

        return Optional.of(clientFound);
    }
    public Optional<Client> getClientByPhoneNumber(String mobileNumber) {
        Client clientFound = clientList.stream()
                .filter((client) -> client.getMobileNumber().equals(mobileNumber))
                .findFirst()
                .get();

        return Optional.of(clientFound);
    }

}
