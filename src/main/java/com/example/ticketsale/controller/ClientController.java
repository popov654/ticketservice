package com.example.ticketsale.controller;

import com.example.ticketsale.dto.ClientWithBalanceDto;
import com.example.ticketsale.dto.FillBalanceDto;
import com.example.ticketsale.dto.Response;
import com.example.ticketsale.dto.mapper.ClientWithBalanceMapper;
import com.example.ticketsale.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    private final ClientWithBalanceMapper clientMapper;

    @GetMapping({ "", "/" })
    public List<ClientWithBalanceDto> getAll() {
        return clientMapper.mapClientsToClientDtos(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ClientWithBalanceDto get(@PathVariable("id") Long id) {
        return clientMapper.clientToClientWithBalanceDto(clientService.getClientById(id));
    }

    @PostMapping("/{id}/fill-balance")
    public ResponseEntity<Response> fill(@PathVariable("id") Long clientId, @RequestBody FillBalanceDto data) {
        clientService.fillBalance(clientId, data.getAmount());
        return new ResponseEntity<Response>(Response.success("ok"), HttpStatusCode.valueOf(200));
    }
}
