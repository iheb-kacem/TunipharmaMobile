<?php  
 $hostname_localhost ="localhost";  
 $database_localhost ="tunipharma";  
 $username_localhost ="moez";  
 $password_localhost ="moez";  
 $con = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)  
 or  
 trigger_error(mysql_error(),E_USER_ERROR);  
 ?>