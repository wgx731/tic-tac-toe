package com.github.wgx731.xos.module;

import com.github.wgx731.xos.service.room.Room;
import com.github.wgx731.xos.service.room.TwoPlayerRoom;

import dagger.Binds;
import dagger.Module;

@Module
abstract class TwoPlayerRoomModule {

    @Binds
    abstract Room provideRoom(TwoPlayerRoom room);

}
