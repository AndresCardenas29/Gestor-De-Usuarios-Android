<?php 
$usuario =$_REQUEST["usuario"];
$con = mysql_connect("localhost","root","") or die("sin conexion");
mysql_select_db("prueba");
$sql = "SELECT id, nombre, usuario, salario, horarioTrabajo FROM usuario WHERE usuario='$usuario'";
$datos = array();
$rs = mysql_query($sql, $con);
while($row=mysql_fetch_object($rs)){
	$datos[] = $row;
}
echo json_encode($datos);

?>
