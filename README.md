<a href="https://www.extrawest.com/"><img src="https://drive.google.com/uc?export=view&id=1kXfNj5WfW2oSMzQR82xYBI6Bw_W8-LpK" width="20%"></a>
# Extrawest-OCPI-2.2.1-EMSP-Server

## Badges

![build](https://img.shields.io/github/actions/workflow/status/extrawest/Extrawest-OCPI-2.2.1-EMSP-Server/docker-image.yml?style=for-the-badge)
![release](https://img.shields.io/github/v/release/extrawest/Extrawest-OCPI-2.2.1-EMSP-Server?style=for-the-badge)
[![Java CI with Maven](https://github.com/extrawest/Extrawest-OCPI-2.2.1-EMSP-Server/actions/workflows/release-publish.yml/badge.svg)](https://github.com/extrawest/Extrawest-OCPI-2.2.1/actions/workflows/snapshot-publish.yml)
![contr](https://img.shields.io/github/contributors/extrawest/Extrawest-OCPI-2.2.1-EMSP-Server?style=for-the-badge)
![commits](https://img.shields.io/github/commit-activity/m/extrawest/Extrawest-OCPI-2.2.1-EMSP-Server?style=for-the-badge)
![lastcommit](https://img.shields.io/github/last-commit/extrawest/Extrawest-OCPI-2.2.1-EMSP-Server?style=for-the-badge)
![OCPI](https://img.shields.io/badge/OCPI-2.2.1-yellowgreen?style=for-the-badge)
![JDK](https://img.shields.io/badge/JDK-17-yellow?style=for-the-badge)
![social](https://img.shields.io/github/forks/extrawest/Extrawest-OCPI-2.2.1-EMSP-Server?style=for-the-badge)

## Table of Contents

- [Field of use](#field-of-use)
- [How is OCPI being used?](#how-is-ocpi-being-used)
- [Description](#description)
- [Maven](#maven)
- [Dependencies](#dependencies)
- [Requirements](#requirements)
- [Changelog](#changelog)
- [Getting Started](#getting-started)

## Field of use
Open Charge Point Interface (OCPI) is an open protocol used for connections between charging station operators and service providers. Simply put, this protocol facilitates automated roaming for EV drivers between different EV charging networks. This interface supports the affordability and accessibility of charging infrastructure for EV owners, allowing drivers to charge across different networks. The protocol provides accurate data on charging stations, such as location, accessibility and pricing, and takes into account real-time billing and mobile access to charging stations.
The OCPI protocol is managed and maintained by the EVRoaming Foundation, making it freely available to software vendors.

## How is OCPI being used?
OCPI consists of several modules. The role of a company in the EV landscape determines which modules of OCPI you need and how you use it.


Description
=============

A CPO library of Open Charge-Point Interface. This library is designed to make life easier for those who want to
implement a CPO application.

With this library, you can easily get started with the Open Charge-Point Interface.

Please note, this is a library and not an application, so there is no main method.

Currently we support 2.2.1 OCPI version.

At 2.2.1 you can choose what kind of events will be supported by CPO or EMSP.

#### Related projects:

1. [BDD tests for eMSP](https://github.com/extrawest/bdd_ocpi_2.2.1_emsp)
2. [OCPI 2.2.1 CPO Library](https://github.com/extrawest/Extrawest-OCPI-2.2.1-CPO-Server)
3. [eMSP Client](https://github.com/extrawest/Extrawest-OCPI-2.2.1-EMSP-Client)
4. [CPO Client](https://github.com/extrawest/Extrawest-OCPI-2.2.1-CPO-Client)

Maven
=====

Find the maven repo here: https://mvnrepository.com/artifact/com.extrawest/Extrawest-OCPI-2.2.1-EMSP-Server)

Dependencies
============

Java-OCA-OCPP uses the following libraries:

* [springdoc](https://springdoc.org)
* [mapstruct](https://mapstruct.org)
* [lombok](https://projectlombok.org)
* [spock-core-0.7-groovy-2.0](http://spockframework.org)
* [javax.validation](https://mvnrepository.com/artifact/javax)
* [javax.servlet-api](https://mvnrepository.com/artifact/javax)
* [javax.annotation-api](https://mvnrepository.com/artifact/javax)
* [springdoc-openapi-starter-webmvc-ui](https://springdoc.org/)
* [springdoc-openapi-ui](https://springdoc.org/)
* [swagger-annotations](https://swagger.io/)
* [jackson-databind-nullable](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)

## Requirements
- Java 17 or higher
- Maven 3.6 or higher

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn clean install
```

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>com.extrawest</groupId>
    <artifactId>Extrawest-OCPI-2.2.1-EMSP-Server</artifactId>
</dependency>
```

## Getting Started
Extrawest-OCPI-2.2.1-CPO-Server contains CPO Models, Validations, Controllers, Interfaces. To implement CPO Server developers need to write own implementations for CPO Services, e.g. :

```java

@Service
public interface EMSPTariffServiceImpl implements EMSPTariffService {
    TariffDto getTariff(String countryCode, String partyId, String tariffId) {
        //custom implementation here
    }

    TariffDto saveTariff(TariffDto tariffDTO, String countryCode, String partyId, String tariffId) {
        //custom implementation here
    }

    void deleteTariff(String countryCode, String partyId, String tariffId) {
        //custom implementation here
    }
}
```

## Changelog

Current library provides APIs for OCPI 2.2.1. To see changes between OCPI 2.2.1 and previous versions please
check [OCPI changelog](https://github.com/ocpi/ocpi/blob/2.2.1/changelog.asciidoc#changelog_changelog)
for OCPI 2.1.1 to OCPI 2.2 -> OCPI 2.2.1

### Changes between v2023.11.21 and v2023.7.12

Lots of typos fixed and textual improvements. The following changes to messages/objects etc.

| Context (Module / Object) | Description                                                                                                                                                                                                                                               |
|---------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Status Codes              | Added [OCPI Status Codes](https://github.com/ocpi/ocpi/blob/master/status_codes.asciidoc). Added exceptions handlers and exceptions to handle common errors (see [Errors](https://github.com/ocpi/ocpi/blob/master/transport_and_format.asciidoc#errors)) |
| All modules               | Added parameter's validation. Wrapped response into [Response format](https://github.com/ocpi/ocpi/blob/master/transport_and_format.asciidoc#117-response-format)|
| Locations                 | Blocking client access to objects that do not belong to them ( see [Errors](https://github.com/ocpi/ocpi/blob/master/transport_and_format.asciidoc#errors)).|
| Locations                 | Added Location's endpoints permissions per roles: CPO as Sender, eMSP - Receiver, HUB - Sender & Receiver, NSP - Receiver, NAP -  Sender & Receiver                                                                                                       |
| Locations                 | Added ability to add/modify Evse and Connector                                                                                                                                                                                                            |                                                                                                                                                                                                                                                       |
| Sessions                  | Blocking client access to objects that do not belong to them ( see [Errors](https://github.com/ocpi/ocpi/blob/master/transport_and_format.asciidoc#errors))                                                                                               |
| Sessions                  | Added Sessions permissions per roles: CPO as Sender, eMSP - Receiver, HUB - Sender & Receiver, SCSP - Receiver                                                                                                                                            |                                                                                                                                                                                      |                                                                                                                                                                                                                                                  |
| CDRs                      | Added CDR's permissions per roles: CPO as Sender, eMSP - Receiver, HUB - Sender & Receiver                                                                                                                                                                |
| Tariffs                   | Blocking client access to objects that do not belong to them ( see [Errors](https://github.com/ocpi/ocpi/blob/master/transport_and_format.asciidoc#errors))                                                                                               |
| Tariffs                   | Added Tariffs permissions per roles: CPO - Sender, eMSP - Receiver, Hub - Sender and Receiver, NSP - Receiver, SCSP - Sender and Receiver|                            
| Tokens                    | Added [Paginated](https://github.com/ocpi/ocpi/blob/480973547169dee2fe6d12b1a0fe604623efcbb5/transport_and_format.asciidoc#paginated-response) response to getting the list of known Tokens  |
| Tokens                    | Added Tokens module's permissions per roles: CPO as Receiver, eMSP - Sender, HUB - Sender & Receiver                                                                                                                                                      |                                                                                                                                                                                      |                                                                                                                                                                                                                                                  |
| Commands                  | Added Commands module's permissions per roles: CPO as Receiver, eMSP - Sender, HUB - Sender & Receiver                                                                                                                                                    |
| Charging Profiles         | Added Charging Profiles module's permissions per roles: CPO as Receiver, HUB - Sender & Receiver, SCSP - Sender                                                                                                                                           |
| Credentials               | Added permissions for main [OCPI roles](https://github.com/ocpi/ocpi/blob/master/terminology.asciidoc#typical-ocpi-implementations-per-role)                                                                                                              |
|  Versions                 | Changed URLs according [Version information endpoint](https://github.com/ocpi/ocpi/blob/480973547169dee2fe6d12b1a0fe604623efcbb5/version_information_endpoint.asciidoc#11-version-information-endpoint)              |

### Changes in v2023.7.12

Extrawest-OCPI-2.2.1-EMSP-Server library (v2023.7.12) covers next modules.

Functional Modules:

| Module                                                                                     | v2023.7.12 | Description                                                                                                                                                                                                                                                                                                                               |
|--------------------------------------------------------------------------------------------|------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Locations](https://github.com/ocpi/ocpi/blob/2.2.1/mod_locations.asciidoc)                | +          | The Location objects live in the CPO back-end system. They describe the charging locations of an operator                                                                                                                                                                                                                                 |
| [Sessions](https://github.com/ocpi/ocpi/blob/2.2.1/mod_sessions.asciidoc)                  | +          | The Session object describes one charging session. The Session object is owned by the CPO back-end system, and can be GET from the CPO system, or pushed by the CPO to another system                                                                                                                                                     |
| [CDRs](https://github.com/ocpi/ocpi/blob/2.2.1/mod_cdrs.asciidoc)                          | +          | A Charge Detail Record is the description of a concluded charging session. The CDR is the only billing-relevant object. CDRs are sent from the CPO to the eMSP after the charging session has ended                                                                                                                                       |
| [Tariffs](https://github.com/ocpi/ocpi/blob/2.2.1/mod_tariffs.asciidoc)                    | +          | The Tariffs module gives eMSPs information about the tariffs used by the CPO                                                                                                                                                                                                                                                              |
| [Tokens](https://github.com/ocpi/ocpi/blob/2.2.1/mod_tokens.asciidoc)                      | +          | The tokens module gives CPOs knowledge of the token information of an eMSP. eMSPs can push Token information to CPOs, CPOs can build a cache of known Tokens. When a request to authorize comes from a Charge Point, the CPO can check against this cache. With this cached information they know to which eMSP they can later send a CDR |
| [Commands](https://github.com/ocpi/ocpi/blob/2.2.1/mod_commands.asciidoc)                  | +          | The Commands module enables remote commands to be sent to a Location/EVSE. The following commands are supported: CANCEL_RESERVATION, RESERVE_NOW, START_SESSION , STOP_SESSION, UNLOCK_CONNECTOR                                                                                                                                          |
| [Charging Profiles](https://github.com/ocpi/ocpi/blob/2.2.1/mod_charging_profiles.asciidoc) | +          | With the ChargingProfiles module, parties (SCSP but also MSPs) can send (Smart) Charging Profiles to a Location/EVSE. It is also possible to request the 'ActiveChargingProfile' from a Location/EVSE                                                                                                                                     |
| [Hub Client Info](https://github.com/ocpi/ocpi/blob/2.2.1/mod_hub_client_info.asciidoc)    | +          | Provides parties connected to a hub with the connection status of other parties that are connected to a hub that they can communicate with. So, CPOs know which eMSP and other parties are online and vice versa                                                                                                                          |

Configuration Modules:

| Module                                                                                     | v2023.7.12 | Description                                                                                                                          |
|--------------------------------------------------------------------------------------------|-----------|--------------------------------------------------------------------------------------------------------------------------------------|
| [Credentials](https://github.com/ocpi/ocpi/blob/2.2.1/credentials.asciidoc)                | +         | The credentials module is used to exchange the credentials token that has to be used by parties for authorization of requests        |
|  [Versions](https://github.com/ocpi/ocpi/blob/2.2.1/version_information_endpoint.asciidoc) | +         |  Via this module, clients can learn which versions of OCPI a server supports, and which modules it supports for each of the versions  |

Roadmap:

Functionality | 2023                      |                                                                                                                     
------------ |------------------------------|
[Authentification](https://github.com/ocpi/ocpi/blob/2.2.1/credentials.asciidoc)|
[Authorization](https://github.com/ocpi/ocpi/blob/master/transport_and_format.asciidoc#transport_and_format_authorization_header) |

License
=======

[MIT License](LICENSE)

About Extrawest.com
=======

We are devoted to push the marked for vehicles charging forward.
There are many standards out there, we intend to implement and share them. Any help is much appreciated!

The market is in its defining state, the practices and standards we come up with now, may very well stick around for decades to come.

See our vision at https://www.extrawest.com/
