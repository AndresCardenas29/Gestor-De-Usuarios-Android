<?php 
	$con = mysqli_connect("localhost","root","","prueba");

	$tipo = $_POST["tipo"];
	$id = $_POST["id"];
	$statement = mysqli_prepare($con, "UPDATE usuario SET tipo = ? WHERE id = ?");
	mysqli_stmt_bind_param($statement,"ss", $tipo, $id);
	mysqli_stmt_execute($statement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);
?>