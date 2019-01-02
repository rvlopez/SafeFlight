# SafeFlight

A very simple application made in Kotlin to improve my skills and learn more about this fantastic language. The application uses the Lufthansa Group public API and consists in a form to select origin and destination airports, with some suggestions while is writing, to load those airports in a map and the flights related with them.

The following is a brief description of the technical decisions taken for the development.

## App Screenshots

<img src="https://i.imgur.com/NvmTn62.png" width="250">  <img src="https://i.imgur.com/R9Nh51a.png" width="250">  <img src="https://i.imgur.com/VEfje6g.png" width="250">  

<img src="https://i.imgur.com/lGLbIve.png" width="250">  <img src="https://i.imgur.com/wyiom90.png" width="250">  <img src="https://i.imgur.com/51xqhmf.png" width="250"> 

## API Service Highlights

Used the Lufthansa Open REST API (documentation guides)

###### HOST 
    https://api.lufthansa.com/v1/

### Endpoints used
#### OAuth 2.0
Access to Lufthansa services is controlled via tokens (Oauth 2.0).

###### PATH
    POST oauth/token
    
  | POST Parameter  | Value |
  | ------------- | ------------- |
  | client_id  | Your client application key  |
  | client_secret  | Your client secret  |
  | grant_type | "client_credentials" |

    
#### Get Airports
List all airports or one specific airport. All airports response is very large. It is possible to request the response in a specific language. 

###### PATH
    GET references/airports/{airportCode}
    
    
#### Flight Schedules
Scheduled flights between given airports on a given date.

###### PATH
     GET operations/schedules/{origin}/{destination}/{fromDateTime}


## Architecture

* Application written in Kotlin based on Clean Architecture for layer abstractions (view/domain/data)
* Model-View-Presenter for the presentation layer
* Interactors / UseCases are used by the presenters to start a data retrieval flow
* UseCases use Repository pattern to request the data needed
* Repository in this case doesn't have DataSources because there's no database/cache/other sources to retrieve data than just * Network. Repository in this project directly request data to the API
* Two different data objects, DTO (Data transfer object) to parse API's response, and Business Object as our own data model.
* DI with Dagger2
* Asynchronous handled by RxJava2 (RxKotlin)
* Animations provided by Lottie 


## Thirtd-Party

* RxKotlin https://github.com/ReactiveX/RxKotlin
* Dagger 2 https://google.github.io/dagger/
* Retrofit 2 https://square.github.io/retrofit/
* OkHttp 3 https://github.com/square/okhttp
* Lottie https://airbnb.design/lottie/

## Work in progress

* Instrumentation Testing
* Cache / database

## Contact

:email: viciana.ruben@gmail.com 
