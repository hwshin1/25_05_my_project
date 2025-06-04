<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Preview</title>
<script src="https://cdn.tailwindcss.com"></script>
<style>
html, body {
	display: flex;
	flex-direction: column;
	flex: 1;
	width: 100%;
	height: 100%;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}
</style>
</head>
<body>
	<div class="w-full max-w-md mx-auto mt-12 p-6 bg-neutral-100 rounded-xl shadow-md">
		<h2 class="text-xl font-semibold text-center mb-6 text-gray-700">회원가입</h2>

		<form class="space-y-4">
			<div>
				<label for="username" class="block text-sm text-gray-600 mb-1">아이디</label> <input id="username" type="text"
					placeholder="아이디를 입력하세요"
					class="w-full px-4 py-2 border border-gray-400 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400" />
			</div>

			<div>
				<label for="password" class="block text-sm text-gray-600 mb-1">비밀번호</label> <input id="password" type="password"
					placeholder="비밀번호를 입력하세요"
					class="w-full px-4 py-2 border border-gray-400 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400" />
			</div>

			<div>
				<label for="name" class="block text-sm text-gray-600 mb-1">이름</label> <input id="name" type="text"
					placeholder="이름을 입력하세요"
					class="w-full px-4 py-2 border border-gray-400 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400" />
			</div>

			<div>
				<label for="nickname" class="block text-sm text-gray-600 mb-1">닉네임</label> <input id="nickname" type="text"
					placeholder="닉네임을 입력하세요"
					class="w-full px-4 py-2 border border-gray-400 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400" />
			</div>
			
			<div>
				<label for="email" class="block text-sm text-gray-600 mb-1">이메일</label> <input id="email" type="text"
					placeholder="이메일을 입력하세요"
					class="w-full px-4 py-2 border border-gray-400 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400" />
			</div>

			<button type="submit" class="w-full py-2 bg-gray-800 text-white rounded-full hover:bg-gray-700 transition">
				회원가입</button>
		</form>

		<div class="mt-6 text-center">
			<p class="text-sm text-gray-600 mb-2">SNS로 회원가입</p>
			<a href="${location}"> <img src="/kakao_login_medium_narrow.png" alt="카카오 로그인" class="mx-auto w-36" />
			</a>
		</div>
	</div>
</body>
</html>
