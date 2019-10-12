<?php 
	$con = mysqli_connect("localhost","root","","prueba");

	$ocupacion = $_POST["ocupacion"];
	$id = $_POST["id"];
	$statement = mysqli_prepare($con, "UPDATE usuario SET ocupacion = ? WHERE id = ?");
	mysqli_stmt_bind_param($statement,"ss", $ocupacion, $id);
	mysqli_stmt_execute($statement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);
?>