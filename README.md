# Kurly
Mock Server를 이용한 Test App 제작

XML 코드 기반으로 UI를 구현하였습니다.

클린아키텍처 기반의 멀티 모듈 구조의 앱입니다.

# 아키텍처 및 주요 의존 관계
<img width="1761" alt="image" src="https://github.com/Songgyubin/Kurly/assets/37494776/7e80e4b2-8119-4227-a64d-bd938cb8f11c">

관심사 분리 및 체계적인 계층 구조를 위해 **CleanArchitecture를** 도입하였습니다.

Domain Layer와 Data Layer 간 **의존성 역전을 위한 Repository 패턴을** 적용함으로써,


기술 인프라 변경, 확장에 대해 비즈니스 로직을 담당하는 **Domain Layer에 가는 영향을 최소화** 시키려 했습니다.



# 모듈화 구조
<img width="1197" alt="image" src="https://github.com/Songgyubin/Kurly/assets/37494776/b90e2c2d-01d7-4233-84ab-2c167d56faea">


### 기본 구조
**CleanArchitecture** 기반의 멀티 모듈 구조로 기본적으로 **app, data, domain** 모듈이 나뉘어져 있습니다.

### Core 모듈
`data`, `domain`, `network`, `common` 모듈을 포함하는 모듈입니다.

처음 생각했던 모듈 구조는 `app` 모듈에서 `core` 모듈을 직접 의존하는 것이 아닌,

`feature` 모듈을 두어 `app` -> `feature:섹션, feature:찜 ...` -> `core` 식의 구조를 가져가려 했지만,

시간 상 `app` -> `core` 모듈 의존 구조로 구현했습니다.

### Common 모듈
**유틸 함수 및 헬퍼를** 관리할 수 있는 모듈입니다.

### Network 모듈
**네트워크 통신 및 응답 모델을** 담당하는 모듈입니다.

현재는 Retrofit만을 사용하기에 '굳이'라는 생각이 들 수도 있지만,

확장성을 고려하여 프로젝트를 진행한다면 Network 모듈을 두어 네트워크 통신에 관한 설정을 이 모듈에서 관리하려고 했습니다.


# 사용 스킬

### 언어 및 아키텍처

- Kotlin
- Clean Architecture
- MVVM

### UI 및 상태 관리

- ViewModel
- DataBinding

### 네트워크 통신

- Retrofit
- OkHttp

### 비동기 처리 및 반응형 프로그래밍

- Coroutine
- Flow

### 의존성 주입

- Hilt

### 이미지 로드

- Glide
