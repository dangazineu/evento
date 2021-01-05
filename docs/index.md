# Evento
Evento is a Cloud Native Event Sourcing platform based on Domain Driven Design (DDD). It provides Command Query Responsibility Segregation (CQRS) semantics over a Function-as-a-Service (FaaS) architecture. 
## Resources
Evento has three basic resources: Entity, Event and Command. More concepts will be added in the future to cover transactions spanning single and multiple commands. 
### Entity
An Entity is a class of business objects. Its instances have constant identity, while its properties change over time. Entity instances must conform to a user-defined schema and are fully managed by Evento.
In a banking application, for example, Checking and Savings accounts would be defined as entities. 
### Event
A core primitive in Evento is the Event itself. An Event represents something that happened to an Entity instance. It is final and is usually named with verbs in the past tense: ```DepositReceived```, ```CashWithdrawn```, etc. 
Events are published by a Function (see [Functions](#functions)) and are available for all Functions and external applications to consume. 
Events can be consumed externally by listening an Entity's Events Kafka topic.
### Command
Another core primitive in Evento is the Command. A Command describes the intent to perform an action. Commands are defined in imperative mood ```ReceiveDeposit```, ```WithdrawCash```, etc. The execution of the command may succeed or fail for business reasons. In either case an Event should be emitted to communicate the result. i.e: ```WithdrawCash``` would fail if the amount requested is not available, the account is closed, etc. 
Commands are immutable and Evento will retry the execution of a Command that fails without an Event. 
Each Command targets a specific instance of an Entity. Only the function responsible for handling entities of the target entity type will handle the command (more about that in [Functions](#functions)). 
## Functions
Evento is composed by different types of user-defined Functions. Functions are containers that accept and respond Avro payloads. 
### handle(Command, Entity)
Each Command type must have a ```Event handle(Command, Entity)``` function associated with it. The handle function attempts to execute a command on an instance of an entity. If the business logic completes successfully, it returns an Event.
### apply(Event, Entity)
Each Entity type must have a ```Entity apply(Event, Entity)``` function associated with it. This function applies an event to a given entity, and returns the updated entity state.
## Persistence
Persistence is entirely handled by Evento, and in its basic use case, functions don't have to worry about reading or writing any data, as the latest state of any Entity instance can be reproduced by replaying the ```apply``` function over all events from the beginning.