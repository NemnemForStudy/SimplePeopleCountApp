# 🚀 SimplePeopleCountApp
간단한 한 줄 소개: 이 어플은 간단하게 어머니가 원해서 만든 사람 카운팅 어플입니다.

![version](https://img.shields.io/badge/version-1.0.0-blue)
![license](https://img.shields.io/badge/license-MIT-green)
![build](https://img.shields.io/badge/build-passing-brightgreen)

## 💡 제작 동기
* 어머니가 사람 세는 어플을 playstore에서 다운로드 했는데 원하는 방향이 아니어서 코틀린 공부하는 김에 만들어 드렸음.

## 🎯 프로젝트 목표
이 프로젝트는 다음과 같은 핵심 기능과 디자인 목표를 가지고 개발되었습니다.

1.  **시각적 특징:**
    * 바탕화면은 **초록색**이어야 합니다.
    * 카운트 폰트는 **흰색**이며, **큰 사이즈**로 표시되어야 합니다.
2.  **핵심 기능 (카운팅):**
    * 초록색 화면 내에서 어디서든 카운팅이 가능해야 합니다.
    * 데이터는 **최근 카운팅 기록 하나만 남기고 리셋**되는 기능을 제공합니다.
3.  **데이터 기록:**
    * **"며칠에 몇 명 셌는지"**에 대한 기록이 저장되어야 합니다.

##🛠️ 사용 기술 (Tech Stack)

*   **언어**: `Kotlin`
*   **아키텍처**: `MVVM (Model-View-ViewModel)`
*   **UI**: `Jetpack Compose`
*   **주요 라이브러리**:
    *   `Jetpack`: ViewModel, LiveData, Navigation, Room
    *   `비동기 처리`: Coroutines
    *   `네트워크 통신`: Retrofit2, OkHttp3
    *   `서버`: Firebase
*   **버전 관리**: `Git`, `GitHub`


## 📱 Screenshots
### 🟢 메인 카운팅 화면 (초기 상태)
<img width="250" alt="초기 상태" src="https://github.com/user-attachments/assets/de118cda-54ec-4a61-b7e5-a37104ab17cb" />

### ✨ 카운트 저장 시
<img width="250" alt="카운트 저장" src="https://github.com/user-attachments/assets/3f61ee6e-99db-49ec-8ea2-4d50403965d6" />

### 🔄 카운트 리셋
<img width="250" alt="카운트 리셋" src="https://github.com/user-attachments/assets/ae5232c2-ff59-46d0-930d-448dd6cb5cff" />

### 💾 카운트 여러 개 저장 및 기록 확인
<img width="250" alt="카운트 저장" src="https://github.com/user-attachments/assets/dbc7f7eb-e420-49ce-a456-1d53337219c3" />

### ⚠️ 데이터 리셋 클릭 (팝업 메시지)
<img width="250" alt="데이터 리셋 클릭" src="https://github.com/user-attachments/assets/78119836-e40c-4140-af4f-f9227d355d0c" />

### 🗑️ 확인 클릭 후 데이터 (최종 상태)
<img width="250" alt="최종 상태" src="https://github.com/user-attachments/assets/65ccf451-87e9-4146-838f-410473a044e6" />
