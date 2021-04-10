# 앱 배포 시,
# 코드 축소, 난독화, 최적화를 하는 경우,
# 카카오 SDK를 제외하고 진행하기 위한 코드 추가
-keep class com.kakao.sdk.**.model.* { <fields>; }
-keep class * extends com.google.gson.TypeAdapter