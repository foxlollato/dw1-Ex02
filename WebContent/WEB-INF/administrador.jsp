<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		<meta charset="UTF-8" />
		<title>Administrador</title>
	</head>
	<body>
		<p>Administrador</p>
		<c:out value="${usuario.nome}"></c:out>
		<br/>	
		<a href="listaInativo">Exibir usuários inativos</a>
		<br/>	
		<a href="logout">Sair da aplicação</a>
</html>