package com.example.service;

import com.example.beans.ItripUser;

/**
 * description:
 * Created by Ray on 2019-09-02
 */
public interface ItripUserService {
    ItripUser getItripUserById(Long id)throws Exception;
}
