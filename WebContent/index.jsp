<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		<meta charset="UTF-8" />
		<title>Exemplo 1 - Webmail</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/acao/login" method="post">
			<label for="email">
				Email:
			</label>
			<input type="text" name="email" id="email" required="required" />
			<label for="senha">
				Senha:
			</label>
			<input type="password" name="senha" id="senha" required="required" />
			<input type="submit" name="botao" value="Entrar" />
		</form>
		<a href="${pageContext.request.contextPath}/acao/cadastro">Efetuar cadastro</a>
	</body>
</html>