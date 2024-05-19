package com.example.ticketsale.controller;

import com.example.ticketsale.dto.ClientWithBalanceDto;
import com.example.ticketsale.dto.FillBalanceDto;
import com.example.ticketsale.dto.ResponseSuccess;
import com.example.ticketsale.dto.mapper.ClientWithBalanceMapper;
import com.example.ticketsale.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    private ClientWithBalanceMapper clientMapper;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping({ "", "/" })
    public List<ClientWithBalanceDto> getAll() {
        return clientMapper.mapClientsToClientDtos(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ClientWithBalanceDto get(@PathVariable("id") Long id) {
        return clientMapper.clientToClientWithBalanceDto(clientService.getClientById(id));
    }

    @PostMapping("/{id}/fill-balance")
    public Object fill(@PathVariable("id") Long clientId, @RequestBody FillBalanceDto data) {
        clientService.fillBalance(clientId, data.getAmount());
        return new ResponseSuccess("ok");
    }
}
