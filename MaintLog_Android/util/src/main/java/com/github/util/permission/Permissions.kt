package com.github.util.permission


/**
 * Permission 리스트
 * 추가, 삭제, 변경 후 register 실행할 것.
 */
val permissionsList = mutableListOf<String>()

/**
 * 안드로이드 11 이상 기기에서
 * Location의 경우 백그라운드는 사용자가 직접 설정으로 이동해 설정해야함.
 * Created by 이기종 on 2022-03-10
 */
val permissionsLocationList = mutableListOf<String>()

fun getPermissionsArray(): Array<String> = permissionsList.toTypedArray()

fun getPermissionsLocationArray(): Array<String> = permissionsLocationList.toTypedArray()

enum class Permissions {
    PERMISSIONS, PERMISSIONSLOCATION, PERMSSIONSBLUETOOTH
}
