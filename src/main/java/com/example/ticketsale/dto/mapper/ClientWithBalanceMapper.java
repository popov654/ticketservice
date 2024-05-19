package com.example.ticketsale.dto.mapper;

import com.example.ticketsale.dto.ClientWithBalanceDto;
import com.example.ticketsale.model.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientWithBalanceMapper {
    ClientWithBalanceDto clientToClientWithBalanceDto(Client client);
    Client clientWithBalanceDtoToClient(ClientWithBalanceDto clientWithBalanceDto);
    List<ClientWithBalanceDto> mapClientsToClientDtos(List<Client> clients);
    List<Client> mapClientDtosToClients(List<ClientWithBalanceDto> clientDtos);
}
