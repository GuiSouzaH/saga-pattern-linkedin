# Progresso do projeto Saga

Última atualização: 24/11/2026

## Objetivo

Demonstrar o padrão Saga com orquestração entre serviços Spring Boot e RabbitMQ. O fluxo planejado é reservar voo, hotel e carro para uma reserva e desfazer as etapas concluídas quando alguma etapa falhar.

## Implementado até agora

- Projeto Maven multi-módulo com `booking-service`, `orchestrator`, `flight-service`, `hotel-service` e `car-service`.
- `docker-compose.yaml` com PostgreSQL, pgAdmin e RabbitMQ para desenvolvimento local.
- O `booking-service` recebe e persiste a reserva e publica `BookingCreatedEvent`.
- O `orchestrator` consome a criação, persiste uma `SagaInstance` e envia `ReserveFlightCommand`.
- O `flight-service` consome o comando, tenta reservar o voo e publica `FlightReservedEvent`.
- O `orchestrator` recebe a resposta do voo em uma fila/rota própria, avança a saga para a etapa de hotel e publica `ReserveHotelCommand`.
- O `hotel-service` consome `ReserveHotelCommand`, persiste a reserva e publica resultado de sucesso ou falha.
- O `orchestrator` consome o resultado do hotel, marca carro como próxima etapa em caso de sucesso ou registra falha.
- A consulta de reserva por cliente usa um parâmetro de rota explícito.

## Onde paramos

O próximo passo é implementar o fluxo do `car-service` e publicar o resultado para o `orchestrator`.

## O que falta

1. Implementar o fluxo do `car-service` e a conclusão bem-sucedida da saga.
2. Definir falhas e ações compensatórias, por exemplo cancelar reservas de voo/hotel já confirmadas quando uma etapa posterior falhar.
3. Tornar o processamento seguro para mensagens repetidas e eventos concorrentes em todos os serviços.
4. Tratar confiabilidade de publicação/consumo, incluindo retries, dead-letter queue e Outbox para evitar divergência entre persistência e publicação RabbitMQ.
5. Validar o fluxo integrado entre os serviços e ampliar os testes além do carregamento de contexto.

## Observação

O fluxo completo ainda não foi validado de ponta a ponta. O serviço de carro ainda não tem o fluxo Saga implementado, e as ações compensatórias continuam pendentes.
