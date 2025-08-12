# Spring Gift Enhancement

## 프로젝트 개요

이 프로젝트는 **카카오테크캠퍼스에서 학습 목적으로 개발된 Spring Boot 기반의 선물 관리 웹 애플리케이션**입니다.  
상품 관리, 카테고리 분류, 위시리스트 기능 등을 통해 Spring Framework의 핵심 개념들을 학습하고 실습했습니다.

## 기술 스택

- **Backend**: Spring Boot, Spring Data JPA, Spring Security
- **Database**: H2 (개발용)
- **Frontend**: Thymeleaf, HTML, CSS, JavaScript
- **Build Tool**: Gradle
- **Authentication**: JWT Token

## 주요 기능

### 🛍️ 상품 관리
- 상품 등록, 수정, 삭제
- 상품 이미지 URL 관리
- 상품별 옵션 관리 (이름, 수량)
- 카테고리별 상품 분류

### 👥 회원 관리
- 회원 가입 및 로그인
- JWT 기반 인증 시스템
- 관리자/일반 사용자 권한 구분

### 📂 카테고리 관리
- 상품 카테고리 생성 및 관리
- 카테고리별 상품 조회

### ❤️ 위시리스트
- 사용자별 위시리스트 관리
- 원하는 상품을 위시리스트에 추가/삭제

### 📊 관리자 기능
- 전체 상품 관리
- 회원 관리
- 상품 옵션 관리

## 프로젝트 구조

```
src/main/java/gift/
├── Application.java              # 메인 애플리케이션 클래스
├── config/                       # 설정 클래스
│   └── WebConfig.java
├── controller/                   # 컨트롤러 레이어
│   ├── AdminController.java
│   ├── HomeController.java
│   ├── MemberController.java
│   ├── OptionController.java
│   ├── ProductController.java
│   └── WishlistController.java
├── dto/                         # 데이터 전송 객체
│   ├── CategoryDto.java
│   ├── MemberDto.java
│   ├── OptionDto.java
│   ├── ProductDto.java
│   └── Role.java
├── entity/                      # JPA 엔티티
│   ├── Category.java
│   ├── Member.java
│   ├── Option.java
│   ├── Product.java
│   └── Wishlist.java
├── exception/                   # 커스텀 예외 클래스
│   ├── MemberNotFoundException.java
│   ├── ProductException.java
│   ├── ProductNoConferredException.java
│   └── ProductNotFoundException.java
├── filter/                      # 필터
│   └── JwtTokenFilter.java
├── handler/                     # 예외 처리
│   └── GlobalExceptionHandler.java
├── repository/                  # 데이터 접근 레이어
│   ├── CategoryRepository.java
│   ├── MemberRepository.java
│   ├── OptionRepository.java
│   ├── ProductRepository.java
│   └── WishlistRepository.java
├── service/                     # 비즈니스 로직 레이어
│   ├── CategoryService.java
│   ├── MemberService.java
│   ├── OptionService.java
│   ├── ProductService.java
│   └── WishlistService.java
└── util/                        # 유틸리티 클래스
    └── JwtTokenProvider.java
```

## 학습 단계별 개발 과정

### 0단계 - 기본 구조 설정
- DOM 처리 메서드 분리 및 모듈화
- PageRequest를 활용한 페이징 처리 구현
- HomeController에서 AdminController 분리
- 예외 처리 로직 추가 (NotFoundexception)
- 메서드명 개선 (findByEmail → getMember)
- 사용자용 상품 목록 페이지 추가 (위시리스트 기능 포함)

### 1단계 - 카테고리 기능 구현
- Category 엔티티, DTO, 컨트롤러, 서비스, 레포지토리 구현
- Product와 Category 간 연관관계 매핑 (Many-to-One)
- 클라이언트사이드 카테고리 선택 및 조회 기능
- DTO 패턴을 활용한 데이터 전송 최적화

### 2단계 - 옵션 기능 구현
- Option 엔티티, DTO, 컨트롤러, 서비스, 레포지토리 구현
- Product와 Option 간 연관관계 매핑 (One-to-Many)
- 상품별 옵션 관리 기능
- 클라이언트사이드 옵션 선택 및 조회 기능

### 3단계 - 수량 관리 및 테스트
- 상품 옵션의 수량 차감 기능 구현
- 비즈니스 로직 검증을 위한 테스트 코드 작성
- 재고 관리 로직 구현

## 실행 방법

### 사전 요구사항
- Java 17 이상
- Gradle 7.0 이상

### 로컬 실행
```bash
# 프로젝트 클론
git clone https://github.com/eunsoni/spring-gift-enhancement.git
cd spring-gift-enhancement

# 애플리케이션 실행
./gradlew bootRun
```

### 빌드
```bash
# 프로젝트 빌드
./gradlew build

# JAR 파일 실행
java -jar build/libs/spring-gift-enhancement-0.0.1-SNAPSHOT.jar
```

애플리케이션 실행 후 `http://localhost:8080`에서 확인할 수 있습니다.

## 주요 학습 포인트

### 🏗️ 아키텍처 패턴
- **Layered Architecture**: Controller, Service, Repository 계층 분리
- **DTO Pattern**: 데이터 전송 최적화
- **Repository Pattern**: 데이터 접근 추상화

### 🔗 JPA 활용
- **Entity 관계 매핑**: @OneToMany, @ManyToOne
- **Cascade 설정**: 연관 엔티티 생명주기 관리
- **Lazy Loading**: 성능 최적화

### 🛡️ 보안
- **JWT 인증**: 토큰 기반 인증 시스템
- **필터 체인**: Spring Security 필터 활용
- **권한 관리**: 사용자 역할별 접근 제어

### 🎯 예외 처리
- **커스텀 예외**: 도메인별 예외 클래스 정의
- **Global Exception Handler**: 통합 예외 처리
- **Validation**: Bean Validation 활용

### 🧪 테스트
- **Unit Test**: 개별 컴포넌트 테스트
- **Integration Test**: 통합 테스트
- **Repository Test**: 데이터 계층 테스트

## 주의사항

⚠️ **이 프로젝트는 카카오테크캠퍼스의 학습용 프로젝트입니다.**
- 실제 운영 환경에서 사용하기에는 보안 및 성능 최적화가 부족합니다.
- 학습 목적으로만 사용하시기 바랍니다.
- H2 인메모리 데이터베이스를 사용하므로 애플리케이션 재시작 시 데이터가 초기화됩니다.

## 라이선스

이 프로젝트는 학습 목적으로 개발되었으며, 개인적인 학습 용도로만 사용하시기 바랍니다.
