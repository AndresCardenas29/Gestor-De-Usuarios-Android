<?php 
	$con = mysqli_connect("localhost","root","","prueba");

	$horasExtrasDiurnas = $_POST["horasExtrasDiurnas"];
	$id = $_POST["id"];
	$statement = mysqli_prepare($con, "UPDATE usuario SET horasExtrasDiurnas = ? WHERE id = ?");
	mysqli_stmt_bind_param($statement,"ss", $horasExtrasDiurnas, $id);
	mysqli_stmt_execute($statement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);
?>