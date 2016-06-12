<?php   

      require_once('connect.php'); 
      require_once('GoogleMapAPI.class.php');

      $places = array();
      $user = array( "log" => $_GET['log'],"lat" => $_GET['lat']);

      mysql_select_db($database_localhost,$con);  
      $query_search = "SELECT  * FROM `pharmacie` where etat = 'active';";  
      $query_exec = mysql_query($query_search) or die(mysql_error());  
      
      if($query_exec!=null){  
      	while($result_array = mysql_fetch_assoc($query_exec)){
            $places[$result_array['ID']] = $result_array;
         }
      }  


      $map = new GoogleMapAPI('map');
      // Entrez votre clé google maps 
      $map->setAPIKey('AIzaSyApra_QEBW3-pPoaWIQNmCpY5e0skbX3pQ');
      $plusProche = null;
      foreach($places as $key => $value)
      {

         $value['distance'] = $map->geoGetDistance($user['lat'],$user['log'], $value['lat'],$value['log'], "K");
         $places[$key] = $value;
         if($plusProche == null){
            $plusProche = $value;
         }elseif($plusProche['distance']>$value['distance']){
            $plusProche = $value;
         }
      }
      if($plusProche != null){  
         $xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
         $root_element = "plusProche"; 
         $xml .= "<$root_element>";
       
         foreach($plusProche as $key => $value)
         {
            //$key holds the table column name
            $xml .= "<$key>";
            //embed the SQL data in a CDATA element to avoid XML entity issues
            $xml .= "<![CDATA[$value]]>"; 
            //and close the element
            $xml .= "</$key>";
         }
         //close the root element
         $xml .= "</$root_element>";
         //send the xml header to the browser
         header ("Content-Type:text/xml"); 
         //output the XML data
         echo $xml;
      }  
 ?>  