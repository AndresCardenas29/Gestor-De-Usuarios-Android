<?php 
	$con = mysqli_connect("localhost","root","","prueba");

	$nombre = $_POST["nombre"];
	$usuario = $_POST["usuario"];
	$email = $_POST["email"];
	$password = $_POST["password"];
	$ocupacion = $_POST["ocupacion"];

	$statement = mysqli_prepare($con, "INSERT INTO usuario (nombre, usuario, email, password,ocupacion) VALUES (?, ?, ?, ?, ?)");
	mysqli_stmt_bind_param($statement,"sssss", $nombre, $usuario, $email, $password, $ocupacion);
	mysqli_stmt_execute($statement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);
?>