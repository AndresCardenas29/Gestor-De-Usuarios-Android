<?php 
	$con = mysqli_connect("localhost","root","","prueba");

	$horasExtras = $_POST["horasExtras"];
	$id = $_POST["id"];
	$statement = mysqli_prepare($con, "UPDATE usuario SET horasExtras = ? WHERE id = ?");
	mysqli_stmt_bind_param($statement,"ss", $horasExtras, $id);
	mysqli_stmt_execute($statement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);
?>