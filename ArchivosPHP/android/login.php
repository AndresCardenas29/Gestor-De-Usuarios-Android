<?php 
	$con = mysqli_connect("localhost","root","","prueba");

	$usuario = $_POST['usuario'];
	$password = $_POST['password'];


	$statement = mysqli_prepare($con, "SELECT * FROM usuario WHERE usuario = ? AND password = ?");
	mysqli_stmt_bind_param($statement, "ss", $usuario, $password);
	mysqli_stmt_execute($statement);

	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement, $id, $nombre, $usuario, $email, $password, $tipo, $ocupacion, $salario, $horarioTrabajo, $horasExtras, $horasExtrasDiurnas, $horasExtrasNocturnas);

	$response = array();
	$response["success"] = false;

	while (mysqli_stmt_fetch($statement)) {
		$response["success"] = true;
		$response["id"] = $id;
		$response["nombre"] = $nombre;
		$response["usuario"] = $usuario;
		$response["email"] = $email;
		$response["password"] = $password;
		$response["tipo"] = $tipo;
		$response['ocupacion'] = $ocupacion;
		$response['salario'] = $salario;
		$response['horarioTrabajo'] = $horarioTrabajo;
		$response['horasExtras'] = $horasExtras;
		$response['horasExtrasDiurnas'] = $horasExtrasDiurnas;
		$response['horasExtrasNocturnas'] = $horasExtrasNocturnas;
	}

	echo json_encode($response);

?>