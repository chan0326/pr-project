package com.erichgamma.api.common.command;

import com.erichgamma.api.common.component.MessengerVo;

public interface CommandService<T>{
      MessengerVo save(T t);
      MessengerVo deleteById(Long id);
      MessengerVo modify(T t);
}
