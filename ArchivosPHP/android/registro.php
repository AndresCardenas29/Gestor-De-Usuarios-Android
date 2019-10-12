<?php
	$con = mysqli_connect("localhost","root","","prueba");

	$nombre = $_GET["nombre"];
	$usuario = $_GET["usuario"];
	$email = $_GET["email"];
	$password = $_GET["password"];
	$ocupacion = $_GET["ocupacion"];


	$statement = mysqli_prepare($con, "SELECT usuario,email FROM usuario WHERE usuario = ?");
	mysqli_stmt_bind_param($statement, "s", $usuario);
	mysqli_stmt_execute($statement);

	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement, $getUsuario, $getEmail);

	$respuesta = false;

	while (mysqli_stmt_fetch($statement)) {
		$respuesta = true;
	}

	if($usuario != $getUsuario && $email != $getEmail){
		$statement = mysqli_prepare($con, "INSERT INTO usuario (nombre, usuario, email, password,ocupacion) VALUES (?, ?, ?, ?, ?)");
		mysqli_stmt_bind_param($statement,"sssss", $nombre, $usuario, $email, $password, $ocupacion);
		mysqli_stmt_execute($statement);

		$response = array();
		$response["success"] = true;

		echo json_encode($response);

		$rpta = array();
	}elseif($usuario == $getUsuario){
		$rpta["rpta"] = "usuario";
		echo json_encode($rpta);
	}elseif ($email == $getEmail) {
		$rpta["rpta"] = "email";
		echo json_encode($rpta);
	}else{
		$rpta["rpta"] = "otro";
		echo json_encode($rpta);
	}


?>