<?php 
	$con = mysqli_connect("localhost","root","","prueba");

	$horasExtrasNocturnas = $_POST["horasExtrasNocturnas"];
	$id = $_POST["id"];
	$statement = mysqli_prepare($con, "UPDATE usuario SET horasExtrasNocturnas = ? WHERE id = ?");
	mysqli_stmt_bind_param($statement,"ss", $horasExtrasNocturnas, $id);
	mysqli_stmt_execute($statement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);
?>