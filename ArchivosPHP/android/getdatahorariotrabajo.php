<?php 
//$id =$_REQUEST["id"];
	$id = '0';
	$con = mysql_connect("localhost","root","") or die("sin conexion");
	mysql_select_db("prueba");
	$sql = "SELECT id, nombre, horarioTrabajo FROM usuario WHERE id>$id";
	$datos = array();
	$rs = mysql_query($sql, $con);
	while($row=mysql_fetch_object($rs)){
		$datos[] = $row;
	}
	echo json_encode($datos);

?>
