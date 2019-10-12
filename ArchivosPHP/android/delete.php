<?php 
	$con = mysqli_connect("localhost","root","","prueba");

	$id = 21;

	$statement = mysqli_prepare($con, "DELETE FROM usuario WHERE id = ?");
	mysqli_stmt_bind_param($statement,"s", $id);
	mysqli_stmt_execute($statement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);
?>