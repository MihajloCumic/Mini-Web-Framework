# Mini Web Framework

## Overview
This project implements a mini web framework inspired by JAX-RS and Spring. The framework is designed around controllers that handle incoming requests and generate JSON responses. It supports dependency injection at the controller level.

## Features

### Route Registration
- **Annotations**:
  - `@Controller`: Marks a class as a controller that contains request handling methods.
  - `@Path`: Specifies the route for a controller method.
  - `@GET` and `@POST`: Indicate which HTTP methods are allowed for the corresponding route.

- **Route Mapping**:
  - Upon startup, the framework scans for classes annotated with `@Controller`, and maps methods based on their `@GET`/`@POST` annotations and `@Path` definitions.
  - Each route is uniquely mapped, preventing multiple methods from handling the same path and HTTP method.

### Dependency Injection
- **Inversion of Control**: The framework automatically initializes controller attributes using dependency injection.
- **Annotations for DI**:
  - `@Autowired`: Marks attributes for injection during controller initialization. Supports a `verbose` parameter to log initialization details.
  - `@Bean`: Marks a class for dependency management, allowing specification of `scope` (singleton or prototype).
  - `@Service` and `@Component`: Shortcuts for `@Bean` with default scopes.
  - `@Qualifier`: Used to specify which implementation to inject for an interface.

### Dependency Container
- Registers implementations for interfaces, throwing exceptions when a requested type is not found.
- Supports recursive dependency resolution, ensuring all dependencies are injected properly.

### DI Engine
- Utilizes reflection to initialize all annotated dependencies recursively starting from controllers.
- Handles initialization logging if the `verbose` parameter is set to true.
- Manages singleton and prototype instances for beans and services.
- Resolves dependencies from the Dependency Container when interfaces are encountered.

## Technical Requirements
- Use reflection or AOP; external libraries for dependency injection are not permitted.
- All classes must have a no-argument constructor.
- Attributes should not be static or final, and no class should inherit from another.
- Circular dependencies are not allowed.
