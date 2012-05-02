The Helianto Project

The Helianto Project provides an open source API with extension points to help java developers to easily 
create a robust working project. Its layered architecture readily provides common domain objects wired to be 
persisted via ORM to many popular databases. Additionally, domain objects can be handled using a flexible filtering 
abstraction and presented to external application or presentantion consumers through a transactional service layer.

Helianto relies on Hibernate and the Spring Framework to implement many of its features. As a result, the API 
is highly customizable, mostly just by changing configuration. Some of the available modules are:

Helianto-core: core configuration and basic domain model containing business isolation and users abstraction, 
as well as security and transaction control.

Helianto-partner: extends helianto-core domain and service to provide flexible relationship with external 
business parties.

Helianto-document: extends helianto-partner domain and service to introduce document handling. Documents are a core 
abstraction to Helianto, extended by other modules to represent financial records, parts, processes and other. 

For additional information, please, visit the project info at http://www.helianto.org. 

-------------------------------------------------------------------------------
