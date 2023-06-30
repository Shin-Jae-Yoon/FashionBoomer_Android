# FashionBoomer_android

<br>

## Fashion Boomer 클라이언트 제작

<br>

무신사 같은 쇼핑몰에서 마음에 드는 옷이 있을 때, 직접적으로 눈으로 확인하여 <br>
마네킹에 대보면서 확인하면 직관적으로 효과가 좋을 것이라고 생각함 <br>
따라서, 크롤링한 쇼핑몰의 이미지에서 배경을 제거하여 마네킹에 대보면서 <br>
옷을 매칭하면서 눈으로 확인할 수 있음. <br>

<hr>

추가로 자신이 가진 실제 옷을 사진 찍어서 올리면 배경을 제거해주므로 <br>
자신이 가진 옷과 쇼핑몰의 옷을 마네킹에 매칭해볼 수 있음 <br>

기능을 확인해보고 싶으면 핵심인 [옷장 Activity](#옷장-activity)로 이동

- 스플래시 Activity
- 로그인 Activity
  - KakaoLogin Activity
- 메인 Activity
  - 카테고리 Fragment
  - 옷장 Fragment
  - 홈 Fragment
  - 게시판 Fragment
  - 마이페이지 Fragment
- 옷 Activity
- Info Activity
- 옷장 Activity
- 게시판 Activity

<hr>

### 스플래시 Activity

<p align="center"><img src="https://user-images.githubusercontent.com/87688023/202463594-38a28015-b26f-42fc-9f89-40d4f420c46a.gif"></img></p>

### 로그인 Activity

- 카카오 회원가입

<p align="center"><img src="https://user-images.githubusercontent.com/87688023/202487901-2e698daa-5016-412c-a442-8311b978df6c.gif"></img></p>

- 카카오 로그인

<p align="center"><img src="https://user-images.githubusercontent.com/87688023/202487462-1903fec4-725a-4121-9ab1-f47a02216566.gif"></img></p>

- 로그아웃

<p align="center"><img src="https://user-images.githubusercontent.com/87688023/202488039-fde7703e-633a-492a-8357-47bffa50500c.gif"></img></p>

### 메인 Activity

- 홈 Fragment
  - 2초에 한 번씩 자동으로 무한 슬라이딩
  - viewPager2를 이용한 구현

<p align="center"><img src="https://user-images.githubusercontent.com/87688023/202488301-7916acc2-8cae-4512-89f0-1c76f574163f.gif"></img></p>

- 카테고리 Fragment

<p align="center"><img src="https://user-images.githubusercontent.com/87688023/204847062-d52c586f-9e43-40e6-84ed-4aa5dd0d86c8.gif"></img></p>

### 옷 Activity (카테고리 Fragment에서 넘어온 액티비티)

<p align="center"><img src="https://user-images.githubusercontent.com/87688023/204847581-b78f3c04-51cf-42b1-b3b8-07ad47fa7a34.gif"></img></p>

### Info Activity (옷 상세 정보, 좋아요 기능)

<p align="center"><img src="https://user-images.githubusercontent.com/87688023/204847884-d43ec1c2-66b9-455d-a1c2-955ebb2d5187.gif"></img></p>

### 옷장 Activity

- 쇼핑몰 이미지 누끼 따기
<p align="center"><img src="https://user-images.githubusercontent.com/87688023/204849690-b8927413-a81b-4183-a3d3-cedfad468680.gif"></img></p>

- 실제 내 옷 이미지 누끼 따기
<p align="center"><img src="https://user-images.githubusercontent.com/87688023/204851955-102e8e9c-1570-4127-bebe-8809a3814f20.gif"></img></p>

- 배경 사진을 변경하여 옷 대보기
<p align="center"><img src="https://user-images.githubusercontent.com/87688023/204851972-18fc3698-8d9d-4c80-ace0-e37d781370f7.gif"></img></p>

### 게시판 Activity

<p align="center"><img src="https://user-images.githubusercontent.com/87688023/204852435-1e240d8a-f426-4b72-9f38-6af0fdaa7cb3.gif"></img></p>
