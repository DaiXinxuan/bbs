package com.cn.bbs.servie;

import com.cn.bbs.database.model.InformEntity;

import java.util.List;

/**
 * Created by dxx on 2017/2/22.
 */
public interface InformService {
    int generateNewInform(InformEntity informEntity);

    int updateInformReadStatus(int informId);

    List<InformEntity> getInformsByUserId(String userId);

    int deleteInforms(String userId);
}
