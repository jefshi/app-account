package com.csp.account.database.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 账户流水
 * Created by csp on 2020/05/22
 * Modified by csp on 2020/05/22
 *
 * @version 1.0.0
 */
// 听网上说，这样就算主键是空的，insertOrReplace 也没问题
@Entity
// @Entity(indexes = {@Index(value = "userId DESC, uuid DESC", unique = true)})
public class TAccount {

    @Id
    // @NotNull
    // @Index
    private String uuid; // 主键：uuid

    @Generated(hash = 1541549871)
    public TAccount(String uuid) {
        this.uuid = uuid;
    }

    @Generated(hash = 713748627)
    public TAccount() {
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
