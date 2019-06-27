<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		<meta charset="UTF-8" />
		<title>Exemplo 1 - Webmail</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/acao/cadastrar" method="post">
			<label for="nome">
				Nome:
			</label>
			<input type="text" name="nome" id="nome" required="required" />
			
			<label for="email">
				Email:
			</label>
			<input type="text" name="email" id="email" required="required" />
			
			<label for="senha">
				Senha:
			</label>
			<input type="password" name="senha" id="senha" required="required" />
			
			<c:choose>
				<c:when test="${not empty listaPerfil}">
					<label for="perfil">
						Escolha o perfil:
					</label>
					<select id="perfil" name="perfil">
						<c:forEach items="${listaPerfil}" var="perfil">				
						<option value="${perfil}">${perfil}</option>					
						</c:forEach>
					</select>
				</c:when>
			</c:choose>
			<input type="submit" name="botao" value="Cadastrar" />
		</form>		
	</body>
</html>