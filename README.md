# JAVA_MINI_PROJECT
자바 미니 프로젝트
## 👨‍🏫 프로젝트 소개
학원에서 자습할때 작성하는 자습대장이 있는데, 이 자습대장이 프로그램에 의해 자동으로 처리되는 형식이 아니라, 학생이 강의듣고 있는 강의실에 맞게 자습대장을 찾고 해당 자습대장에 이름, 시간을 수동으로 기입해야 하는 번거러움이 있었으며, 다음날 자습대장을 기입하기 위해선 누군가가 해당 자습대장을 날마다 새로 초기화 해주는 번거러운 작업이 존재해서 해당 작업을 프로그램이 자동으로 처리해주면 편하다고 생각하여 해당 프로젝트를 진행하게 되었다.

## ⏲️ 개발 기간 
- 2024.06.25(화) ~ 2023.07.12(금)
  
## 🧑‍🤝‍🧑 개발자 소개 
- **최재영** : 팀장, 개발자

## 💻 개발환경
- **Version** : Java 11
- **IDE** : eclipse

## ⚙️ 기술 스택
- **Lang** : JAVA
- **DataBase** : MySQL
- **VM** : Docker

## 📝 프로젝트 클래스 다이어그램
![프로젝트 클래스 다이어그램](https://github.com/Developer-Choi-Jae-Young/JavaProject/blob/d5f5272d968d9643d2215e0424714b53740facb6/1_java/ClassDiagram/ClassDiagram_Control.png)
![프로젝트 클래스 다이어그램](https://github.com/Developer-Choi-Jae-Young/JavaProject/blob/d5f5272d968d9643d2215e0424714b53740facb6/1_java/ClassDiagram/ClassDiagram_DAO.png)
![프로젝트 클래스 다이어그램](https://github.com/Developer-Choi-Jae-Young/JavaProject/blob/d5f5272d968d9643d2215e0424714b53740facb6/1_java/ClassDiagram/ClassDiagram_Database.png)
![프로젝트 클래스 다이어그램](https://github.com/Developer-Choi-Jae-Young/JavaProject/blob/d5f5272d968d9643d2215e0424714b53740facb6/1_java/ClassDiagram/ClassDiagram_ETC.png)
![프로젝트 클래스 다이어그램](https://github.com/Developer-Choi-Jae-Young/JavaProject/blob/d5f5272d968d9643d2215e0424714b53740facb6/1_java/ClassDiagram/ClassDiagram_Model.png)
![프로젝트 클래스 다이어그램](https://github.com/Developer-Choi-Jae-Young/JavaProject/blob/d5f5272d968d9643d2215e0424714b53740facb6/1_java/ClassDiagram/ClassDiagram_View.png)
![프로젝트 클래스 다이어그램](https://github.com/Developer-Choi-Jae-Young/JavaProject/blob/d5f5272d968d9643d2215e0424714b53740facb6/1_java/ClassDiagram/ClassDiagram_Run.png)

## 🧾 프로젝트 ERD
![프로젝트 ERD](https://github.com/Developer-Choi-Jae-Young/JavaProject/blob/d5f5272d968d9643d2215e0424714b53740facb6/1_java/ERD/JavaMiniProject.png)

## 📌 주요 기능
- 로그인
  - 자습대장에 이름을 수기 작성보단 로그인 정보를 기반으로 이름 작성처리가 되면 편할것으로 보아 로그인 기능을 추가하였으며, 로그인시 Password는 암호화, 복호화를 통해 로그인 과정이 진행이됨.
- 회원가입
  - 회원가입시 바로 로그인이 진행되기보단, 회원가입 요청형식으로 해당 요청은 관리자에 의해 진행이 된다. (막무가내 회원가입을 방지하기 위한 설계)
  - 회원가입시 학생, 강사로 나눠져 프로그램에서 해당 사용자가 학생인지 강사인지 구별이 가능하다.
  - 회원 삭제기능은 오로지 관리자에 의해서만 가능하다.
  - 회원요청 수락후 관리자에 의해 각 학생 사용자 별로 강사와 강의실을 할당할수 있다.
- 자습대장 데이터 CRUD
   - 자습대장에 데이터를 삽입시 오늘날짜 기준으로 1번씩만 기록이 가능하며 수정을 원하면 삭제후 재등록 방식으로 설계하였다.
   - 사용자가 학생일 경우 학생 사용자에게 등록된 건물 이면서 같은 층수에 등록된 사용자 기준 자습대장들의 기록만 보여준다.
   - 사용자가 관리자일 경우 보고싶은 날짜와 건물 및 층수를 입력하면 해당 입력에 맞게 자습대장들의 기록만 보여준다.
   - 사용자가 강사일 경우 자습대장 기록은 불가하다.
- 강의실 데이터 CRUD
    - 관리자에 의해 강의실에 대한 추가/삭제/검색 이 가능하다.
