package com.example.res_project;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.kakao.sdk.user.UserApiClient;
import java.util.Map;

public class KakaoLoginAPI {

    private String KakaoUserName;
    private String kakaoUserId;
    UserApiClient uac = UserApiClient.getInstance();

    public String getKakaoUserName() {
        return KakaoUserName;
    }

    public void setKakaoUserName(String kakaoUserName) {
        KakaoUserName = kakaoUserName;
    }

    public String getKakaoUserId() {
        return kakaoUserId;
    }

    public void setKakaoUserId(String kakaoUserId) {
        this.kakaoUserId = kakaoUserId;
    }

    public KakaoLoginAPI() {
    }

    // 카카오 계정으로 로그인
    public void kakaoLogin(Context context) {

        uac.loginWithKakaoAccount(context, (token, loginError) -> {
            if (loginError != null) {
                Log.i("login", "error");
            } else {
                Log.i("login", "success");
                uac.me((user, meError) -> {
                    if (meError != null) {
                        Log.i("me", "error");
                    } else {
                        Log.i("me", "success");
                    }
                    return null;
                });
            }
            return null;
        });

    }


    // 토큰 정보 보기
    public void tokenCheck() {

        uac.accessTokenInfo((accessTokenInfo, error) -> {
            if (error != null) {
                Log.e("token", "정보얻기 실패");
            } else if (accessTokenInfo != null) {
                Log.i("token", "정보 얻기 성공"
                        + "\n 회원번호 : " + accessTokenInfo.getId()
                        + "\n 만료시간 : " + accessTokenInfo.getExpiresIn());
            }
            return null;
        });

    }


    // 사용자 정보 가져오기
    public void userInfoRequest() {

        uac.me((user, error) -> {
            if (error != null) {
                Log.e("user", "정보 요청 실패");
            } else if (user != null) {
                Log.i("user", "정보 요청 성공"
                        + "\n 프로필사진 : " + user.getKakaoAccount().getProfile().getThumbnailImageUrl());
                setKakaoUserName(user.getKakaoAccount().getProfile().getNickname());
                setKakaoUserId(user.getId() + "");
                Log.i("get", getKakaoUserName());
                Log.i("get", getKakaoUserId());
            }
            return null;
        });
    }


    // 사용자 정보 저장하기
    @RequiresApi(api = Build.VERSION_CODES.R)
    public void userInfoSave() {

        //사용 가능 properties : id, status, registered_at, msg_blocked, nickname,
        //                      thumbnail_image, profile_image
        Map properties = Map.of("nickname", "hyojin");

        uac.updateProfile(properties, error -> {
            if (error != null) {
                Log.i("save", "저장 실패", error);
            } else {
                Log.i("save", "저장 성공");
            }
            return null;
        });

    }


}
