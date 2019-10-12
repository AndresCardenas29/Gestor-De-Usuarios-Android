<?php 
	$con = mysqli_connect("localhost","root","","prueba");

	$horarioTrabajo = $_POST["horarioTrabajo"];
	$id = $_POST["id"];
	$statement = mysqli_prepare($con, "UPDATE usuario SET horarioTrabajo = ? WHERE id = ?");
	mysqli_stmt_bind_param($statement,"ss", $horarioTrabajo, $id);
	mysqli_stmt_execute($statement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);
?>