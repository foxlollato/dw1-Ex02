<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		<meta charset="UTF-8" />
		<title>Exemplo 1 - Webmail</title>
	</head>
	<body>				
		<c:choose>
			<c:when test="${not empty listaInativos}">				
				
				<table>
					<tr>
						<th>Nome</th>
						<th>Usuario</th>
						<th>Perfil</th>
						<th>Ação</th>
					</tr>				
					<c:forEach items="${listaInativos}" var="inativo">				
						<tr>
							<th>${inativo.nome}</th>
							<th>${inativo.email}</th>
							<th>${inativo.perfil}</th>													
							
							
							<th><a href="${pageContext.request.contextPath}/acao/ativar?usuario=${inativo.email}">Ativar</a> <a href="${pageContext.request.contextPath}/acao/excluir?usuario=${inativo.email}">Excluir</a></th>
						</tr>					
									
					</c:forEach>
				</table>
				
			</c:when>
			<c:otherwise>
				<p>Não há usuários inativos</p>
			</c:otherwise>
		</c:choose>
		
				
	</body>
</html>