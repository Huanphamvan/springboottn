package com.codegym.service;

import com.codegym.model.ClassRoom;

import java.util.List;

public interface IClassRoomService extends IGeneralService<ClassRoom> {
    List<ClassRoom> findByNameContain(String name);
}
