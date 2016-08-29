# Tic Tac Toe

[![Travis](https://img.shields.io/travis/wgx731/tic-tac-toe.svg?style=flat)](https://travis-ci.org/wgx731/tic-tac-toe) [![GitHub issues](https://img.shields.io/github/issues/wgx731/tic-tac-toe.svg?style=flat)](https://github.com/wgx731/tic-tac-toe/issues) [![GitHub forks](https://img.shields.io/github/forks/wgx731/tic-tac-toe.svg?style=flat)](https://github.com/wgx731/tic-tac-toe/network) [![GitHub stars](https://img.shields.io/github/stars/wgx731/tic-tac-toe.svg?style=flat)](https://github.com/wgx731/tic-tac-toe/stargazers)

## Project Goal

* Practice software design (MVC)
* Practice dependency injection using [dagger2](http://google.github.io/dagger)
* Practice testing (e.g. unit testing, code coverage)

## Project Structure
```
.
├── build
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── README.md
├── settings.gradle
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── github
    │               └── wgx731
    │                   └── xos
    │                       ├── exception
    │                       │   ├── EngineException.java
    │                       │   └── RoomException.java
    │                       ├── Main.java
    │                       ├── service
    │                       │   ├── engine
    │                       │   │   ├── Engine.java
    │                       │   │   ├── State.java
    │                       │   │   └── TwoPlayerTicTacToeEngine.java
    │                       │   ├── room
    │                       │   │   ├── Room.java
    │                       │   │   └── TwoPlayerRoom.java
    │                       │   └── view
    │                       │       ├── ConsoleView.java
    │                       │       └── View.java
    │                       └── TwoPlayerTicTacToe.java
    └── test
        └── java
            └── com
                └── github
                    └── wgx731
                        └── xos
                            └── service
                                ├── engine
                                │   └── TwoPlayerTicTacToeEngineTest.java
                                ├── room
                                │   └── TwoPlayerRoomTest.java
                                └── view
                                    └── ConsoleViewTest.java

84 directories, 81 files
```

## TODO

* :boom: better `build.gradle`
* :bowling: refactor and better design
* :secret: Game Server using [grpc](http://www.grpc.io)
* :book: better docs

/play letitgo
