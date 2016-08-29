package com.github.wgx731.xos;

import com.github.wgx731.xos.service.view.ConsoleView;
import com.github.wgx731.xos.service.view.View;
import com.github.wgx731.xos.service.room.Room;
import com.github.wgx731.xos.service.room.TwoPlayerRoom;
import com.github.wgx731.xos.service.engine.Engine;
import com.github.wgx731.xos.service.engine.TwoPlayerTicTacToeEngine;

import dagger.Module;
import dagger.Component;
import dagger.Subcomponent;
import dagger.Provides;
import javax.inject.Singleton;

@Module
class TwoPlayerTicTacToeModule {

      @Provides
      View provideView() {
          return new ConsoleView(
            System.in,
            System.out,
            System.err
          );
      }

      @Provides
      Engine provideEngine(Room room) {
          return new TwoPlayerTicTacToeEngine(room);
      }

}

@Module
class TwoPlayerRoomModule {

    @Provides
    Room provideRoom() {
        return new TwoPlayerRoom();
    }

}

public class Main {

    @Component(modules = {TwoPlayerTicTacToeModule.class, TwoPlayerRoomModule.class})
    interface GameComponent {
        TwoPlayerTicTacToe get();
    }

    @Subcomponent(modules = { TwoPlayerRoomModule.class })
    interface RoomComponent {
        Room get();
    }

    public static void main(String[] args) {
        TwoPlayerTicTacToeModule twoPlayerTicTacToeModule = new TwoPlayerTicTacToeModule();
        TwoPlayerRoomModule twoPlayerRoomModule = new TwoPlayerRoomModule();
        GameComponent gameComponent = DaggerMain_GameComponent.builder()
            .twoPlayerTicTacToeModule(twoPlayerTicTacToeModule)
            .twoPlayerRoomModule(twoPlayerRoomModule)
            .build();
        TwoPlayerTicTacToe game = gameComponent.get();
        game.run();
    }

}
