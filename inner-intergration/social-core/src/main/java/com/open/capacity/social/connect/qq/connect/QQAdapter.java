package com.open.capacity.social.connect.qq.connect;

import com.open.capacity.social.connect.qq.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class QQAdapter implements ApiAdapter<QQ> {

    @Override
    public boolean test(QQ api) {
        return false;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {

    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {

    }
}
