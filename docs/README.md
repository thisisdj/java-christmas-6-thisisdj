## 📝 기능구현 목록

### 1. 기본사항
- [x] 각 메뉴(에피타이저, 메인, 디저트, 음료) 별로 enum 생성
- [x] 각 클래스 내 표시(안내, 오류) 메세지 상수 선언

### 2. 주요 구현사항
- [x] 식당 예상 방문날짜 입력
    - [x] 1 이상 31 이하만 입력허용
    - [x] 숫자 타입체크
- [x] 주문 메뉴 및 개수 입력
  - [x] 메뉴 존재 확인
  - [x] 메뉴 개수 숫자타입 체크
  - [x] 입력 형식 체크
  - [x] 중복 메뉴 입력 체크
- [x] 주문 메뉴 출력
- [x] 총 혜택 금액 출력 (총 혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격)
  - [x] 총 혜택 금액에 따른 배지 표시
- [x] 할인 후 예상 결제 금액 출력 (할인 후 예상 결제 금액 = 할인 전 총주문 금액 - 할인 금액)
- [x] 증정 메뉴 출력
  - [x] 증정 이벤트에 해당하지 않는 경, 증정 메뉴 "없음"으로 출력
- [x] 혜택 내역 출력
  - [x] 고객에게 적용된 혜택만 출력
  - [x] 혜택이 없으면 '없음'으로 출력
- [x] 이벤트 배지 출력
  - [x] 배지가 없다면 '없음'으로 출력
  - [x] 5천원 이상(별), 1만 원 이상(트리), 2만 원 이상(산타)

### 3. 추가된 요구사항
- [x] 입출력 클래스 구현 (InputView, OutputView)