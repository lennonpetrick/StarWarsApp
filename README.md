# Star Wars Movies App

### Summary

This project uses some third libraries and principles like:

- **[Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)**: Split the project in 3 layers (Data, Domain and Presentation). It makes the code completely decoupled, maintainable and testable.

- **MVP**: The Presentation layer uses Model View Presenter pattern to completely separate the Android framework from business logic.

- **Dagger2**: Applying dependency injection concentrates all instances in one place. It decouples the creation of objects and enables easily dependencies replacement.

- **RxJava**: Makes the project more reactive by executing calls in background. It also have operators to transform and filter data.

- **Retrofitb**: Saving developement time by taking over the RESTFul apis calls, Gson and RxJava integrations.

- **Apache StringUtils**: Used to normalize the opening crawl in order to fit in the layout. It is also used for checking whether string is empty, so that it can work without issue in unit testes.

### Testability

Some classes from Data and Domain layer is covered by unit tests. The presentation layer is tested with unit tests and ui tests. The project uses [Mockito](https://site.mockito.org/) to create mock objects and verify values from mocked objects, and [Hamcrest](https://github.com/npryce/hamkrest) for assertions.


