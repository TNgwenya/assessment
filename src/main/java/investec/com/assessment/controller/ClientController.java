package investec.com.assessment.controller;

import investec.com.assessment.dto.ClientRequestDto;
import investec.com.assessment.model.Client;
import investec.com.assessment.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping("/signup")
    public String saveClient(@RequestBody @Valid ClientRequestDto clientRequestDto) {

        return clientService.addClient(clientRequestDto);
    }

    @PostMapping("/update")
    public String updateClient(@RequestBody @Valid ClientRequestDto clientRequestDto) {

        return clientService.updateClient(clientRequestDto);
    }

    @GetMapping("/firstname")
    public Client getClientByFirstName(@RequestParam String firstName){
        Optional<Client> client = clientService.getClientByFirstName(firstName);
        if(client.isPresent()){
            return (Client) client.get();
        }
        return null;
    }

    @GetMapping("/identity-number")
    public Client getClientByIdNumber(@RequestParam String idNumber) {
        Optional<Client> client = clientService.getClientByIdNumber(idNumber);
        if(client.isPresent()){
            return (Client) client.get();
        }
        return null;
    }

    @GetMapping("/phone-number")
    public Client getClientByPhoneNumber(@RequestParam String phoneNumber) {
        Optional<Client> client = clientService.getClientByPhoneNumber(phoneNumber);
        if(client.isPresent()){
            return (Client) client.get();
        }
        return null;
    }
}