<?php 
	$mysqli = new mysqli("localhost","root","","prueba");
	if($mysqli->connect_errno){
		die("Fallo la conexion");
	} else {
		//echo "conexion exitosa!\n";
	}
 ?>