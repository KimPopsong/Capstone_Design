# 프로젝트 포트폴리오: 머지?(Merge?)

이 문서는 캡스톤 디자인 프로젝트의 아키텍처, 기술 스택 및 주요 기능을 설명하는 자세한 개요 및 분석을 제공합니다.
[Merge Presentation Link](https://www.ideaboom.net/project/project/view?seq=922&comp_seq=59&search_keyword=%EB%A8%B8%EC%A7%80&data_seq[]=1&data_seq[]=2&data_seq[]=3&data_seq[]=4&data_seq[]=5&data_seq[]=6&data_seq[]=7&data_seq[]=8&data_seq[]=9&data_seq[]=10&data_seq[]=11&data_seq[]=12&data_seq[]=13&order=reg)

## 1. 프로젝트 개요

이 프로젝트는 Android 모바일 클라이언트, Java 기반 백엔드 서버 및 온디바이스 머신러닝을 위한 통합 TensorFlow Lite 모델로 구성된 풀스택 애플리케이션으로 보입니다. 이 프로젝트는 식품 관련 TensorFlow Lite 모델의 존재를 고려할 때, 식품 인식 또는 식단 관리와 관련된 포괄적인 솔루션을 제공하는 것을 목표로 합니다.

## 2. 아키텍처

이 프로젝트는 머신러닝 구성 요소가 통합된 클라이언트-서버 아키텍처를 따릅니다:

*   **모바일 클라이언트 (Android-merge):** Kotlin 및 Java를 사용하여 개발된 사용자 대면 애플리케이션입니다. RESTful API를 통해 백엔드 서버와 상호 작용하며 TensorFlow Lite를 사용하여 온디바이스 머신러닝 기능을 통합합니다. UI 개발에는 MVVM (Model-View-ViewModel) 아키텍처 패턴이 사용됩니다.
*   **백엔드 서버 (Server-merge):** Spring Boot로 구축되었을 가능성이 높은 Java 기반 서버 애플리케이션으로, 비즈니스 로직, 데이터 저장, 사용자 인증을 처리하고 모바일 클라이언트에 데이터를 제공합니다. 관계형 데이터베이스와 상호 작용합니다.
*   **머신러닝 (Tensorflow-merge & Android Assets):** TensorFlow Lite 모델은 효율적인 온디바이스 추론을 위해 Android 애플리케이션에 직접 통합됩니다. TensorFlow 관련 파일을 관리하거나 처리하기 위한 Python 스크립트도 있습니다.

```
+-------------------+       +-------------------+       +-------------------+
|                   |       |                   |       |                   |
|  Android 클라이언트 |<----->|   백엔드 서버     |<----->|     데이터베이스    |
|  (android-merge)  |       |   (server-merge)  |       |                   |
|                   |       |                   |       |                   |
+---------+---------+       +-------------------+       +-------------------+
          |
          | (온디바이스 ML 추론)
          |
+---------v---------+
|                   |
| TensorFlow Lite   |
|    모델           |
| (dairy, drink,    |
|  icecream, ramen, |
|  snack.tflite)    |
+-------------------+
```

## 3. 기술 스택

### 3.1. 프론트엔드 (Android-merge)

*   **언어:** Kotlin, Java
*   **빌드 시스템:** Gradle
*   **UI 프레임워크:** Android SDK, Google Material Design
*   **아키텍처 패턴:** MVVM (Model-View-ViewModel)
*   **네트워킹:**
    *   **Retrofit:** Android 및 Java용 타입-세이프 HTTP 클라이언트. 백엔드 서버에 API 호출을 하는 데 사용됩니다.
    *   **PersistentCookieJar:** 세션 간 쿠키를 관리하고 유지하는 데 사용됩니다.
*   **머신러닝:**
    *   **TensorFlow Lite:** 온디바이스 머신러닝 추론을 위한 TensorFlow의 경량 버전. 식품 분류/감지에 사용됩니다.
*   **이미지 로딩:**
    *   **Glide:** Android용 빠르고 효율적인 오픈 소스 미디어 관리 및 이미지 로딩 프레임워크.
*   **번역:**
    *   **Google Cloud Translate API:** 번역 기능 통합에 사용됩니다.
    *   **Apache Commons Text:** 번역된 텍스트의 HTML 이스케이프/언이스케이프 처리에 사용됩니다.
*   **지역화:**
    *   **Akexorcist Localization Library:** 애플리케이션 내 다국어 지원 관리에 사용됩니다.
*   **핵심 Android 라이브러리:**
    *   `androidx.appcompat`, `com.google.android.material`, `androidx.constraintlayout`, `androidx.legacy`
    *   `androidx.lifecycle:lifecycle-viewmodel`, `androidx.lifecycle:lifecycle-livedata` (MVVM용)
    *   `androidx.core:core-ktx`, `androidx.fragment:fragment-ktx` (Kotlin 확장)

### 3.2. 백엔드 (Server-merge)

*   **언어:** Java
*   **빌드 시스템:** Gradle
*   **프레임워크:** Spring Boot ( `application.yml` 및 일반적인 Java 서버 구조에서 유추)
*   **데이터베이스:** SQL (데이터베이스 스키마 정의를 위한 `ddl.sql`에 표시됨)

### 3.3. 머신러닝 (Tensorflow-merge)

*   **언어:** Python (`change_filename.py`에서)
*   **프레임워크:** TensorFlow (`.tflite` 모델 및 프로젝트 이름에서 유추)
*   **스크립트:** `change_filename.py`는 데이터 또는 모델 준비를 위한 유틸리티 스크립트를 제안합니다.

## 4. 주요 기능 및 기술 활용

### 4.1. Android 애플리케이션 기능

*   **사용자 인증:** `LoginActivity`, `SignUpActivity`는 사용자 로그인 및 등록을 처리합니다. `AutoLogin.java`는 자동 로그인 메커니즘을 제안합니다.
*   **메인 인터페이스:** `MainActivity`는 로그인 후 기본 진입점 역할을 하며, 제품 목록 또는 대시보드를 표시할 가능성이 높습니다.
*   **제품 관리:** `ProductActivity`는 상세 제품 정보를 표시합니다. `ProductInfoDTO`, `ProductReviewDTO`는 데이터 전송에 사용됩니다. `ProductInfoAdapter` 및 `ProductReviewAdapter`는 UI 표시를 관리합니다.
*   **리뷰 작성:** `WriteReviewActivity`는 사용자가 리뷰를 작성하고 제출할 수 있도록 합니다. `WriteReviewDTO`는 리뷰 데이터를 처리합니다.
*   **카메라 통합:** `CameraActivity`는 카메라 기능을 통합하며, ML 추론을 위한 이미지 캡처에 사용될 가능성이 높습니다.
*   **검색 기능:** `SearchActivity`는 제품 또는 기타 콘텐츠에 대한 검색 기능을 제공합니다.
*   **온디바이스 ML 추론:** `.tflite` 모델 (dairy, drink, icecream, ramen, snack)은 `CameraActivity` 또는 관련 구성 요소 내에서 로드되어 사용자가 캡처한 이미지에서 식품 항목을 분류하거나 감지하는 데 사용됩니다. 이를 통해 실시간 오프라인 분석이 가능합니다.
*   **국제화/지역화:** `localization` 라이브러리 및 `values-xx` 폴더 (예: `values-en`, `values-ko-rKR`)는 다국어 지원을 나타냅니다. Google Translate API는 콘텐츠 번역을 용이하게 하는 데 사용됩니다.
*   **네트워크 통신:** Retrofit은 다양한 활동 및 뷰 모델 (`LoginViewModel`, `MainViewModel`, `ProductViewModel`, `WriteReviewViewModel`)에서 백엔드 서버와 통신하여 데이터를 가져오고, 양식을 제출하고, 사용자 세션을 관리하는 데 광범위하게 사용됩니다.

### 4.2. 백엔드 서버 기능

*   **API 엔드포인트:** 서버는 Android 애플리케이션에서 사용하는 RESTful API를 제공합니다. `LoginRequestDTO`, `MemberDTO`, `ProductInfoDTO`, `ProductReviewDTO`, `SignUpDTO`, `WriteReviewDTO`, `PatchReviewDTO`와 같은 DTO는 이러한 API의 데이터 구조를 정의합니다.
*   **데이터베이스 관리:** `ddl.sql`은 데이터베이스 스키마를 정의하며, 서버가 사용자 데이터, 제품 정보, 리뷰 및 기타 관련 엔티티를 관리함을 시사합니다.
*   **비즈니스 로직:** `src/main/java/server/study/` 내의 Java 코드는 사용자 인증, 데이터 영속성 및 데이터 검색을 포함한 핵심 비즈니스 로직을 구현합니다.

### 4.3. TensorFlow 구성 요소 기능

*   **모델 준비/관리:** `tensorflow-merge/scripts/change_filename.py` 스크립트는 TensorFlow 모델을 배포 또는 버전 관리를 위해 준비하거나 관리하는 프로세스가 있음을 시사합니다.

## 5. 파일 구조 설명

*   **`android-merge/`**: Android 애플리케이션 소스 코드를 포함합니다.
    *   `app/`: 주요 Android 애플리케이션 모듈.
        *   `src/main/java/com/example/merge/`: Android 앱의 모든 Java/Kotlin 소스 코드를 포함하며, 기능별로 구성됩니다 (예: `login`, `main`, `product`, `camera`, `writereview`, `signup`, `search`).
        *   `src/main/assets/`: 애플리케이션과 함께 번들되는 TensorFlow Lite 모델 (`.tflite` 파일)을 저장합니다.
        *   `src/main/res/`: 레이아웃 (`layout/`), 드로어블 (`drawable/`), 지역화된 문자열 (`values-xx/`)과 같은 Android 리소스를 포함합니다.
        *   `AndroidManifest.xml`: 애플리케이션의 구성 요소, 권한 및 기능을 정의합니다.
        *   `build.gradle`: Android 애플리케이션의 빌드 프로세스, 종속성 및 플레이버를 구성합니다.
*   **`.idea/`**: IntelliJ IDEA/Android Studio 프로젝트 파일.
*   **`gradle/`**: Gradle 래퍼 파일.
*   **`server-merge/`**: 백엔드 서버 소스 코드를 포함합니다.
    *   `src/main/java/server/study/`: 서버용 Java 소스 코드.
    *   `src/main/resources/application.yml`: 서버 구성 파일 (예: 데이터베이스 연결, 서버 포트).
    *   `sql/ddl.sql`: DDL (데이터베이스 스키마)용 SQL 스크립트.
    *   `build.gradle`: 서버 애플리케이션의 빌드 프로세스 및 종속성을 구성합니다.
*   **`tensorflow-merge/`**: TensorFlow 관련 스크립트 또는 리소스를 포함합니다.
    *   `scripts/change_filename.py`: TensorFlow 모델 또는 데이터와 관련된 파일 이름을 조작하는 Python 스크립트일 가능성이 높습니다.
*   **`Presentation/` & `Presentation Document/`**: 다양한 형식 (PPTX, PDF, MP4, JPG, DOCX, HWP)의 프로젝트 발표 자료를 포함합니다.

이 포트폴리오는 프로젝트에 대한 높은 수준의 개요를 제공합니다. 더 깊은 이해를 위해서는 개별 코드 파일 및 문서를 검토해야 합니다.