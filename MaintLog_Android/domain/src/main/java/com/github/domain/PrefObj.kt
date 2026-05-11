package com.github.domain

import com.github.util.pref.pref

object PrefObj {
    /**
     * 로그인 정보 저장
     */
    var LOGIN_ID: String by pref("")
    var LOGIN_PW: String by pref("")
    /**
     * 로그인 시 유저 데이터 저장
     * 앱 다시 실행 시 초기화 해야 함
     */
//    var USER_INFO: UserData? by pref(null)

    var PERMISSION_OK: Boolean by pref(false)
}