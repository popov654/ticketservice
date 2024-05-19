package com.example.ticketsale.dto.mapper;

import com.example.ticketsale.dto.ClientDto;
import com.example.ticketsale.model.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto clientToClientDto(Client client);
    Client clientDtoToClient(ClientDto clientDto);
    List<ClientDto> mapClientsToClientDtos(List<Client> clients);
    List<Client> mapClientDtosToClients(List<ClientDto> clientDtos);
}
