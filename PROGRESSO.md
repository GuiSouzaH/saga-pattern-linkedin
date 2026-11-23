# Progresso do projeto Saga

Última atualização: 23/11/2026

## Objetivo

Demonstrar o padrão Saga com orquestração entre serviços Spring Boot e RabbitMQ. O fluxo planejado é reservar voo, hotel e carro para uma reserva e desfazer as etapas concluídas quando alguma etapa falhar.

## Implementado até agora

- Projeto Maven multi-módulo com `booking-service`, `orchestrator`, `flight-service`, `hotel-service` e `car-service`.
- `docker-compose.yaml` com PostgreSQL, pgAdmin e RabbitMQ para desenvolvimento local.
- O `booking-service` recebe e persiste a reserva e publica `BookingCreatedEvent`.
- O `orchestrator` consome a criação, persiste uma `SagaInstance` e envia `ReserveFlightCommand`.
- O `flight-service` consome o comando, tenta reservar o voo e publica `FlightReservedEvent`.
- O `orchestrator` recebe a resposta do voo em uma fila/rota própria, avança a saga para a etapa de hotel e publica `ReserveHotelCommand`.
- O contrato `ReserveHotelCommand` e o enum `CommandType` já existem no `hotel-service`.

## Onde paramos

O próximo passo é implementar o recebimento de `ReserveHotelCommand` no `hotel-service`: configurar a fila e a rota `hotel.reserve`, criar o listener e a lógica de reserva e publicar um evento de resposta, como `HotelReservedEvent`, pela rota `hotel.reserved`. Depois, o `orchestrator` precisa consumir essa resposta e avançar a saga.

## O que falta

1. Completar o fluxo do hotel: modelo e persistência da reserva, listener do comando e publicação do evento de sucesso/falha.
2. Fazer o `orchestrator` consumir a resposta do hotel e iniciar a etapa do carro.
3. Implementar o fluxo do `car-service` e a conclusão bem-sucedida da saga.
4. Definir falhas e ações compensatórias, por exemplo cancelar reservas de voo/hotel já confirmadas quando uma etapa posterior falhar.
5. Tornar o processamento seguro para mensagens repetidas e eventos concorrentes; avaliar controle otimista de versão e transições idempotentes.
6. Tratar confiabilidade de publicação/consumo, incluindo retries, dead-letter queue e Outbox para evitar divergência entre persistência e publicação RabbitMQ.
7. Adicionar logs úteis com `sagaId`, `bookingId` e etapa, além de validar o fluxo integrado entre os serviços.

## Observação

O fluxo completo ainda não foi validado de ponta a ponta. Os serviços de hotel e carro estão em estágios iniciais; o hotel tem apenas parte do contrato de comando, e o carro ainda não tem o fluxo Saga implementado.
